package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface AdminDAO {
	public boolean addCostumer(String name, String surname, int phone, String dni);
	public boolean editCostumer();
	public boolean deleteCostumer();
	public boolean createBooking();
	public boolean editBooking();
	public boolean deleteBooking();
	public boolean addExtraService();
	public boolean deleteExtraService();
	public boolean checkPhone(int phone);
	public boolean checkDni(String dni);
	
	
}
