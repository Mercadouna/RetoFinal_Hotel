//Javadock: Alain

package modelo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
public class ImplementacionBD implements AdminDAO {
	// Fields
	private Connection con;
	private PreparedStatement stmt;
	private java.sql.CallableStatement cs;
	// The following fields hold configuration values read from the config file
	private ResourceBundle configFile;
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String passwordBD;
	// Sentencias SQL
	private final String SQL_VIEW_ROOMS = "SELECT * FROM Room";
	private final String SQL_VIEW_BOOKINGS = "SELECT b.id_booking, c.id_customer, c.name_customer, c.surname, r.id_room, r.room_number, r.type_room, b.check_in, b.check_out, b.paid FROM Booking b JOIN Customer c ON b.id_customer=c.id_customer JOIN Room r ON b.id_room = r.id_room";
	private final String SQL_ADD_BOOKING = "INSERT INTO Booking (id_room, id_customer, check_in, check_out, paid) VALUES (?, ?, ?, ?, ?)";
	private final String SQL_VIEW_CUSTOMERS = "SELECT * FROM Customer";
	private final String SQL_VIEW_EXTRA_SERVICES = "SELECT * FROM Extra_Service";
	private final String SQL_ADD_EXTRA_SERVICE = "INSERT INTO Extra_Service (name_service, price) VALUES (?, ?)";
	private final String SQL_ADD_CUSTUMER = "INSERT INTO Customer (name_customer, surname, phone, dni) VALUES (?, ?, ?, ?)";
	private final String SQL_BORRAR_CUSTOMER = "DELETE FROM Customer WHERE id_customer=?";
	private final String SQL_EDIT_CUSTOMER = "UPDATE Customer SET name_customer = ?, surname = ?, phone = ?, dni = ? WHERE id_customer = ?";
	private final String SQL_CHECK_ROOM_AVAILABILITY = "SELECT CheckRoomAvailability(?, ?, ?)";
	private final String SQL_CHECK_CUSTOMER_AVAILABILITY = "SELECT CheckCustomerAvailability(?, ?, ?)";
	private final String SQL_CHECK_PHONE = "SELECT * FROM Customer WHERE phone = ?";
	private final String SQL_CHECK_DNI = "SELECT * FROM Customer WHERE dni = ?";
	private final String SQL_CHECK_ROOM_EXISTS = "SELECT * FROM Room WHERE id_room = ?";
	private final String SQL_CHECK_CUSTOMER_EXISTS = "SELECT * FROM Customer WHERE id_customer = ?";
	private final String SQL_CHECK_PHONE_OTHER = "SELECT * FROM Customer WHERE phone = ? AND id_customer != ?";
	private final String SQL_CHECK_DNI_OTHER = "SELECT * FROM Customer WHERE dni = ? AND id_customer != ?";
	private final String SQL_LOGIN = "SELECT * FROM Adm WHERE name_a = ? AND password_a = ?";
	private final String SQL_DELETE_BOOKING = "DELETE FROM Booking WHERE id_booking = ?";
	private final String SQL_CHECK_BOOKING_EXISTS = "SELECT * FROM Booking WHERE id_booking = ?";
	private final String SQL_VIEW_BOOKING_EXTRA_SERVICES = "SELECT E.id_service, E.name_service, E.price FROM Extra_Service E JOIN Booking_Service B ON E.id_service = B.id_service WHERE B.id_booking = ?";
	private final String SQL_ADD_EXTRA_SERVICE_TO_BOOKING = "INSERT INTO Booking_Service (id_booking, id_service) VALUES (?, ?)";
	private final String SQL_CHECK_EXTRA_SERVICE_EXISTS = "SELECT * FROM Extra_Service WHERE id_service = ?";
	private final String SQL_DELETE_EXTRA_SERVICE_FROM_BOOKING = "DELETE FROM Booking_Service WHERE id_booking = ? AND id_service = ?";
	private final String SQL_CHECK_EXTRA_SERVICE_IN_BOOKING = "SELECT * FROM Booking_Service WHERE id_booking = ? AND id_service = ?";
	private final String SQL_VIEW_UNPAID_BOOKINGS = "SELECT b.id_booking, c.id_customer, c.name_customer, c.surname, c.phone, c.dni, r.id_room, r.room_number, r.type_room, b.check_in, b.check_out, b.paid FROM Booking b JOIN Customer c ON b.id_customer = c.id_customer JOIN Room r ON b.id_room = r.id_room WHERE b.paid = false";
	private final String SQL_TOGGLE_PAYMENT = "UPDATE Booking SET paid = NOT paid WHERE id_booking = ?";
	private final String SQL_GET_BOOKING_BY_ID = "SELECT b.id_booking, c.id_customer, c.name_customer, c.surname, r.id_room, r.room_number, r.type_room, r.price_per_night, b.check_in, b.check_out, b.paid FROM Booking b JOIN Customer c ON b.id_customer = c.id_customer JOIN Room r ON b.id_room = r.id_room WHERE b.id_booking = ?";

	
	
