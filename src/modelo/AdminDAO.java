package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface AdminDAO {
	public boolean addCostumer(String name, String surname, int phone, String dni);

	public boolean deleteCostumer(int id);

	public boolean createBooking(int id_room, int id_customer, LocalDate check_in, LocalDate check_out, boolean paid);

	public boolean editBooking();

	public boolean deleteBooking();

	public boolean deleteExtraService();

	public boolean checkPhone(int phone);

	public boolean checkDni(String dni);

	public boolean addExtraService(String name_service, double price);

	public ArrayList<Room> viewRooms();

	public boolean editCostumer(int id, String name, String surname, int phone, String dni);

	public boolean isRoomAvailable(int roomNumber, LocalDate checkIn, LocalDate checkOut);
}
