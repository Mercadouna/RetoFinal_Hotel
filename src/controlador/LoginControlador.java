package controlador;

import vista.V_1;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.AdminDAO;
import modelo.ImplementacionBD;
import modelo.Room;

public class LoginControlador {
	AdminDAO dao = new ImplementacionBD();

	public void visualizarPantalla() {
		V_1 ven = new V_1(this);
		ven.setVisible(true);
	}

	public boolean addCostumer(String name, String surname, int phone, String dni) {
		return dao.addCostumer(name, surname, phone, dni);
	}

	public boolean editCostumer(int id, String name, String surname, int phone, String dni) {
		return dao.editCostumer(id, name, surname, phone, dni);
	}

	public boolean deleteCostumer(int id) {
		return dao.deleteCostumer(id);
	}

	public boolean createBooking(int id_room, int id_customer, LocalDate check_in, LocalDate check_out, boolean paid) {
		return dao.createBooking(id_room, id_customer, check_in, check_out, paid);
	}

	public boolean editBooking() {
		return dao.editBooking();
	}

	public boolean deleteBooking() {
		return dao.deleteBooking();
	}

	public boolean addExtraService(String name_service, double price) {
		return dao.addExtraService(name_service, price);
	}

	public boolean deleteExtraService() {
		return dao.deleteExtraService();
	}

	public boolean checkPhone(int phone) {
		return dao.checkPhone(phone);
	}

	public boolean checkPhone(int phone, int excludeId) {
		// Checks if phone exists for any customer other than excludeId
		return ((ImplementacionBD) dao).checkPhone(phone, excludeId);
	}

	public boolean checkDni(String dni) {
		return dao.checkDni(dni);
	}

	public boolean checkDni(String dni, int excludeId) {
		// Checks if DNI exists for any customer other than excludeId
		return ((ImplementacionBD) dao).checkDni(dni, excludeId);
	}

	public ArrayList<Room> viewRooms() {
		return dao.viewRooms();
	}

	public boolean isRoomAvailable(int roomNumber, LocalDate checkIn, LocalDate checkOut) {
		return dao.isRoomAvailable(roomNumber, checkIn, checkOut);
	}

	public boolean isCustomerAvailable(int idCustomer, LocalDate checkIn, LocalDate checkOut) {
		return dao.isCustomerAvailable(idCustomer, checkIn, checkOut);
	}

	public boolean checkRoomExists(int roomNumber) {
		return dao.checkRoomExists(roomNumber);
	}

	public boolean checkCustomerExists(int idCustomer) {
		return dao.checkCustomerExists(idCustomer);
	}

	public boolean login(String user, String password) {
		return dao.login(user, password);
	}

	public boolean deleteBooking(int id) {
		return dao.deleteBooking(id);
	}

	public boolean checkBookingExists(int id) {
		return dao.checkBookingExists(id);
	}
}
