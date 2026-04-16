package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Booking {
    private int idBooking;
    private int idRoom;
    private int idCustomer;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean paid;
    private ArrayList<ExtraService> extraServices;
 
    public Booking() {
    }

    public Booking(int idBooking, int idRoom, int idCustomer, LocalDate checkIn, LocalDate checkOut, boolean paid,
            ArrayList<ExtraService> extraServices) {
        this.idBooking = idBooking;
        this.idRoom = idRoom;
        this.idCustomer = idCustomer;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.paid = paid;
        this.extraServices = extraServices;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public ArrayList<ExtraService> getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(ArrayList<ExtraService> extraServices) {
        this.extraServices = extraServices;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "idBooking=" + idBooking +
                ", idRoom=" + idRoom +
                ", idCustomer=" + idCustomer +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", paid=" + paid +
                ", extraServices=" + extraServices +
                '}';
    }
}
