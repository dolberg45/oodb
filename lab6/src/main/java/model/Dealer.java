package model;

import annotations.Column;
import annotations.Entity;
import annotations.ManyToMany;
import annotations.OneToMany;

import java.util.List;

@Entity
public class Dealer {

    @Column(name = "id")
    private String dealerName;

    @Column
    @OneToMany //У одного дилера может быть множество агентов.
    private List<Agent> agents;

    @Column
    @OneToMany//У одного дилера может быть множество автомобилей.
    private List<Car> cars;

    @Column
    @ManyToMany//У одного дилера может быть множество клиентов.
    private List<Client> clients;

    @Column
    @OneToMany//У одного дилера может быть множество транзакций.
    private List<Purchase> purchases;

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addToCars(Car car) {
        this.cars.add(car);
    }

    public void addToAgents(Agent agent) {
        this.agents.add(agent);
    }

    public void addToClients(Client client) {
        this.clients.add(client);
    }

    public void addPurchases(Purchase purchase) {
        this.purchases.add(purchase);
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "dealerName='" + dealerName + '\'' +
                ", agents=" + agents +
                ", cars=" + cars +
                ", clients=" + clients +
                ", purchases=" + purchases +
                '}';
    }
}
