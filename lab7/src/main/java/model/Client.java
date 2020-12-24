package model;

import annotations.*;

import java.util.List;

@Entity
public class Client {

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
}
