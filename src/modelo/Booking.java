//Javadock: Ivan

package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a hotel booking made by a customer for a specific room.
 * Stores the booking identifier, the associated room and customer IDs,
 * the check-in and check-out dates, the payment status, and any extra
 * services requested for the stay.
 */
public class Booking {

    /** Unique identifier for this booking. */
    private int idBooking;

    /** Identifier of the room reserved in this booking. */
    private int idRoom;

    /** Identifier of the customer who made this booking. */
    private int idCustomer;

    /** Check-in date for the booking. */
    private LocalDate checkIn;

    /** Check-out date for the booking. */
    private LocalDate checkOut;

    /** Whether the booking has been paid. */
    private boolean paid;

    /** List of extra services added to this booking. */
    private ArrayList<ExtraService> extraServices;

    /**
     * Default constructor. Creates an empty Booking with no field values set.
     */
    public Booking() {
    }

    /**
     * Full constructor. Creates a Booking with all fields initialized.
     *
     * @param idBooking     the unique identifier of the booking
     * @param idRoom        the ID of the reserved room
     * @param idCustomer    the ID of the customer who made the booking
     * @param checkIn       the check-in date
     * @param checkOut      the check-out date
     * @param paid          {@code true} if the booking has been paid; {@code false}
     *                      otherwise
     * @param extraServices the list of extra services associated with this booking
     */
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

    /**
     * Returns the unique identifier of this booking.
     *
     * @return the booking ID
     */
    public int getIdBooking() {
        return idBooking;
    }

    /**
     * Sets the unique identifier of this booking.
     *
     * @param idBooking the booking ID to set
     */
    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    /**
     * Returns the ID of the room reserved in this booking.
     *
     * @return the room ID
     */
    public int getIdRoom() {
        return idRoom;
    }

    /**
     * Sets the ID of the room reserved in this booking.
     *
     * @param idRoom the room ID to set
     */
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    /**
     * Returns the ID of the customer who made this booking.
     *
     * @return the customer ID
     */
    public int getIdCustomer() {
        return idCustomer;
    }

    /**
     * Sets the ID of the customer who made this booking.
     *
     * @param idCustomer the customer ID to set
     */
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     * Returns the check-in date of this booking.
     *
     * @return the check-in date
     */
    public LocalDate getCheckIn() {
        return checkIn;
    }

    /**
     * Sets the check-in date of this booking.
     *
     * @param checkIn the check-in date to set
     */
    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Returns the check-out date of this booking.
     *
     * @return the check-out date
     */
    public LocalDate getCheckOut() {
        return checkOut;
    }

    /**
     * Sets the check-out date of this booking.
     *
     * @param checkOut the check-out date to set
     */
    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * Returns whether this booking has been paid.
     *
     * @return {@code true} if the booking is paid; {@code false} otherwise
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets the payment status of this booking.
     *
     * @param paid {@code true} to mark the booking as paid; {@code false} otherwise
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * Returns the list of extra services associated with this booking.
     *
     * @return an {@link ArrayList} of {@link ExtraService} objects
     */
    public ArrayList<ExtraService> getExtraServices() {
        return extraServices;
    }

    /**
     * Sets the list of extra services associated with this booking.
     *
     * @param extraServices the list of extra services to set
     */
    public void setExtraServices(ArrayList<ExtraService> extraServices) {
        this.extraServices = extraServices;
    }

    /**
     * Returns a string representation of this booking, including all field values.
     *
     * @return a formatted string with the booking's data
     */
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
