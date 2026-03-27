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

import com.mysql.cj.jdbc.CallableStatement;

public class ImplementacionBD implements AdminDAO {
	// Atributos
	private Connection con;
	private PreparedStatement stmt;
	private java.sql.CallableStatement cs;

	// Los siguientes atributos se utilizan para recoger los valores del fich de
	// configuraci n
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
			System.out.println("Error al intentar abrir la BD");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

		} catch (SQLException e) {
			System.out.println("Error al recuperar las habitaciones");
			e.printStackTrace();
		}

		return rooms;
	}

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
		} catch (SQLException e) {
			System.out.println("Error al recuperar los clientes");
			e.printStackTrace();
		}
		return customers;

	}

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
		} catch (SQLException e) {
			System.out.println("Error al recuperar las habitaciones");
			e.printStackTrace();
		}
		return bookings;

	}

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

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al añadir cliente");
			e.printStackTrace();
		}
		return correct;
	}

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

	@Override
	public boolean createBooking(int id_room, int id_customer, LocalDate check_in, LocalDate check_out, boolean paid) {
		// TODO Auto-generated method stub
		boolean correct = false;
		int rowsAffected;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_ADD_BOOKING);
			stmt.setInt(1, id_room);
			stmt.setInt(2, id_customer);
			stmt.setDate(3, java.sql.Date.valueOf(check_in));
			stmt.setDate(4, java.sql.Date.valueOf(check_out));
			stmt.setBoolean(5, paid);

			rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al añadir Reserva");
			e.printStackTrace();
		}
		return correct;
	}

	@Override
	public boolean editBooking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBooking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addExtraService(String name_service, double price) {
		boolean correct = false;
		int rowsAffected;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_ADD_EXTRA_SERVICE);
			stmt.setString(1, name_service);
			stmt.setDouble(2, price);

			rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al añadir cliente");
			e.printStackTrace();
		}
		return correct;
	}

	@Override
	public boolean deleteExtraService() {
		// TODO Auto-generated method stub
		return false;
	}

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
		} catch (SQLException e) {
			System.out.println("Error al comprobar el teléfono");
			e.printStackTrace();
		}
		return exists;
	}

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

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
		} catch (SQLException e) {
			System.out.println("Error al comprobar el DNI");
			e.printStackTrace();
		}
		return exists;
	}

	// Overloaded: excludes customer with given id (used in edit)
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	@Override
	public boolean editCostumer(int id, String name, String surname, int phone, String dni) {
		boolean correct = false;
		int rowsAffected;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_EDIT_CUSTOMER);
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setInt(3, phone);
			stmt.setString(4, dni);
			stmt.setInt(5, id);
			rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al editar cliente");
			e.printStackTrace();
		}
		return correct;
	}

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
		} catch (SQLException e) {
			System.out.println("Error al comprobar la disponibilidad de la habitación");
			e.printStackTrace();
		}
		return valido;
	}

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
		} catch (SQLException e) {
			System.out.println("Error al comprobar la disponibilidad del cliente");
			e.printStackTrace();
		}
		return valido;
	}

	public boolean checkRoomExists(int roomNumber) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_ROOM_EXISTS);
			stmt.setInt(1, roomNumber);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				exists = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	public boolean checkCustomerExists(int idCustomer) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_CUSTOMER_EXISTS);
			stmt.setInt(1, idCustomer);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				exists = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

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
		} catch (SQLException e) {
			System.out.println("Error al iniciar sesión");
			e.printStackTrace();
		}
		return correct;
	}

	@Override
	public boolean deleteBooking(int id) {
		boolean correct = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_DELETE_BOOKING);
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al eliminar reserva");
			e.printStackTrace();
		}
		return correct;
	}

	@Override
	public boolean checkBookingExists(int id) {
		boolean exists = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_CHECK_BOOKING_EXISTS);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				exists = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	
	
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
			System.out.println("Error al ver los servicios extra de la reserva");
			e.printStackTrace();
		}
		return extraServices;
	}
	
	@Override
	public boolean addExtraServiceToBooking(int id, int cant) {
		boolean valid = false;
		this.openConnection();
		
		return valid;
	}

}