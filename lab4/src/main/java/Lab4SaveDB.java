import Classes.Purchase;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Lab4SaveDB {

    public static void savePurchaseList(List<Purchase> purchases, Connection connection) throws SQLException {

        if (purchases != null && purchases.size() > 0) {
            Gson gson = new Gson();

            String purchasesAsJson = gson.toJson(purchases);

            PreparedStatement statement = connection.prepareStatement("insert into jsonTest (id, exampleJson, exampleJsonB) values ( 1, cast( ? as json) , cast( ? as json) )");
            statement.setString(1, purchasesAsJson);
            statement.setString(2, purchasesAsJson);

            int count = statement.executeUpdate();
//
//            System.out.println(count + " records added!");

            statement.close();

        }
    }
}
