public class Car {

    private String model;
    private int price;
    private Dealer dealer;

    public Car(String model, int price, Dealer dealer) {
        this.model = model;
        this.price = price;
        this.dealer = dealer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", dealer=" + dealer +
                '}';
    }
}
