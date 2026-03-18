package modelo;

public class Customer {
    private int idCustomer;
    private String nameCostumer;
    private String surname;
    private int phone;
    private String dni;

    public Customer() {
    }

    public Customer(int idCustomer, String nameCostumer, String surname, int phone, String dni) {
        this.idCustomer = idCustomer;
        this.nameCostumer = nameCostumer;
        this.surname = surname;
        this.phone = phone;
        this.dni = dni;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCostumer() {
        return nameCostumer;
    }

    public void setNameCostumer(String nameCostumer) {
        this.nameCostumer = nameCostumer;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", nameCostumer='" + nameCostumer + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + phone +
                ", dni='" + dni + '\'' +
                '}';
    }
}
