import Classes.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class JsonExample {

    public static void main(String[] args) throws InterruptedException, IOException {
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
        File baseFile = new File("/Users/grigoriy/Desktop/OOBD/lab2/src/main/java/user.json");

        //Сортировка по критерию названия авто car.getName();
        List<Purchase> purchasesList = new ArrayList<>();
        purchasesList.add(purchaseOne);
        purchasesList.add(purchaseTwo);
        purchasesList.add(purchaseThree);
        PurchaseComparator comparator = new PurchaseComparator();
        purchasesList.sort(comparator);

        for (Purchase i : purchasesList) {
            System.out.println(i);
        }

        setObjectList(purchasesList, baseFile);
        List<Object> objects = getLoadList(baseFile);
        for (Object i : objects) {
            System.out.println(i.toString());
        }

        //Поиск элемента
        System.out.println(JsonExample.searchObject(purchasesList, "Tesla Model S").toString());

    }

    public static List<Object> getLoadList(File file) throws IOException {
        String obStr = new String(Files.readAllBytes(file.toPath()));
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Object[] array = gson.fromJson(obStr, Object[].class);
        List<Object> result = Arrays.asList(array);
        return result;
    }

    public static void setObjectList(List<Purchase> objectList, File file) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String objectsStr = gson.toJson(objectList);
        System.out.println(objectsStr);

        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(objectsStr.getBytes("UTF-8"));
        outputStream.flush();
    }

    public static Purchase searchObject(List<Purchase> list, String carModelNameElement) {
        Purchase result = null;
        for (Purchase i : list) {
            if (i.getCar().getModel().equals(carModelNameElement)) {
                return i;
            }
        }
        return result;
    }
}
