package model.classes;

import annotations.Column;
import annotations.Entity;
import annotations.ManyToOne;
import model.IBaseEntity;

@Entity
public class Client implements IBaseEntity {

    @Column
    private Long id;

    @Column
    private int balance;

    @Column
    @ManyToOne
    private Agent agent;

    public Client(Long id, int balance, Agent agent) {
        this.id = id;
        this.balance = balance;
        this.agent = agent;
    }

    public Client() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", balance=" + balance +
                ", agent=" + agent +
                '}';
    }
}
