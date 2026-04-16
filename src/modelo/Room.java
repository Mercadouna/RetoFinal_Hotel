//Javadock: Hodei
package modelo;

/**
 * Represents a hotel room.
 * Stores the room's unique identifier, room number, type, current status,
 * and price per night.
 */
public class Room {

    /** Unique identifier for this room. */
    private int idRoom;

    /** Physical room number displayed to guests. */
    private int roomNumber;

    /** Type or category of the room (e.g., single, double, suite). */
    private String typeRoom;

    /** Current status of the room (e.g., available, occupied, maintenance). */
    private String statusRoom;

    /** Nightly rate charged for this room. */
    private double pricePerNight;

    /**
     * Default constructor. Creates an empty Room with no field values set.
     */
    public Room() {
    }

    /**
     * Full constructor. Creates a Room with all fields initialized.
     *
     * @param idRoom        the unique identifier of the room
     * @param roomNumber    the physical room number
     * @param typeRoom      the type or category of the room
     * @param statusRoom    the current status of the room
     * @param pricePerNight the nightly rate for the room
     * @param quantPers     the maximum number of guests the room can accommodate
     */
    public Room(int idRoom, int roomNumber, String typeRoom, String statusRoom, double pricePerNight, int quantPers) {
        this.idRoom = idRoom;
        this.roomNumber = roomNumber;
        this.typeRoom = typeRoom;
        this.statusRoom = statusRoom;
        this.pricePerNight = pricePerNight;
    }

    /**
     * Returns the unique identifier of this room.
     *
     * @return the room ID
     */
    public int getIdRoom() {
        return idRoom;
    }

    /**
     * Sets the unique identifier of this room.
     *
     * @param idRoom the room ID to set
     */
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    /**
     * Returns the physical room number of this room.
     *
     * @return the room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the physical room number of this room.
     *
     * @param roomNumber the room number to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Returns the type or category of this room.
     *
     * @return the room type
     */
    public String getTypeRoom() {
        return typeRoom;
    }

    /**
     * Sets the type or category of this room.
     *
     * @param typeRoom the room type to set
     */
    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    /**
     * Returns the current status of this room.
     *
     * @return the room status
     */
    public String getStatusRoom() {
        return statusRoom;
    }

    /**
     * Sets the current status of this room.
     *
     * @param statusRoom the room status to set
     */
    public void setStatusRoom(String statusRoom) {
        this.statusRoom = statusRoom;
    }

    /**
     * Returns the nightly rate for this room.
     *
     * @return the price per night
     */
    public double getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Sets the nightly rate for this room.
     *
     * @param pricePerNight the price per night to set
     */
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    /**
     * Returns a string representation of this room, including all field values.
     *
     * @return a formatted string with the room's data
     */
    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + idRoom +
                ", roomNumber=" + roomNumber +
                ", typeRoom='" + typeRoom + '\'' +
                ", statusRoom='" + statusRoom + '\'' +
                ", pricePerNight=" + pricePerNight +
                '}';
    }
}
