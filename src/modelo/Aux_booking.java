package modelo;


public class Aux_booking {
	private Customer idCustomer;
	private Customer nameCostumer;
	private Customer surname;
	private Room roomNumber;
	private Room typeRoom;
	private Booking checkIn;
	private Booking checkOut;
	private Booking paid;

	public Aux_booking(Customer idCustomer, Customer nameCostumer, Customer surname, Room roomNumber, Room typeRoom, Booking checkIn, Booking checkOut, Booking paid) {
		this.idCustomer=idCustomer;
		this.nameCostumer=nameCostumer;
		this.surname=surname;
		this.roomNumber=roomNumber;
		this.typeRoom=typeRoom;
		this.checkIn=checkIn;
		this.checkOut=checkOut;
		this.paid=paid;
	}

	public Customer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Customer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Customer getNameCostumer() {
		return nameCostumer;
	}

	public void setNameCostumer(Customer nameCostumer) {
		this.nameCostumer = nameCostumer;
	}

	public Customer getSurname() {
		return surname;
	}

	public void setSurname(Customer surname) {
		this.surname = surname;
	}

	public Room getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Room roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Room getTypeRoom() {
		return typeRoom;
	}

	public void setTypeRoom(Room typeRoom) {
		this.typeRoom = typeRoom;
	}

	public Booking getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Booking checkIn) {
		this.checkIn = checkIn;
	}

	public Booking getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Booking checkOut) {
		this.checkOut = checkOut;
	}

	public Booking getPaid() {
		return paid;
	}

	public void setPaid(Booking paid) {
		this.paid = paid;
	}

	@Override
	public String toString() {
		return "Aux_booking [idCustomer=" + idCustomer + ", nameCostumer=" + nameCostumer + ", surname=" + surname
				+ ", roomNumber=" + roomNumber + ", typeRoom=" + typeRoom + ", checkIn=" + checkIn + ", checkOut="
				+ checkOut + ", paid=" + paid + "]";
	}
	
	
}
