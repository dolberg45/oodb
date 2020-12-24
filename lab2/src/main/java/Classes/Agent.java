package Classes;

import java.util.List;

public class Agent {

    private String agentId;
    private Client client;

    public Agent(String agentId, Client client) {
        this.agentId = agentId;
        this.client = client;
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

}
