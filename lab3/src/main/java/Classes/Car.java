package Classes;

public class Car {

    private String model;
    private int price;

    public Car(String model, int price, Dealer dealer) {
        this.model = model;
        this.price = price;
    }

    public Car() {
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

}
