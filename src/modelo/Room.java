package modelo;

public class Room {
    private int idRoom;
    private int roomNumber;
    private String typeRoom;
    private String statusRoom;
    private double pricePerNight;
    private int quantPers;

    public Room() {
    }

    public Room(int idRoom, int roomNumber, String typeRoom, String statusRoom, double pricePerNight, int quantPers) {
        this.idRoom = idRoom;
        this.roomNumber = roomNumber;
        this.typeRoom = typeRoom;
        this.statusRoom = statusRoom;
        this.pricePerNight = pricePerNight;
        this.quantPers = quantPers;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public String getStatusRoom() {
        return statusRoom;
    }

    public void setStatusRoom(String statusRoom) {
        this.statusRoom = statusRoom;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getQuantPers() {
        return quantPers;
    }

    public void setQuantPers(int quantPers) {
        this.quantPers = quantPers;
    }

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + idRoom +
                ", roomNumber=" + roomNumber +
                ", typeRoom='" + typeRoom + '\'' +
                ", statusRoom='" + statusRoom + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", quantPers=" + quantPers +
                '}';
    }
}
