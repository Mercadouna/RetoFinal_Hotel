package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ImplementacionBD implements AdminDAO {
	// Atributos
	private Connection con;
	private PreparedStatement stmt;

	// Los siguientes atributos se utilizan para recoger los valores del fich de
	// configuraci n
	private ResourceBundle configFile;
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String passwordBD;

	// Sentencias SQL
	private final String SQL_VIEW_ROOMS = "SELECT * FROM Room";
	private final String SQL_VIEW_BOOKINGS = "SELECT c.id_customer, c.name_customer, c.surname, r.room_number, r.type_room, b.check_in, b.check_out, b.paid FROM Booking b JOIN Customer c ON b.id_customer=c.id_customer JOIN Room r ON b.id_room = r.id_room";
	private final String SQL_ADD_BOOKING = "INSERT INTO Booking (id_room, id_customer, check_in, check_out, paid) VALUES (?, ?, ?, ?, ?)";
	private final String SQL_VIEW_CUSTOMERS = "SELECT * FROM Customer";
	private final String SQL_VIEW_EXTRA_SERVICES = "SELECT * FROM Extra_Service";
	private final String SQL_ADD_EXTRA_SERVICE = "INSERT INTO Extra_Service (name_service, price) VALUES (?, ?)";
	private final String SQL_ADD_CUSTUMER = "INSERT INTO Customer (name_customer, surname, phone, dni) VALUES (?, ?, ?, ?)";
	private final String SQL_BORRAR_CUSTOMER = "DELETE FROM Customer WHERE id_customer=?";
	private final String SQL_EDIT_CUSTOMER = "UPDATE Customer SET name_costumer = ?, surname = ?, phone = ?, dni = ? WHERE id_customer = ?";

	// final String SQL = "SELECT * FROM usuario WHERE nombre = ? AND contrasena =
	// ?";

	// Para la conexi n utilizamos un fichero de configuaraci n, config que
	// guardamos en el paquete control:
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

				Booking b = new Booking();

				b.setCheckIn(rs.getObject("check_in", LocalDate.class));
				b.setCheckOut(rs.getObject("check_out", LocalDate.class));
				b.setPaid(rs.getBoolean("paid"));

				Aux_booking booking = new Aux_booking(c, r, b);

				bookings.add(booking);
			}
		} catch (SQLException e) {
			System.out.println("Error al recuperar las habitaciones");
			e.printStackTrace();
		}
		return bookings;

	}

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
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_ADD_BOOKING);
			stmt.setInt(1, id_room);
			stmt.setInt(2, id_customer);
			stmt.setDate(3, java.sql.Date.valueOf(check_in));
			stmt.setDate(4, java.sql.Date.valueOf(check_out));
			stmt.setBoolean(5, paid);

			int rowsAffected = stmt.executeUpdate();
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
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_ADD_EXTRA_SERVICE);
			stmt.setString(1, name_service);
			stmt.setDouble(2, price);

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
	public boolean deleteExtraService() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkPhone(int phone) {
		boolean exists = false;
		this.openConnection();
		try {
			String sql = "SELECT * FROM Customer WHERE phone = ?";
			stmt = con.prepareStatement(sql);
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

	@Override
	public boolean checkDni(String dni) {
		boolean exists = false;
		this.openConnection();
		try {
			String sql = "SELECT * FROM Customer WHERE dni = ?";
			stmt = con.prepareStatement(sql);
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
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				correct = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al editar cliente");
			e.printStackTrace();
		}
		return correct;
	}

}
