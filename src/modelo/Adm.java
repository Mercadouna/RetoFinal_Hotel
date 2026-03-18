package modelo;

public class Adm {
    private int idAdmin;
    private String nameA;
    private String passwordA;

    public Adm() {
    }

    public Adm(int idAdmin, String nameA, String passwordA) {
        this.idAdmin = idAdmin;
        this.nameA = nameA;
        this.passwordA = passwordA;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public String getPasswordA() {
        return passwordA;
    }

    public void setPasswordA(String passwordA) {
        this.passwordA = passwordA;
    }

    @Override
    public String toString() {
        return "Adm{" +
                "idAdmin=" + idAdmin +
                ", nameA='" + nameA + '\'' +
                ", passwordA='" + passwordA + '\'' +
                '}';
    }
}
