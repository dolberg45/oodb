package Classes;

import java.util.Date;

public class Purchase {
    private int purchaseId;
    private Date purchaseDate;
    private Car car;
    private Client client;

    public Purchase(int purchaseId, Date purchaseDate, Car car, Client client) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.car = car;
        this.client = client;
    }

    public Purchase(){}

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", purchaseDate=" + purchaseDate +
                ", car=" + car.getModel() +
                ", client=" + client.getName() +
                '}';
    }
}
