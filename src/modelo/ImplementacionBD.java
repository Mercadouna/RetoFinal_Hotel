package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private final String SQL_VIEW_BOOKINGS = "SELECT c.id_customer, c.name_costumer, c.surname, r.room_number, r.type_room, b.check_in, b.check_out, b.paid FROM Booking b JOIN Customer c ON b.id_customer=c.id_customer JOIN Room r ON b.id_room = r.room_number";
	private final String SQL_VIEW_CUSTOMERS = "SELECT * FROM Customer";
	
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

	public ArrayList<Customer> viewCustomers(){
		ArrayList<Customer>customers = new ArrayList<>();
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_VIEW_BOOKINGS);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int idCustomer = rs.getInt("id_customer");
				String name = rs.getString("name_customer");
				String surname = rs.getString("surname");
				int phone = rs.getInt("phone");
				String dni = rs.getString("dni");
				
				Customer customer = new Customer(idCustomer, name, surname, phone, dni);
				customers.add(customer);
			}
		}catch (SQLException e) {
			System.out.println("Error al recuperar las habitaciones");
			e.printStackTrace();
		}
		return customers;
		
	}
	
	/*public ArrayList<Aux_booking> viewBookings(){
		ArrayList<Booking>bookings = new ArrayList<>();
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_VIEW_BOOKINGS);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int idCustomer = rs.getInt("id_customer");
				String name = rs.getString("name_customer");
				String surname = rs.getString("surname");
				Booking booking = new Booking();
			}
		}catch (SQLException e) {
			System.out.println("Error al recuperar las habitaciones");
			e.printStackTrace();
		}
		return bookings;
		
	}*/
	
	
}
