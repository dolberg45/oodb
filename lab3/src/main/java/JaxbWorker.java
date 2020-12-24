import Classes.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class JaxbWorker {

    public static void main(String[] args) {
        // определяем название файла, куда будем сохранять
        String fileName = "/Users/grigoriy/Desktop/OOBD/lab3/src/main/java/purchases.xml";

        //создаем объект Student с какими-то данными
        Dealer dealer = new Dealer();
        dealer.setDealerName("TransTexService");
        Car carOne = new Car("Tesla Model S", 100_000, dealer);
        Client clientOne = new Client("Grigoriy", "Alekseev", 20, "89225150121", "dolberg@gmail.com", 1_000_000);
        Purchase purchase = new Purchase();//Создали транзакцию
        purchase.setPurchaseId(1);
        purchase.setPurchaseDate(new Date());
        purchase.setCar(carOne);
        purchase.setClient(clientOne);

        // сохраняем объект в XML файл
        convertObjectToXml(purchase, fileName);

        // восстанавливаем объект из XML файла
        Purchase unmarshPurchase = fromXmlToObject(fileName);
        if (unmarshPurchase != null) {
            System.out.println(unmarshPurchase.toString());
        }
    }


    // восстанавливаем объект из XML файла
    private static Purchase fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Purchase.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Purchase) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // сохраняем объект в XML файл
    private static void convertObjectToXml(Purchase purchase, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Purchase.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(purchase, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void createObjects() {
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
    }
}
