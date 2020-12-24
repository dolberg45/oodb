package Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnect {

    private static final String url = "jdbc:postgresql://localhost:5432/lab7";
    private static final String user = "postgres";
    private static final String password = "458900";

    private static Connection connection;

    public static Connection getConnection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7","postgres", "458900");
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database!");
            }
        }
        return connection;
    }

    public static List<String> getColumns(String columnName){
        List<String> columns = new ArrayList<>();
        connection = DbConnect.getConnection();
        String sql = "SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, columnName);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                columns.add(set.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error!");
        }
        return columns;
    }


    public static List<String> getTables(){
        List<String> tables = new ArrayList<>();
        connection = DbConnect.getConnection();
        String string = "SELECT * \n" +
                "FROM information_schema.tables \n" +
                "WHERE table_type = 'BASE TABLE' \n" +
                "    AND table_schema = 'public' \n" +
                "ORDER BY table_type, table_name";
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(string);
            while (set.next()){
                tables.add(set.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return tables;
    }


    public static List<String> printDatabase(){
        List<String> allColumns = new ArrayList<>();
//        System.out.println("------------------------------");
        for (String table : getTables()){
//            System.out.println(table + ": ");
            for (String column : getColumns(table)){
//                System.out.println(column);
                allColumns.add(column);
            }
//            System.out.println("------------------------------");
        }
        return allColumns;
    }


}
