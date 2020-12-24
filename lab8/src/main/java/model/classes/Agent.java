package model.classes;

import annotations.Column;
import annotations.Entity;
import annotations.ManyToOne;
import annotations.OneToOne;
import model.IBaseEntity;

@Entity
public class Agent implements IBaseEntity {

    @Column
    private Long id;

    @Column
    @OneToOne
    private Client client;

    @Column
    @ManyToOne //У одного агента может быть только один дилер, на которого он работает.
    private Dealer dealer;

    public Agent() { }

    public Agent(Long id, Client client, Dealer dealer) {
        this.id = id;
        this.client = client;
        this.dealer = dealer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "Agent{" +
                "id=" + id +
                ", client=" + client +
                ", dealer=" + dealer +
                '}';
    }
}
