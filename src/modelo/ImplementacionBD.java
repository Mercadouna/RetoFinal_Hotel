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
			System.out.println("Error al intentar abrir la BD");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Este método sirve para obtener todas las habitaciones de la base de datos.
	Primero se conecta, luego hace una consulta y va leyendo cada habitación, creando objetos Room con sus datos.
	Al final guarda todas las habitaciones en una lista y la devuelve.
	 * @return
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
			System.out.println("Error al recuperar las habitaciones");
			e.printStackTrace();
		}
		return rooms;
	}
	
	/**
	 * Este método sirve para obtener todos los clientes de la base de datos.
	Primero se conecta, luego ejecuta una consulta para leer los clientes y va recorriendo los resultados uno a uno, creando objetos Customer con sus datos.
	Al final guarda todos esos clientes en una lista y la devuelve
	 * @return
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
			System.out.println("Error al recuperar los clientes");
			e.printStackTrace();
		}
		return customers;
	}
	
	/**
	 * Este método sirve para obtener todas las reservas de la base de datos.
	Primero se conecta, luego hace una consulta y va leyendo cada resultado, creando objetos de cliente, habitación y reserva con sus datos.
	Después junta todo en un objeto Aux_booking, lo guarda en una lista y al final devuelve todas las reservas.
	 * @return
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
			System.out.println("Error al recuperar las habitaciones");
			e.printStackTrace();
		}
		return bookings;
	}
	
	/**
	 * Este método sirve para añadir un cliente nuevo a la base de datos.
	 Primero se conecta, luego prepara una consulta para insertar los datos del cliente y le pasa el nombre, apellido, teléfono y DNI.
	 Después ejecuta la consulta y, si todo ha ido bien, devuelve true; si hay algún error, devuelve false.
	 * @param name
	 * @param surname
	 * @param phone
	 * @param dni
	 * @return
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
			System.out.println("Error al añadir cliente");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Este método sirve para eliminar un cliente de la base de datos según su ID.
	Primero se conecta y prepara una consulta de tipo DELETE, donde se le pasa el identificador del cliente.
	Después ejecuta la consulta y, si se elimina alguna fila, devuelve true; si no o si ocurre un error, devuelve false.
	 * @param id
	 * @return
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
	 * Este método sirve para crear una nueva reserva en la base de datos.
	Primero se conecta y prepara una consulta INSERT con los datos de la reserva, como la habitación, el cliente, las fechas de entrada y salida y si está pagada o no.
	Después ejecuta la consulta y devuelve true si se ha guardado correctamente, o false si hay algún error.
	 * @param id_room
	 * @param id_customer
	 * @param check_in
	 * @param check_out
	 * @param paid
	 * @return
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
			System.out.println("Error al añadir Reserva");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Este método sirve para añadir un nuevo servicio extra a la base de datos.
	Primero se conecta y prepara una consulta de tipo INSERT, donde se guardan el nombre del servicio y su precio.
	Después ejecuta la consulta y devuelve true si se ha añadido correctamente, o false si ocurre algún error.
	 * @param name_service
	 * @param price
	 * @return
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
			System.out.println("Error al añadir cliente");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Este método sirve para comprobar si un número de teléfono ya existe en la base de datos.
	Primero se conecta y ejecuta una consulta que busca ese teléfono.
	Si encuentra algún resultado, significa que existe y devuelve true; si no, devuelve false.
	 * @param phone
	 * @return
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
			System.out.println("Error al comprobar el teléfono");
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Este método sirve para comprobar si un número de teléfono ya existe en la base de datos, pero ignorando un cliente concreto.
	Primero se conecta y ejecuta una consulta buscando ese teléfono en otros clientes distintos al que se excluye por su ID.
	Si encuentra algún resultado, devuelve true; si no, devuelve false.
	 * @param phone
	 * @param excludeId
	 * @return
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
	 * Este método sirve para comprobar si un DNI ya existe en la base de datos.
	Primero se conecta y ejecuta una consulta que busca ese DNI en los clientes.
	Si encuentra algún resultado, devuelve true, y si no encuentra nada, devuelve false.
	 * @param dni
	 * @return
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
			System.out.println("Error al comprobar el DNI");
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Este método sirve para comprobar si un DNI ya existe en la base de datos, pero ignorando a un cliente concreto por su ID.
	Primero se conecta y ejecuta una consulta que busca ese DNI en otros clientes distintos al que se excluye.
	Si encuentra algún resultado, devuelve true; si no, devuelve false.
	 * @param dni
	 * @param excludeId
	 * @return
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
	 * Este método sirve para modificar los datos de un cliente en la base de datos.
	Primero se conecta y prepara una consulta UPDATE, donde se actualizan el nombre, apellido, teléfono y DNI del cliente.
	Después ejecuta la consulta usando el ID para saber qué cliente se tiene que modificar, y devuelve true si se ha actualizado correctamente o false si hay algún error.
	 * @param id
	 * @param name
	 * @param surname
	 * @param phone
	 * @param dni
	 * @return
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
			System.out.println("Error al editar cliente");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Este método sirve para comprobar si una habitación está disponible en unas fechas concretas.
	Primero se conecta a la base de datos y ejecuta una consulta que busca si la habitación está libre entre la fecha de entrada y salida.
	Si la consulta devuelve un resultado, guarda ese valor y lo devuelve como true o false según la disponibilidad.
	 * @param roomNumber
	 * @param checkIn
	 * @param checkOut
	 * @return
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
			System.out.println("Error al comprobar la disponibilidad de la habitación");
			e.printStackTrace();
		}
		return valido;
	}
	
	/**
	 * Este método sirve para comprobar si un cliente está disponible en unas fechas concretas.
	Primero se conecta a la base de datos y ejecuta una consulta que comprueba si el cliente tiene alguna reserva entre la fecha de entrada y salida.
	Si la consulta devuelve un resultado, se guarda y se devuelve como true o false según si el cliente está libre o no.
	 * @param idCustomer
	 * @param checkIn
	 * @param checkOut
	 * @return
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
			System.out.println("Error al comprobar la disponibilidad del cliente");
			e.printStackTrace();
		}
		return valido;
	}
	
	/**
	 * Este método sirve para comprobar si una habitación existe en la base de datos.
	Primero se conecta y ejecuta una consulta buscando el número de habitación.
	Si encuentra algún resultado, devuelve true, y si no encuentra nada, devuelve false.
	 * @param roomNumber
	 * @return
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
	 * Este método sirve para comprobar si un cliente existe en la base de datos.
	Primero se conecta y ejecuta una consulta buscando el cliente por su ID.
	Si encuentra algún resultado, devuelve true, y si no encuentra nada, devuelve false.
	 * @param idCustomer
	 * @return
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
	 * Este método sirve para comprobar si el usuario y la contraseña son correctos.
	Primero se conecta a la base de datos y ejecuta una consulta buscando un usuario que coincida con esos datos.
	Si encuentra un resultado, significa que el login es correcto y devuelve true; si no, devuelve false.
	 * @param user
	 * @param password
	 * @return
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
			System.out.println("Error al iniciar sesión");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Este método sirve para eliminar una reserva de la base de datos.
	Primero se conecta y prepara una consulta DELETE usando el ID de la reserva.
	Después ejecuta la consulta y, si se elimina alguna fila, devuelve true; si no o si hay un error, devuelve false.
	 * @param id
	 * @return
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
			System.out.println("Error al eliminar reserva");
			e.printStackTrace();
		}
		return correct;
	}
	
	/**
	 * Este método sirve para comprobar si una reserva existe en la base de datos.
	Primero se conecta y ejecuta una consulta buscando la reserva por su ID.
	Si encuentra algún resultado, devuelve true, y si no encuentra nada, devuelve false.
	 * @param id
	 * @return
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
	 * Este método sirve para obtener los servicios extra de una reserva concreta.
	Primero se conecta a la base de datos y ejecuta una consulta usando el ID de la reserva.
	Luego recorre los resultados, crea objetos ExtraService con los datos y los guarda en una lista que finalmente devuelve.
	 * @param idBooking
	 * @return
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
			System.out.println("Error al ver los servicios extra de la reserva");
			e.printStackTrace();
		}
		return extraServices;
	}
	
	/**
	 * Este método sirve para añadir un servicio extra a una reserva.
	Primero se conecta a la base de datos y prepara una consulta INSERT con el ID de la reserva y el ID del servicio.
	Después ejecuta la consulta y devuelve true si se ha añadido correctamente, o false si ocurre algún error.
	 * @param idBooking
	 * @param idService
	 * @return
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
	 * Este método sirve para comprobar si un servicio extra existe en la base de datos.
	Primero se conecta y ejecuta una consulta buscando el servicio por su ID.
	Si encuentra algún resultado, devuelve true, y si no encuentra nada, devuelve false.
	 * @param extraServiceId
	 * @return
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
			System.out.println("Error al comprobar si el servicio extra existe");
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Este método sirve para obtener todas las reservas que todavía no están pagadas.
	Primero se conecta a la base de datos y ejecuta una consulta que filtra solo las reservas sin pagar.
	Después recorre los resultados, crea objetos de cliente, habitación y reserva, los junta en un Aux_booking, los guarda en una lista y finalmente la devuelve.
	 * @return
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
			System.out.println("Error al recuperar reservas sin pagar");
			e.printStackTrace();
		}
		return bookings;
	}
	
	/**
	 * Este método sirve para comprobar si un servicio extra ya está añadido a una reserva concreta.
	Primero se conecta a la base de datos y ejecuta una consulta usando el ID de la reserva y el ID del servicio.
	Si encuentra algún resultado, devuelve true, y si no encuentra nada, devuelve false.
	 * @param idBooking
	 * @param idService
	 * @return
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
			System.out.println("Error al comprobar si el servicio extra está en la reserva");
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * Este método sirve para eliminar un servicio extra de una reserva.
	Primero se conecta a la base de datos y prepara una consulta DELETE usando el ID de la reserva y el ID del servicio.
	Después ejecuta la consulta y devuelve true si se ha eliminado correctamente, o false si ocurre algún error.
	 * @param idBooking
	 * @param idService
	 * @return
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
			System.out.println("Error al eliminar servicio extra de la reserva");
			e.printStackTrace();
		}
		return correct;
	}
	
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
			System.out.println("Error al recuperar la reserva por ID");
			e.printStackTrace();
		}
		return result;
	}

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
			System.out.println("Error al ver los servicios extra");
			e.printStackTrace();
		}
		return extraServices;
	}

	
	/**
	 * Este método sirve para cambiar el estado de pago de una reserva.
	Primero se conecta a la base de datos y ejecuta una consulta que actualiza el campo de “pagado” usando el ID de la reserva.
	Después ejecuta la consulta y devuelve true si el cambio se ha hecho correctamente, o false si ocurre algún error.
	 * @param id
	 * @return
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
			System.out.println("Error al cambiar el estado de pago de la reserva");
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
	public boolean deleteExtraService() {
		// TODO Auto-generated method stub
		return false;
	}
}
