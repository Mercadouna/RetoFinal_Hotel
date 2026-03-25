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

	public boolean editCostumer() {
		return dao.editCostumer();
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

	public boolean checkDni(String dni) {
		return dao.checkDni(dni);
	}

	public ArrayList<Room> viewRooms() {
		return dao.viewRooms();
	}

}
