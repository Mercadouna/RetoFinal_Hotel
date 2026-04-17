package test;

import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import modelo.Booking;
import modelo.Customer;
import modelo.ExtraService;
import modelo.Room;
// import modelo.AdminDAO;
// import controlador.LoginControlador;

class Tests {

	@Test
	void testCustomerModel() {
		// Default constructor: String fields should be null
		Customer empty = new Customer();
		assertNull(empty.getNameCostumer(), "Default constructor should not assign a name, must be null");
		assertNull(empty.getDni(), "Default constructor should not assign a DNI, must be null");

		// Full constructor: fields should be correctly assigned
		Customer c = new Customer(1, "Ana", "Lopez", 600123456, "12345678X");
		assertEquals(1, c.getIdCustomer(), "Customer ID should be 1");
		assertEquals("Ana", c.getNameCostumer(), "Customer name should be Ana");
		assertEquals("12345678X", c.getDni(), "Customer DNI should match the one passed to the constructor");

		// toString should contain the customer's name
		assertTrue(c.toString().contains("Ana"), "toString() should contain the customer's name");
	}

	@Test
	void testRoomModel() {
		// Default constructor: room type should be null
		Room empty = new Room();
		assertNull(empty.getTypeRoom(), "Default constructor should not assign a room type, must be null");

		// Full constructor: fields should be correctly assigned
		Room r = new Room(2, 101, "Suite", "Available", 300.0, 2);
		assertEquals(101, r.getRoomNumber(), "Room number should be 101");
		assertEquals("Suite", r.getTypeRoom(), "Room type should be Suite");

		// Price per night should be positive
		assertTrue(r.getPricePerNight() > 0, "Price per night should be positive");
	}

	@Test
	void testBookingModel() {
		// Default constructor: LocalDate fields should be null
		Booking empty = new Booking();
		assertNull(empty.getCheckIn(), "Default constructor should not assign a check-in date, must be null");
		assertNull(empty.getCheckOut(), "Default constructor should not assign a check-out date, must be null");

		// The paid field works correctly in both states
		Booking paid = new Booking(1, 1, 1, LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 10), true,
				new ArrayList<>());
		assertTrue(paid.isPaid(), "A booking marked as paid should return true in isPaid()");

		Booking unpaid = new Booking(2, 1, 1, LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 5), false,
				new ArrayList<>());
		assertFalse(unpaid.isPaid(), "A booking marked as unpaid should return false in isPaid()");

		// Business rule: check-in date must be before check-out date
		// (validation used in createBooking)
		assertTrue(paid.getCheckIn().isBefore(paid.getCheckOut()),
				"Check-in date must be before check-out date");
	}

	@Test
	void testExtraServiceModel() {
		// Default constructor: service name should be null
		ExtraService empty = new ExtraService();
		assertNull(empty.getNameService(), "Default constructor should not assign a service name, must be null");

		// Full constructor: fields should be correctly assigned
		ExtraService es = new ExtraService(3, "Breakfast", 15.50, 10);
		assertEquals("Breakfast", es.getNameService(), "Service name should be Breakfast");
		assertEquals(15.50, es.getPrice(), "Service price should be 15.50");

		// Extra service price should be positive
		assertTrue(es.getPrice() > 0, "Extra service price should be positive");
	}

	/*
	 * =====================================================================
	 * TESTS WITH MOCKITO — LoginControlador + AdminDAO (mock)
	 *
	 * To run these tests the following is required:
	 * 1. Have mockito-core on the classpath (e.g. mockito-core-5.x.jar)
	 * 2. Uncomment the imports above (AdminDAO, LoginControlador,
	 * org.mockito.Mockito.*)
	 * =====================================================================
	 *
	 * @Test
	 * void testLogin_CorrectCredentials() {
	 * // Arrange: create the AdminDAO mock and configure its behaviour
	 * AdminDAO mockDao = mock(AdminDAO.class);
	 * when(mockDao.login("admin", "1234")).thenReturn(true);
	 *
	 * // Inject the mock into the controller via the constructor
	 * LoginControlador controlador = new LoginControlador(mockDao);
	 *
	 * // Act
	 * boolean resultado = controlador.login("admin", "1234");
	 *
	 * // Assert
	 * assertTrue(resultado,
	 * "Login with correct credentials should return true");
	 * }
	 */

	@Test
	void testConstructorsNoThrow() {
		// Creating a Customer with valid data should not throw any exception
		assertDoesNotThrow(new Executable() {
			@Override
			public void execute() throws Throwable {
				new Customer(1, "Ana", "Lopez", 600123456, "12345678X");
			}
		}, "Customer constructor with valid data should not throw any exception");

		// Creating a Room with valid data should not throw any exception
		assertDoesNotThrow(new Executable() {
			@Override
			public void execute() throws Throwable {
				new Room(2, 101, "Suite", "Available", 300.0, 2);
			}
		}, "Room constructor with valid data should not throw any exception");

		// Creating a Booking with consistent dates should not throw any exception
		assertDoesNotThrow(new Executable() {
			@Override
			public void execute() throws Throwable {
				new Booking(1, 1, 1, LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 10), true, new ArrayList<>());
			}
		}, "Booking constructor with valid data should not throw any exception");
	}
}
