//Javadock: Lorea

package modelo;

/**
 * Represents a hotel customer.
 * Stores the customer's unique identifier, first name, surname,
 * phone number, and national ID (DNI).
 */
public class Customer {

    /** Unique identifier for this customer. */
    private int idCustomer;

    /** First name of the customer. */
    private String nameCostumer;

    /** Surname of the customer. */
    private String surname;

    /** Phone number of the customer. */
    private int phone;

    /** National ID (DNI) of the customer. */
    private String dni;

    /**
     * Default constructor. Creates an empty Customer with no field values set.
     */
    public Customer() {
    }

    /**
     * Full constructor. Creates a Customer with all fields initialized.
     *
     * @param idCustomer   the unique identifier of the customer
     * @param nameCostumer the first name of the customer
     * @param surname      the surname of the customer
     * @param phone        the phone number of the customer
     * @param dni          the national ID (DNI) of the customer
     */
    public Customer(int idCustomer, String nameCostumer, String surname, int phone, String dni) {
        this.idCustomer = idCustomer;
        this.nameCostumer = nameCostumer;
        this.surname = surname;
        this.phone = phone;
        this.dni = dni;
    }

    /**
     * Returns the unique identifier of this customer.
     *
     * @return the customer ID
     */
    public int getIdCustomer() {
        return idCustomer;
    }

    /**
     * Sets the unique identifier of this customer.
     *
     * @param idCustomer the customer ID to set
     */
    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     * Returns the first name of this customer.
     *
     * @return the customer's first name
     */
    public String getNameCostumer() {
        return nameCostumer;
    }

    /**
     * Sets the first name of this customer.
     *
     * @param nameCostumer the first name to set
     */
    public void setNameCostumer(String nameCostumer) {
        this.nameCostumer = nameCostumer;
    }

    /**
     * Returns the surname of this customer.
     *
     * @return the customer's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of this customer.
     *
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the phone number of this customer.
     *
     * @return the customer's phone number
     */
    public int getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of this customer.
     *
     * @param phone the phone number to set
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * Returns the national ID (DNI) of this customer.
     *
     * @return the customer's DNI
     */
    public String getDni() {
        return dni;
    }

    /**
     * Sets the national ID (DNI) of this customer.
     *
     * @param dni the DNI to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Returns a string representation of this customer, including all field values.
     *
     * @return a formatted string with the customer's data
     */
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
