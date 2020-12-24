import Classes.*;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Dealer dealer = new Dealer();
        dealer.setDealerName("TransTexService");


        Client clientOne = new Client("Grigoriy", "Alekseev", 20, "89225150121", "dolberg@gmail.com", 1_000_000);
        Client clientTwo = new Client("Dan", "Bobsly", 20, "89225153221", "bob22@gmail.com", 2_000_000);
        Client clientThree = new Client("Lord", "Bender", 20, "89222150121", "lorde@gmail.com", 33_000_000);

        ArrayList<Client> list = new ArrayList<>();
        list.add(clientOne);
        list.add(clientTwo);
        list.add(clientThree);
        dealer.setClients(list);

        Car carOne = new Car("Tesla Model S", 100_000, dealer);
        Car carTwo = new Car("Tesla Model X", 130_000, dealer);
        Car carThree = new Car("Tesla Roadstar", 250_000, dealer);
        Car carFour = new Car("Tesla Model E", 40_000, dealer);
        Car carFive = new Car("Tesla CyberTrack", 105_000, dealer);

        ArrayList<Car> list1 = new ArrayList<>();
        list1.add(carOne);
        list1.add(carTwo);
        list1.add(carThree);
        list1.add(carFour);
        list1.add(carFive);
        dealer.setCars(list1);

        Agent agentOne = new Agent("1", clientOne);
        Agent agentTwo = new Agent("2", null);
        Agent agentThree = new Agent("3", clientThree);

        ArrayList<Agent> list2 = new ArrayList<>();
        list2.add(agentOne);
        list2.add(agentTwo);
        list2.add(agentThree);
        dealer.setAgents(list2);

        Purchase purchaseOne = new Purchase(1, new Date(), carOne, clientOne);
        Purchase purchaseTwo = new Purchase(2, new Date(), carFour, clientTwo);
        Purchase purchaseThree = new Purchase(3, new Date(), carTwo, clientThree);

        ArrayList<Purchase> list3 = new ArrayList<>();
        list3.add(purchaseOne);
        list3.add(purchaseTwo);
        list3.add(purchaseThree);
        dealer.setPurchases(list3);

        JsonExample jsonExample = new JsonExample();
        File baseFile = new File("/Users/grigoriy/Desktop/OOBD/lab4/src/main/java/user.json");

        //Сортировка по критерию названия авто car.getName();
        List<Purchase> purchasesList = new ArrayList<>();
        purchasesList.add(purchaseOne);
        purchasesList.add(purchaseTwo);
        purchasesList.add(purchaseThree);
        PurchaseComparator comparator = new PurchaseComparator();
        purchasesList.sort(comparator);



        String url = "jdbc:postgresql://localhost/oodb";
        String user = "postgres";
        String password = "458900";


        Gson gson = new Gson();
        String sqlQuery = gson.toJson(purchasesList);
        Connection connection = DriverManager.getConnection(url, user, password);

        long startTimeJson = System.currentTimeMillis();
        insert(connection, "json", "jsonexample", sqlQuery);
        long finishedTimeJson = System.currentTimeMillis();
        long timeJson = finishedTimeJson - startTimeJson;
        System.out.println("Прошло времени для добавления json: " +  timeJson+"\n");

        long startTimeJsonb = System.currentTimeMillis();
        insert(connection, "jsonb", "jsonbexample", sqlQuery);
        long finishedTimeJsonb = System.currentTimeMillis();
        long timeJsonb = finishedTimeJsonb - startTimeJsonb;
        System.out.println("Прошло времени для добавления jsonb: " + timeJsonb);

        System.out.println("-----------");

        sqlQuery = gson.toJson(list2);

        long startTimeJsonUpdate = System.currentTimeMillis();
        update(connection, "json", "jsonexample", sqlQuery);
        long finishedTimeJsonUpdate = System.currentTimeMillis();
        long timeJsonUpdate = finishedTimeJsonUpdate - startTimeJsonUpdate;
        System.out.println("Прошло времени для обновления json: " + timeJsonUpdate+"\n");

        long startTimeJsonbUpdate = System.currentTimeMillis();
        update(connection, "jsonb", "jsonbexample", sqlQuery);
        long finishedTimeJsonbUpdate = System.currentTimeMillis();
        long timeJsonbUpdate = finishedTimeJsonbUpdate - startTimeJsonbUpdate;
        System.out.println("Прошло времени для обновления jsonb: " + timeJsonbUpdate);

        System.out.println("-------");

        long startTimeJsonSelect = System.currentTimeMillis();
        select(connection, "json");
        long finishedTimeJsonSelect = System.currentTimeMillis();
        long timeJsonSelect = finishedTimeJsonSelect - startTimeJsonSelect;
        System.out.println("Прошло времени для отображения json: " + timeJsonSelect+"\n");

        long startTimeJsonbSelect = System.currentTimeMillis();
        select(connection, "jsonb");
        long finishedTimeJsonbSelect = System.currentTimeMillis();
        long timeJsonbSelect = finishedTimeJsonbSelect - startTimeJsonbSelect;
        System.out.println("Прошло времени для отображения jsonb: " + timeJsonbSelect);
    }


    public static void insert(Connection connection, String table, String column, String jsonString) throws SQLException {
        String sqlInsert = "insert into "+table+" ("+column+") values (cast (? as json))";
        PreparedStatement statement = connection.prepareStatement(sqlInsert);
        statement.setString(1, jsonString);
        int rows = statement.executeUpdate();
        System.out.printf("%d rows added", rows);
        statement.close();
    }

    public static void retrieve(Connection connection, String tableName) throws SQLException {
        String sqlQuery = "Select * from " + tableName;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.print(resultSet.getInt(1));
            System.out.print(":");
            System.out.println(resultSet.getString(2));
        }
    }

    public static void update(Connection connection, String table, String column, String jsonString) throws SQLException {
        String sqlUpdate = "update " + table + " set " + column + " = (cast (? as json))";
        PreparedStatement statement = connection.prepareStatement(sqlUpdate);
        statement.setObject(1, jsonString);
        int rows = statement.executeUpdate();
        System.out.printf("%d rows update ", rows);
        statement.close();
    }

    public static void select(Connection connection, String tableName) throws SQLException {
        String sqlQuery = "Select * from " + tableName;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
    }
}
