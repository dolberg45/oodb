package model;

import annotations.*;

import java.util.Date;

@Entity
public class Purchase {

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