	public ImplementacionBD() {
		this.configFile = ResourceBundle.getBundle("configClase");
		this.driverBD = this.configFile.getString("Driver");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}
	private void openConnection() {
		try {
			con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
		} catch (SQLException e) {
			System.out.println("Error trying to open the database connection");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Retrieves all rooms from the database.
	 * Opens a connection, executes a query, and reads each row to build a Room object.
	 * All rooms are collected into a list and returned.
	 *
	 * @return an {@link ArrayList} of {@link Room} objects representing all rooms in the database
	 */
	@Override
	public ArrayList<Room> viewRooms() {
		ArrayList<Room> rooms = new ArrayList<>();
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_VIEW_ROOMS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int idRoom = rs.getInt("id_room");
				int roomNumber = rs.getInt("room_number");
				String typeRoom = rs.getString("type_room");
				String statusRoom = rs.getString("status_room");
				double pricePerNight = rs.getDouble("price_per_night");
				int quantPers = rs.getInt("quant_pers");
				Room room = new Room(idRoom, roomNumber, typeRoom, statusRoom, pricePerNight, quantPers);
				rooms.add(room);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving rooms");
			e.printStackTrace();
		}
		return rooms;
	}
	
	/**
	 * Retrieves all customers from the database.
	 * Opens a connection, executes a query, and iterates over each row to build a Customer object.
	 * All customers are collected into a list and returned.
	 *
	 * @return an {@link ArrayList} of {@link Customer} objects representing all customers in the database
	 */
	@Override
	public ArrayList<Customer> viewCustomers() {
		ArrayList<Customer> customers = new ArrayList<>();
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_VIEW_CUSTOMERS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int idCustomer = rs.getInt("id_customer");
				String name = rs.getString("name_customer");
				String surname = rs.getString("surname");
				int phone = rs.getInt("phone");
				String dni = rs.getString("dni");
				Customer customer = new Customer(idCustomer, name, surname, phone, dni);
				customers.add(customer);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving customers");
			e.printStackTrace();
		}
		return customers;
	}
	
