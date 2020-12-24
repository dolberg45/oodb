import model.classes.Dealer;
import orm.EntityManager;
import orm.EntityManagerFactory;

import java.util.Properties;


public class Application {

    public static void main(String[] args) {


        Properties properties = new Properties();
        properties.put("url", "jdbc:postgresql://localhost:5432/lab7");
        properties.put("user", "postgres");
        properties.put("password", "458900");

        EntityManagerFactory factory = new EntityManagerFactory(properties);

        EntityManager entityManager = factory.createEntityManager();

//        Reception reception = new Reception();
//        reception.setId((long) 3);
//        reception.setName("The Hilton Reception");
//
//        Hotel hotel = new Hotel();
//        hotel.setName("The Korston Tower");
//        hotel.setStars(5L);
//        hotel.setReception(reception);

//        entityManager.persist(reception);
//        entityManager.persist(hotel);
//        entityManager.remove(reception);

//        Staff staff = new Staff();
//        staff.setName("Dave");
//        staff.setEmail("dave@gmail.com");
//        staff.setReception(reception);
//        entityManager.persist(staff);
//        entityManager.find(Reception.class, 3);
//        entityManager.find(Staff.class, 1);
//        entityManager.remove(staff);

//        Client client = new Client();
//        client.setEmail("BackhamDavid@mail.ru");
//        client.setName("David");
//        client.setRoomCount(3);

//        entityManager.merge(client,2);

//        Reception recep = (Reception) entityManager.find(Reception.class, 3);
//        entityManager.remove(recep);
//        Hotel hotel = (Hotel) entityManager.find(Hotel.class, 3);



        Dealer dealer = (Dealer) entityManager.find(Dealer.class, 1);
        System.out.println(dealer);

        dealer.setName("Moscow Tesla Club");

        entityManager.merge(dealer);

        dealer = (Dealer) entityManager.find(Dealer.class, 1);

        System.out.println(dealer);


    }


}
