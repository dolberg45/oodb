package model;

import annotations.*;

@Entity
public class Agent {

    @Column
    private Long id;

    @Column
    @OneToOne
    private Client client;

    @Column
    @ManyToOne //У одного агента может быть только один дилер, на которого он работает.
    private Dealer dealer;

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
}
