import java.util.List;

public class Dealer {

    private String dealerName;
    private List<Agent> agents;
    private List<Car> cars;
    private List<Client> clients;
    private List<Purchase> purchases;

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String bankName) {
        this.dealerName = bankName;
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
