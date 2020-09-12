import java.util.Date;

public class Purchase {
    private String purchaseId;
    private Date purchaseDate;
    private Dealer dealer;
    private Car car;
    private Client client;

    public Purchase(String purchaseId, Date purchaseDate, Dealer dealer, Car car, Client client) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.dealer = dealer;
        this.car = car;
        if (client.getBalance() >= car.getPrice()) {
            this.client = client;
        }
    }

    public Purchase(){}

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
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
                "purchaseId='" + purchaseId + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", dealer=" + dealer +
                ", car=" + car +
                ", client=" + client +
                '}';
    }
}
