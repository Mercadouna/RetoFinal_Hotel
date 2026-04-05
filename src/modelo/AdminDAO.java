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

	public boolean isCustomerAvailable(int idCustomer, LocalDate checkIn, LocalDate checkOut);

	public boolean checkRoomExists(int roomNumber);

	public boolean checkCustomerExists(int idCustomer);

	public boolean checkPhone(int phone, int excludeId);

	public boolean checkDni(String dni, int excludeId);

	public boolean login(String user, String password);

	public boolean deleteBooking(int id);

	public boolean checkBookingExists(int id);

	public ArrayList<Aux_booking> viewBookings();

	public ArrayList<Customer> viewCustomers();

	public ArrayList<ExtraService> viewBookingExtraServices(int idBooking);

	public boolean addExtraServiceToBooking(int idBooking, int idService);

	public boolean checkExtraServiceExists(int idService);
}
