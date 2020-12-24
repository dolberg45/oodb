package model;

import annotations.*;

import java.util.List;

@Entity
public class Client extends Human {

    @Column
    private int balance;
    @OneToOne
    private Agent agent;
    @OneToMany
    private List<Purchase> purchases;

    @ManyToMany//У одного клиента может быть множество разных дилерских центров.
    private List<Dealer> dealers;

    public Client(String name, String surname, int age, String phoneNumber, String email, int balance, Agent agent, List<Dealer> dealers) {
        super(name, surname, age, phoneNumber, email);
        this.balance = balance;
        this.agent = agent;
        this.dealers = dealers;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(List<Dealer> dealers) {
        this.dealers = dealers;
    }
}
