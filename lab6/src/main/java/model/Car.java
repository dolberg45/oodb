package model;

import annotations.Column;
import annotations.Entity;
import annotations.ManyToOne;
import annotations.OneToOne;

@Entity
public class Car {

    @Column
    private String model;

    @Column
    private int price;

    @ManyToOne//У одного авто может быть только один дилер, на которого он работает.
    private Dealer dealer;

    @OneToOne
    private Purchase purchase;

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
                '}';
    }
}
