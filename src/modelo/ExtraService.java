package modelo;

public class ExtraService {
    private int idService;
    private String nameService;
    private double price;
    private int quantity;

    public ExtraService() {
    }

    public ExtraService(int idService, String nameService, double price, int quantity) {
        this.idService = idService;
        this.nameService = nameService;
        this.price = price;
        this.quantity=quantity;
        
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ExtraService [idService=" + idService + ", nameService=" + nameService + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

	
}
