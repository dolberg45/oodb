package model.classes;

import annotations.Column;
import annotations.Entity;
import annotations.ManyToOne;
import annotations.OneToOne;
import model.IBaseEntity;

@Entity
public class Car implements IBaseEntity {

    @Column
    private Long id;

    @Column
    private String model;

    @Column
    private int price;

    @Column
    @ManyToOne//У множества есть один дилер.
    private Dealer dealer;

    @Column
    @OneToOne
    private Purchase purchase;

    public Car() {}

    public Car(Long id, String model, int price, Dealer dealer, Purchase purchase) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.dealer = dealer;
        this.purchase = purchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", dealer=" + dealer +
                ", purchase=" + purchase +
                '}';
    }
}
