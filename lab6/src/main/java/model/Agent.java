package model;

import annotations.Column;
import annotations.Entity;
import annotations.ManyToOne;
import annotations.OneToOne;

@Entity
public class Agent {

    @Column
    private String agentId;

    @Column
    @OneToOne
    private Client client;

    @ManyToOne //У одного агента может быть только один дилер, на которого он работает.
    private Dealer dealer;

    public Agent(String agentId, Client client, Dealer dealer) {
        this.agentId = agentId;
        this.client = client;
        this.dealer = dealer;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
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
}
