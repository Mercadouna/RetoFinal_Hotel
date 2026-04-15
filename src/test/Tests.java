package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import modelo.Booking;
import modelo.Customer;
import modelo.ExtraService;
import modelo.Room;

class Tests {

	@Test
	void testCustomerModel() {
		// Constructor vacío: los campos String deben ser null
		Customer empty = new Customer();
		assertNull(empty.getNameCostumer(), "El constructor vacío no asigna nombre, debe ser null");
		assertNull(empty.getDni(), "El constructor vacío no asigna DNI, debe ser null");

		// Constructor completo: los campos se asignan correctamente
		Customer c = new Customer(1, "Ana", "Lopez", 600123456, "12345678X");
		assertEquals(1, c.getIdCustomer(), "El ID del cliente debe ser 1");
		assertEquals("Ana", c.getNameCostumer(), "El nombre del cliente debe ser Ana");
		assertEquals("12345678X", c.getDni(), "El DNI del cliente debe coincidir con el pasado al constructor");

		// toString debe contener el nombre del cliente
		assertTrue(c.toString().contains("Ana"), "toString() debe contener el nombre del cliente");
	}

	@Test
	void testRoomModel() {
		// Constructor vacío: el tipo de habitación debe ser null
		Room empty = new Room();
		assertNull(empty.getTypeRoom(), "El constructor vacío no asigna tipo de habitación, debe ser null");

		// Constructor completo: los campos se asignan correctamente
		Room r = new Room(2, 101, "Suite", "Available", 300.0, 2);
		assertEquals(101, r.getRoomNumber(), "El número de habitación debe ser 101");
		assertEquals("Suite", r.getTypeRoom(), "El tipo de habitación debe ser Suite");

		// El precio por noche debe ser positivo
		assertTrue(r.getPricePerNight() > 0, "El precio por noche debe ser positivo");
	}

	@Test
	void testBookingModel() {
		// Constructor vacío: las fechas (LocalDate) deben ser null
		Booking empty = new Booking();
		assertNull(empty.getCheckIn(), "El constructor vacío no asigna fecha de entrada, debe ser null");
		assertNull(empty.getCheckOut(), "El constructor vacío no asigna fecha de salida, debe ser null");

		// El campo paid funciona en ambos estados
		Booking paid = new Booking(1, 1, 1, LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 10), true,
				new ArrayList<>());
		assertTrue(paid.isPaid(), "Una reserva marcada como pagada debe devolver true en isPaid()");

		Booking unpaid = new Booking(2, 1, 1, LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 5), false,
				new ArrayList<>());
		assertFalse(unpaid.isPaid(), "Una reserva marcada como no pagada debe devolver false en isPaid()");

		// Regla de negocio: la fecha de entrada debe ser anterior a la de salida
		// (validación usada en addBooking)
		assertTrue(paid.getCheckIn().isBefore(paid.getCheckOut()),
				"La fecha de entrada debe ser anterior a la de salida");
	}

	@Test
	void testExtraServiceModel() {
		// Constructor vacío: el nombre del servicio debe ser null
		ExtraService empty = new ExtraService();
		assertNull(empty.getNameService(), "El constructor vacío no asigna nombre de servicio, debe ser null");

		// Constructor completo: los campos se asignan correctamente
		ExtraService es = new ExtraService(3, "Breakfast", 15.50, 10);
		assertEquals("Breakfast", es.getNameService(), "El nombre del servicio debe ser Breakfast");
		assertEquals(15.50, es.getPrice(), "El precio del servicio debe ser 15.50");

		// El precio de un servicio extra debe ser positivo
		assertTrue(es.getPrice() > 0, "El precio de un servicio extra debe ser positivo");
	}

	@Test
	void testConstructorsNoThrow() {
		// Crear un Customer con datos válidos no debe lanzar ninguna excepción 
		assertDoesNotThrow(new Executable() {
			@Override
			public void execute() throws Throwable {
				new Customer(1, "Ana", "Lopez", 600123456, "12345678X");
			}
		}, "El constructor de Customer con datos válidos no debe lanzar ninguna excepción");

		// Crear un Room con datos válidos no debe lanzar ninguna excepción
		assertDoesNotThrow(new Executable() {
			@Override
			public void execute() throws Throwable {
				new Room(2, 101, "Suite", "Available", 300.0, 2);
			}
		}, "El constructor de Room con datos válidos no debe lanzar ninguna excepción");

		// Crear un Booking con fechas coherentes no debe lanzar ninguna excepción
		assertDoesNotThrow(new Executable() {
			@Override
			public void execute() throws Throwable {
				new Booking(1, 1, 1, LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 10), true, new ArrayList<>());
			}
		}, "El constructor de Booking con datos válidos no debe lanzar ninguna excepción");
	}
}
