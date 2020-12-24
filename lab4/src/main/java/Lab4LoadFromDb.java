import Classes.Dealer;
import Classes.Purchase;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Lab4LoadFromDb {

    public static Dealer load() throws IOException {
        Dealer dealer = null;
        String dealerStr = "";
        File file = new File("Dealer.json");

        if (file.exists()) {
            dealerStr = new String(Files.readAllBytes(file.toPath()));
        }

        dealer = new Gson().fromJson(dealerStr, Dealer.class);

        return dealer;
    }

    /**
     * Пример чтения из файла массива JSON объектов
     */
    public static List<Purchase> loadPurchaseList(Connection connection) throws JsonSyntaxException, SQLException {
        String pStr = "";

        PreparedStatement statement = connection.prepareStatement("select exampleJsonB from jsonTest "); //Достаем список столбцов из таблицы jtest

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pStr = resultSet.getString("exampleJsonB");
            System.out.println(pStr);
        }

        statement.close();

        Gson gson = new Gson();

        Purchase[] plst = gson.fromJson(pStr, Purchase[].class);


        statement = connection.prepareStatement("select exampleJsonB->0 as c from jsonTest ");

        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            pStr = resultSet.getString("c");
            System.out.println(pStr);
        }

        return Arrays.asList(plst);
    }
}
