package model.classes;

import annotations.Column;
import annotations.Entity;
import annotations.ManyToOne;
import annotations.OneToOne;
import model.IBaseEntity;

import java.util.Date;

@Entity
public class Purchase implements IBaseEntity {

    @Column
    private Long id;

    @Column
    private Date date;

    @Column
    @OneToOne
    private Car car;

    @Column()
    @ManyToOne
    private Client client;

    @Column
    @ManyToOne
    private Dealer dealer;

    public Purchase() {}

    public Purchase(Long id, Date date, Car car, Client client, Dealer dealer) {
        this.id = id;
        this.date = date;
        this.car = car;
        this.client = client;
        this.dealer = dealer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", date=" + date +
                ", car=" + car +
                ", client=" + client +
                ", dealer=" + dealer +
                '}';
    }
}
