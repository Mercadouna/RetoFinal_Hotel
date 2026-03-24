package modelo;
public class Aux_booking {
	private Customer customer;
	private Room room;
	private Booking booking;
	public Aux_booking(Customer customer, Room room, Booking booking) {
		this.customer=customer;
		this.room=room;
		this.booking=booking;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	@Override
	public String toString() {
		return "Aux_booking [customer=" + customer + ", room=" + room + ", booking=" + booking + "]";
	}
}
