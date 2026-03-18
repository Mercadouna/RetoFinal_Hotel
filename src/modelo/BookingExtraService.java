package modelo;

import java.time.LocalDate;

public class BookingExtraService {
    private int idBooking;
    private int idService;
    private int quantity;
    private LocalDate dateBooking;

    public BookingExtraService() {
    }

    public BookingExtraService(int idBooking, int idService, int quantity, LocalDate dateBooking) {
        this.idBooking = idBooking;
        this.idService = idService;
        this.quantity = quantity;
        this.dateBooking = dateBooking;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(LocalDate dateBooking) {
        this.dateBooking = dateBooking;
    }

    @Override
    public String toString() {
        return "BookingExtraService{" +
                "idBooking=" + idBooking +
                ", idService=" + idService +
                ", quantity=" + quantity +
                ", dateBooking=" + dateBooking +
                '}';
    }
}
