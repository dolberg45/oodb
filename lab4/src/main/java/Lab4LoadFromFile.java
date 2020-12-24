import Classes.Dealer;
import Classes.Purchase;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Lab4LoadFromFile {

    public static Dealer load() throws IOException {
        Dealer dealer = null;
        String dealerStr = "";
        File file = new File("dealer.json");

        if (file.exists()) {
            dealerStr = new String(Files.readAllBytes(file.toPath()));
        }

        dealer = new Gson().fromJson(dealerStr, Dealer.class);

        return dealer;
    }

    /**
     * Пример чтения из файла массива JSON объектов
     */
    public static List<Purchase> loadPurchaseList() throws IOException, JsonSyntaxException {
        String pStr = "";
        File file = new File("persons.json");

        if (file.exists()) {
            pStr = new String(Files.readAllBytes(file.toPath()));
        } else {
            System.out.println("File purchase.json not found!");
        }

        Gson gson = new Gson();

        Purchase[] plst = gson.fromJson(pStr, Purchase[].class);

        return Arrays.asList(plst);
    }
}
