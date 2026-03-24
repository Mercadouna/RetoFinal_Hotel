package controlador;

import vista.V_1;
import modelo.AdminDAO;
import modelo.ImplementacionBD;

public class LoginControlador {
	AdminDAO dao = new ImplementacionBD();

	public void visualizarPantalla() {
		V_1 ven = new V_1(this);
		ven.setVisible(true);
	}

	public boolean addCostumer() {
		return dao.addCostumer();
	}

	public boolean editCostumer() {
		return dao.editCostumer();
	}

	public boolean deleteCostumer() {
		return dao.deleteCostumer();
	}

	public boolean createBooking() {
		return dao.createBooking();
	}

	public boolean editBooking() {
		return dao.editBooking();
	}

	public boolean deleteBooking() {
		return dao.deleteBooking();
	}

	public boolean addExtraService() {
		return dao.addExtraService();
	}

	public boolean deleteExtraService() {
		return dao.deleteExtraService();
	}

	public boolean checkPhone(int phone) {
		return dao.checkPhone(phone);
	}

}