	/**
	 * Retrieves all bookings from the database.
	 * Opens a connection, executes a JOIN query, and reads each row to build Customer, Room, and Booking objects.
	 * These are combined into an {@link Aux_booking} object, collected into a list, and returned.
	 *
	 * @return an {@link ArrayList} of {@link Aux_booking} objects representing all bookings in the database
	 */
	@Override
	public ArrayList<Aux_booking> viewBookings() {
		ArrayList<Aux_booking> bookings = new ArrayList<>();
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_VIEW_BOOKINGS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Customer c = new Customer();
				c.setIdCustomer(rs.getInt("id_customer"));
				c.setNameCostumer(rs.getString("name_customer"));
				c.setSurname(rs.getString("surname"));
				Room r = new Room();
				r.setRoomNumber(rs.getInt("room_number"));
				r.setTypeRoom(rs.getString("type_room"));
				r.setIdRoom(rs.getInt("id_room"));
				Booking b = new Booking();
				b.setCheckIn(rs.getObject("check_in", LocalDate.class));
				b.setCheckOut(rs.getObject("check_out", LocalDate.class));
				b.setPaid(rs.getBoolean("paid"));
				b.setIdBooking(rs.getInt("id_booking"));
				Aux_booking booking = new Aux_booking(c, r, b);
				bookings.add(booking);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving bookings");
			e.printStackTrace();
		}
		return bookings;
	}
	
	/**
	 * Adds a new customer to the database.
	 * Opens a connection and executes an INSERT statement with the provided customer data.
	 *
	 * @param name    the first name of the customer
	 * @param surname the surname of the customer
	 * @param phone   the phone number of the customer
	 * @param dni     the national ID (DNI) of the customer
	 * @return {@code true} if the customer was inserted successfully; {@code false} otherwise
	 */
	@Override
	public boolean addCostumer(String name, String surname, int phone, String dni) {
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_ADD_CUSTUMER);
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setInt(3, phone);
			stmt.setString(4, dni);
			if (stmt.executeUpdate() > 0) {
				correct = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error adding customer");
			e.printStackTrace();
		}
		return correct;
	}

	/**
	 * Deletes a customer from the database by their ID.
	 * Opens a connection and executes a DELETE statement using the given customer identifier.
	 *
	 * @param id the ID of the customer to delete
	 * @return {@code true} if the customer was deleted successfully; {@code false} otherwise
	 */
	@Override
	public boolean deleteCostumer(int id) {
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_BORRAR_CUSTOMER);
			stmt.setInt(1, id);
			if (stmt.executeUpdate() > 0) {
				correct = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error deleting customer");
		}
		return correct;
	}
	
	/**
	 * Creates a new booking in the database.
	 * Opens a connection and executes an INSERT statement with the provided booking data.
	 *
	 * @param id_room      the ID of the room to be booked
	 * @param id_customer  the ID of the customer making the booking
	 * @param check_in     the check-in date
	 * @param check_out    the check-out date
	 * @param paid         {@code true} if the booking has been paid; {@code false} otherwise
	 * @return {@code true} if the booking was created successfully; {@code false} otherwise
	 */
	@Override
	public boolean createBooking(int id_room, int id_customer, LocalDate check_in, LocalDate check_out, boolean paid) {
		// TODO Auto-generated method stub
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_ADD_BOOKING);
			stmt.setInt(1, id_room);
			stmt.setInt(2, id_customer);
			stmt.setDate(3, java.sql.Date.valueOf(check_in));
			stmt.setDate(4, java.sql.Date.valueOf(check_out));
			stmt.setBoolean(5, paid);
			if (stmt.executeUpdate() > 0) {
				correct = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error adding booking");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Adds a new extra service to the database.
	 * Opens a connection and executes an INSERT statement with the service name and price.
	 *
	 * @param name_service the name of the extra service
	 * @param price        the price of the extra service
	 * @return {@code true} if the service was added successfully; {@code false} otherwise
	 */
	@Override
	public boolean addExtraService(String name_service, double price) {
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_ADD_EXTRA_SERVICE);
			stmt.setString(1, name_service);
			stmt.setDouble(2, price);
			if (stmt.executeUpdate() > 0) {
				correct = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error adding extra service");
			e.printStackTrace();
		}
		return correct;
	}

	/**
	 * Checks whether a phone number already exists in the database.
	 * Opens a connection and executes a query searching for the given phone number.
	 *
	 * @param phone the phone number to check
	 * @return {@code true} if the phone number exists; {@code false} otherwise
	 */
	@Override
	public boolean checkPhone(int phone) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_PHONE);
			stmt.setInt(1, phone);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exists = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error checking phone number");
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Checks whether a phone number already exists in the database, ignoring a specific customer.
	 * Opens a connection and searches for the phone number among customers other than the one excluded by ID.
	 * This overload is intended for use during customer editing.
	 *
	 * @param phone     the phone number to check
	 * @param excludeId the ID of the customer to exclude from the search
	 * @return {@code true} if another customer has the same phone number; {@code false} otherwise
	 */
	// Overloaded: excludes customer with given id (used in edit)
	public boolean checkPhone(int phone, int excludeId) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_PHONE_OTHER);
			stmt.setInt(1, phone);
			stmt.setInt(2, excludeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exists = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Checks whether a DNI (national ID) already exists in the database.
	 * Opens a connection and executes a query searching for the given DNI among customers.
	 *
	 * @param dni the DNI to check
	 * @return {@code true} if the DNI exists; {@code false} otherwise
	 */
	@Override
	public boolean checkDni(String dni) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_DNI);
			stmt.setString(1, dni);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exists = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error checking DNI");
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Checks whether a DNI (national ID) already exists in the database, ignoring a specific customer.
	 * Opens a connection and searches for the DNI among customers other than the one excluded by ID.
	 * This overload is intended for use during customer editing.
	 *
	 * @param dni       the DNI to check
	 * @param excludeId the ID of the customer to exclude from the search
	 * @return {@code true} if another customer has the same DNI; {@code false} otherwise
	 */
	public boolean checkDni(String dni, int excludeId) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_DNI_OTHER);
			stmt.setString(1, dni);
			stmt.setInt(2, excludeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exists = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Updates the data of an existing customer in the database.
	 * Opens a connection and executes an UPDATE statement using the customer ID to identify the record.
	 *
	 * @param id      the ID of the customer to update
	 * @param name    the new first name of the customer
	 * @param surname the new surname of the customer
	 * @param phone   the new phone number of the customer
	 * @param dni     the new national ID (DNI) of the customer
	 * @return {@code true} if the customer was updated successfully; {@code false} otherwise
	 */
	@Override
	public boolean editCostumer(int id, String name, String surname, int phone, String dni) {
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_EDIT_CUSTOMER);
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setInt(3, phone);
			stmt.setString(4, dni);
			stmt.setInt(5, id);
			if (stmt.executeUpdate() > 0) {
				correct = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error editing customer");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Checks whether a room is available for the given date range.
	 * Opens a connection and calls a database function that returns whether the room is free
	 * between the check-in and check-out dates.
	 *
	 * @param roomNumber the number of the room to check
	 * @param checkIn    the desired check-in date
	 * @param checkOut   the desired check-out date
	 * @return {@code true} if the room is available; {@code false} otherwise
	 */
	public boolean isRoomAvailable(int roomNumber, LocalDate checkIn, LocalDate checkOut) {
		boolean valido = false;
		try {
			this.openConnection();
			stmt = con.prepareStatement(SQL_CHECK_ROOM_AVAILABILITY);
			stmt.setInt(1, roomNumber);
			stmt.setDate(2, Date.valueOf(checkIn));
			stmt.setDate(3, Date.valueOf(checkOut));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				valido = rs.getBoolean(1);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error checking room availability");
			e.printStackTrace();
		}
		return valido;
	}
	
	/**
	 * Checks whether a customer is available for the given date range.
	 * Opens a connection and calls a database function that returns whether the customer
	 * has no overlapping bookings between the check-in and check-out dates.
	 *
	 * @param idCustomer the ID of the customer to check
	 * @param checkIn    the desired check-in date
	 * @param checkOut   the desired check-out date
	 * @return {@code true} if the customer is available; {@code false} otherwise
	 */
	public boolean isCustomerAvailable(int idCustomer, LocalDate checkIn, LocalDate checkOut) {
		boolean valido = false;
		try {
			this.openConnection();
			stmt = con.prepareStatement(SQL_CHECK_CUSTOMER_AVAILABILITY);
			stmt.setInt(1, idCustomer);
			stmt.setDate(2, Date.valueOf(checkIn));
			stmt.setDate(3, Date.valueOf(checkOut));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				valido = rs.getBoolean(1);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error checking customer availability");
			e.printStackTrace();
		}
		return valido;
	}
	
	/**
	 * Checks whether a room exists in the database.
	 * Opens a connection and executes a query searching for the given room number.
	 *
	 * @param roomNumber the number of the room to look up
	 * @return {@code true} if the room exists; {@code false} otherwise
	 */
	public boolean checkRoomExists(int roomNumber) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_ROOM_EXISTS);
			stmt.setInt(1, roomNumber);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exists = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Checks whether a customer exists in the database.
	 * Opens a connection and executes a query searching for the customer by their ID.
	 *
	 * @param idCustomer the ID of the customer to look up
	 * @return {@code true} if the customer exists; {@code false} otherwise
	 */
	public boolean checkCustomerExists(int idCustomer) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_CUSTOMER_EXISTS);
			stmt.setInt(1, idCustomer);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exists = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Validates administrator credentials against the database.
	 * Opens a connection and executes a query searching for a matching username and password.
	 *
	 * @param user     the administrator username
	 * @param password the administrator password
	 * @return {@code true} if the credentials are valid; {@code false} otherwise
	 */
	public boolean login(String user, String password) {
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_LOGIN);
			stmt.setString(1, user);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				correct = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error during login");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Deletes a booking from the database by its ID.
	 * Opens a connection and executes a DELETE statement using the given booking identifier.
	 *
	 * @param id the ID of the booking to delete
	 * @return {@code true} if the booking was deleted successfully; {@code false} otherwise
	 */
	@Override
	public boolean deleteBooking(int id) {
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_DELETE_BOOKING);
			stmt.setInt(1, id);
			if (stmt.executeUpdate() > 0) {
				correct = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error deleting booking");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Checks whether a booking exists in the database.
	 * Opens a connection and executes a query searching for the booking by its ID.
	 *
	 * @param id the ID of the booking to look up
	 * @return {@code true} if the booking exists; {@code false} otherwise
	 */
	@Override
	public boolean checkBookingExists(int id) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_BOOKING_EXISTS);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exists = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Retrieves all extra services associated with a specific booking.
	 * Opens a connection and executes a JOIN query filtered by the booking ID.
	 * Each result row is used to build an {@link ExtraService} object, which is added to a list.
	 *
	 * @param idBooking the ID of the booking whose extra services are to be retrieved
	 * @return an {@link ArrayList} of {@link ExtraService} objects linked to the given booking
	 */
	@Override
	public ArrayList<ExtraService> viewBookingExtraServices(int idBooking) {
		ArrayList<ExtraService> extraServices = new ArrayList<>();
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_VIEW_BOOKING_EXTRA_SERVICES);
			stmt.setInt(1, idBooking);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ExtraService extraService = new ExtraService();
				extraService.setIdService(rs.getInt("id_service"));
				extraService.setNameService(rs.getString("name_service"));
				extraService.setPrice(rs.getDouble("price"));
				extraServices.add(extraService);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving extra services for the booking");
			e.printStackTrace();
		}
		return extraServices;
	}
	
	/**
	 * Adds an extra service to an existing booking.
	 * Opens a connection and executes an INSERT statement linking the booking and the service.
	 *
	 * @param idBooking the ID of the booking to add the service to
	 * @param idService the ID of the extra service to add
	 * @return {@code true} if the service was added successfully; {@code false} otherwise
	 */
	@Override
	public boolean addExtraServiceToBooking(int idBooking, int idService) {
		boolean valid = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_ADD_EXTRA_SERVICE_TO_BOOKING);
			stmt.setInt(1, idBooking);
			stmt.setInt(2, idService);
			if (stmt.executeUpdate() > 0) {
				valid = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valid;
	}
	
	/**
	 * Checks whether an extra service exists in the database.
	 * Opens a connection and executes a query searching for the service by its ID.
	 *
	 * @param idService the ID of the extra service to look up
	 * @return {@code true} if the extra service exists; {@code false} otherwise
	 */
	@Override
	public boolean checkExtraServiceExists(int idService) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_EXTRA_SERVICE_EXISTS);
			stmt.setInt(1, idService);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exists = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error checking if extra service exists");
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Retrieves all unpaid bookings from the database.
	 * Opens a connection and executes a query filtered to bookings where {@code paid = false}.
	 * Each row is used to build Customer, Room, and Booking objects combined into an {@link Aux_booking}.
	 *
	 * @return an {@link ArrayList} of {@link Aux_booking} objects for all unpaid bookings
	 */
	@Override
	public ArrayList<Aux_booking> viewUnpaidBookings() {
		ArrayList<Aux_booking> bookings = new ArrayList<>();
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_VIEW_UNPAID_BOOKINGS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Customer c = new Customer();
				c.setIdCustomer(rs.getInt("id_customer"));
				c.setNameCostumer(rs.getString("name_customer"));
				c.setSurname(rs.getString("surname"));
				c.setPhone(rs.getInt("phone"));
				c.setDni(rs.getString("dni"));
				Room r = new Room();
				r.setIdRoom(rs.getInt("id_room"));
				r.setRoomNumber(rs.getInt("room_number"));
				r.setTypeRoom(rs.getString("type_room"));
				Booking b = new Booking();
				b.setIdBooking(rs.getInt("id_booking"));
				b.setCheckIn(rs.getObject("check_in", LocalDate.class));
				b.setCheckOut(rs.getObject("check_out", LocalDate.class));
				b.setPaid(rs.getBoolean("paid"));
				bookings.add(new Aux_booking(c, r, b));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving unpaid bookings");
			e.printStackTrace();
		}
		return bookings;
	}
	
	/**
	 * Checks whether an extra service is already linked to a specific booking.
	 * Opens a connection and executes a query using both the booking ID and the service ID.
	 *
	 * @param idBooking the ID of the booking to check
	 * @param idService the ID of the extra service to check
	 * @return {@code true} if the service is already associated with the booking; {@code false} otherwise
	 */
	@Override
	public boolean checkExtraServiceInBooking(int idBooking, int idService) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_EXTRA_SERVICE_IN_BOOKING);
			stmt.setInt(1, idBooking);
			stmt.setInt(2, idService);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				exists = true;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error checking if extra service is already in the booking");
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Removes an extra service from a booking.
	 * Opens a connection and executes a DELETE statement targeting the booking-service link.
	 *
	 * @param idBooking the ID of the booking from which the service will be removed
	 * @param idService the ID of the extra service to remove
	 * @return {@code true} if the service was removed successfully; {@code false} otherwise
	 */
	@Override
	public boolean deleteExtraServiceFromBooking(int idBooking, int idService) {
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_DELETE_EXTRA_SERVICE_FROM_BOOKING);
			stmt.setInt(1, idBooking);
			stmt.setInt(2, idService);
			if (stmt.executeUpdate() > 0) {
				correct = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error removing extra service from booking");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Retrieves a single booking from the database by its ID.
	 * Opens a connection and executes a JOIN query to fetch customer, room, and booking data
	 * for the specified booking. The results are combined into an {@link Aux_booking} object.
	 *
	 * @param idBooking the ID of the booking to retrieve
	 * @return an {@link Aux_booking} containing the booking details, or {@code null} if not found
	 */
	@Override
	public Aux_booking getBookingById(int idBooking) {
		Aux_booking result = null;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_GET_BOOKING_BY_ID);
			stmt.setInt(1, idBooking);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Customer c = new Customer();
				c.setIdCustomer(rs.getInt("id_customer"));
				c.setNameCostumer(rs.getString("name_customer"));
				c.setSurname(rs.getString("surname"));

				Room r = new Room();
				r.setIdRoom(rs.getInt("id_room"));
				r.setRoomNumber(rs.getInt("room_number"));
				r.setTypeRoom(rs.getString("type_room"));
				r.setPricePerNight(rs.getDouble("price_per_night"));

				Booking b = new Booking();
				b.setIdBooking(rs.getInt("id_booking"));
				b.setCheckIn(rs.getObject("check_in", LocalDate.class));
				b.setCheckOut(rs.getObject("check_out", LocalDate.class));
				b.setPaid(rs.getBoolean("paid"));

				result = new Aux_booking(c, r, b); 
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving booking by ID");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Retrieves all extra services from the database.
	 * Opens a connection, executes a query, and reads each row to build an {@link ExtraService} object.
	 * All services are collected into a list and returned.
	 *
	 * @return an {@link ArrayList} of {@link ExtraService} objects representing all available extra services
	 */
	@Override
	public ArrayList<ExtraService> viewExtraServices() {
		ArrayList<ExtraService> extraServices = new ArrayList<>();
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_VIEW_EXTRA_SERVICES);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ExtraService extraService = new ExtraService();
				extraService.setIdService(rs.getInt("id_service"));
				extraService.setNameService(rs.getString("name_service"));
				extraService.setPrice(rs.getDouble("price"));
				extraServices.add(extraService);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error retrieving extra services");
			e.printStackTrace();
		}
		return extraServices;
	}

	
	/**
	 * Toggles the payment status of a booking.
	 * Opens a connection and executes an UPDATE statement that flips the {@code paid} field
	 * for the booking with the given ID.
	 *
	 * @param id the ID of the booking whose payment status will be toggled
	 * @return {@code true} if the update was applied successfully; {@code false} otherwise
	 */
	@Override
	public boolean togglePayment(int id) {
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_TOGGLE_PAYMENT);
			stmt.setInt(1, id);
			if (stmt.executeUpdate() > 0) {
				correct = true;
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error toggling payment status of booking");
			e.printStackTrace();
		}
		return correct;
	}
}
