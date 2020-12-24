package orm.operations;

import model.*;
import model.classes.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {

    private final Connection connection;
    private final Insert insert;

    public Select(Connection connection) {
        this.connection = connection;
        insert = new Insert(connection);
    }

    public IBaseEntity selectById(Class<?> object, Object var){
        if (var instanceof Integer){
            Integer id = (Integer) var;
            String sql = createSelectQuery(object);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet set = preparedStatement.executeQuery();
                if (!set.next()){
                    return null;
                }
                preparedStatement.close();
                if (object.equals(Client.class)) {
                    Client client = new Client();
                    client.setId((long) set.getInt(1));
                    client.setBalance(set.getInt(2));
                    client.setAgent((Agent) selectById(Agent.class, set.getInt(3)));
                    return client;
                }
                else if (object.equals(Agent.class)){
                    Agent agent = new Agent();
                    agent.setId((long) set.getInt(1));
                    agent.setClient((Client) selectById(Client.class, set.getInt(2)));
                    agent.setDealer((Dealer) selectById(Dealer.class, set.getInt(3)));
                    return agent;
                }
                else if (object.equals(Car.class)){
                    Car car = new Car();
                    car.setId((long) set.getInt(1));
                    car.setModel(set.getString(2));
                    car.setPrice(set.getInt(3));
                    car.setDealer((Dealer) selectById(Dealer.class, set.getInt(4)));
                    car.setPurchase((Purchase) selectById(Purchase.class, set.getInt(5)));
                    return car;
                }
                else if (object.equals(Dealer.class)){
                    Dealer dealer = new Dealer();
                    dealer.setId((long) set.getInt(1));
                    dealer.setName(set.getString(2));
                    return dealer;
                }
                else if (object.equals(Purchase.class)){
                    Purchase purchase = new Purchase();
                    purchase.setId((long) set.getInt(1));
                    purchase.setDate(set.getDate(2));
                    purchase.setCar((Car) selectById(Car.class, 3));
                    purchase.setClient((Client) selectById(Client.class, 4));
                    purchase.setDealer((Dealer) selectById(Dealer.class, 5));
                    return purchase;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public String createSelectQuery(Class<?> object) {
        String tableName = object.getSimpleName().toLowerCase();
        return "Select * from " + tableName + " where id = ?";
    }


}
