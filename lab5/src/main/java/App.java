import Classes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class App {


    public static void main(String[] args) {
        Connection connection = null;
        try {
            String dbURL = "jdbc:postgresql://localhost/lab5";
            connection = DriverManager.getConnection(dbURL, "postgres", "458900");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert connection != null;
        //addData(connection);//Заполнение бд инфой
        showData(connection);//Показываем бд
        editData(connection);
        System.out.println("\n\t\t======================= Data changed =============================\n");
        showData(connection);
    }

    public static void showData(Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT purchase, purchase from purchases");
            ResultSet purchases = preparedStatement.executeQuery();
            while (purchases.next()) {
                System.out.println(purchases.getObject(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static ArrayList<String> getRates(Connection connection, int userID) {
        ArrayList<String> result = new ArrayList<>();
        try {
            PreparedStatement statementMovies = connection.prepareStatement(
                    "SELECT movie, value, dateofchange from rate where userid=?");
            statementMovies.setInt(1, userID);
            ResultSet resultSet = statementMovies.executeQuery();
            while (resultSet.next()) {
                result.add("    " + numToStars(resultSet.getInt(2)) + ", " + getMovie(connection, resultSet.getInt(1)) + ", " + resultSet.getDate(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private static String getMovie(Connection connection, int movieID) {
        StringBuilder result = new StringBuilder();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT name, actors, artists from movie where id=?");
            statement.setInt(1, movieID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.append("\t").append(resultSet.getString(1)).append(", \n\t\tВ фильме снимались: ").append(resultSet.getString(2)).append("\n\t\tТруппа:").append(resultSet.getString(3));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result.toString();
    }

    private static String numToStars(int count) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }

    public static void addData(Connection connection) {
        addCars(connection);
        addClients(connection);
        addPurchases(connection);
    }

    private static void addCars(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO cars (car.model, car.price) VALUES (?,?)");
            statement.setObject(1, "Tesla Model S");
            statement.setObject(2, 100000);
            statement.executeUpdate();

            statement.setObject(1, "Tesla Model X");
            statement.setObject(2, 120000);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void addClients(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO clients (client.firstname, client.surname, client.age, client.phoneNumber, client.email, client.balance) VALUES (?,?,?,?,?,?)");
            statement.setObject(1, "Grigoriy");
            statement.setObject(2, "Alekseev");
            statement.setObject(3, 20);
            statement.setObject(4, "89274941525");
            statement.setObject(5, "grin4500@mail.ru");
            statement.setObject(6, 500000);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void addPurchases(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO purchases (purchase.purchaseId, purchase.purchaseDate, purchase.car.model, purchase.car.price, purchase.client.firstname, purchase.client.surname, purchase.client.age, purchase.client.phoneNumber, purchase.client.email, purchase.client.balance) VALUES (?,?,?,?,?,?,?,?,?,?)");
            statement.setObject(1, 1);
            statement.setDate(2, Date.valueOf("2000-10-01"));
            statement.setObject(3, "Cybertrack Tesla");
            statement.setObject(4, 150000);
            statement.setObject(5, "Grigoriy");
            statement.setObject(6, "Alekseev");
            statement.setObject(7, 20);
            statement.setObject(8, "89274941525");
            statement.setObject(9, "grin4500@mail.ru");
            statement.setObject(10, 500000);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    public static void addMovie(Connection connection) {
//        try {
//            GregorianCalendar calendar = new GregorianCalendar();
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSERT INTO movie (name, actors, artists) " +
//                            "VALUES (?," +
//                            "ARRAY[((?,?,?),?),((?,?,?),?),((?,?,?),?)]::actor[]," +
//                            "ARRAY[((?,?,?),?),((?,?,?),?),((?,?,?),?)]::artist[])");
//            statement.setString(1, "Двойной форсаж");
//
//            statement.setString(2, "Пол Уокер");
//            statement.setString(3, "Пол Уи́льям Уо́кер IV — американский киноактёр и модель. Широкую известность получил благодаря роли Брайана О’Коннора в серии фильмов «Форсаж»");
//            calendar.set(1973, Calendar.SEPTEMBER, 12);
//            statement.setDate(4, new Date(calendar.getTimeInMillis()));
//            statement.setString(5, "Брайан О’Коннор");
//
//            statement.setString(6, "Тайриз Гибсон");
//            statement.setString(7, "Тайри́з Дарне́лл Ги́бсон, также известный просто как Тайриз — американский рэпер, автор-исполнитель, виджей, актёр и продюсер");
//            calendar.set(1978, Calendar.DECEMBER, 30);
//            statement.setDate(8, new Date(calendar.getTimeInMillis()));
//            statement.setString(9, "Роман Пирс");
//
//            statement.setString(10, "Ева Мендес");
//            statement.setString(11, "Е́ва Ме́ндес — американская актриса");
//            calendar.set(1974, Calendar.MARCH, 5);
//            statement.setDate(12, new Date(calendar.getTimeInMillis()));
//            statement.setString(13, "Моника Фуэнтес");
//
//            //noinspection JpaQueryApiInspection
//            statement.setString(14, "Джон Синглтон");
//            statement.setString(15, "Джон Дэ́ниел Си́нглтон — американский кинорежиссёр, сценарист и кинопродюсер. Синглтон является самым молодым афроамериканцем в истории кинематографа, который был номинирован на «Оскар»");
//            calendar.set(1968, Calendar.JANUARY, 6);
//            statement.setDate(16, new Date(calendar.getTimeInMillis()));
//            statement.setString(17, "Режиссер");
//
//            statement.setString(18, "Мэттью Ф. Леонетти");
//            statement.setString(19, "Мэттью Ф. Леонетти A.S.C. — американский кинооператор. Его младший брат, Джон Р. Леонетти, также является кинооператором, а иногда и режиссёром. Мэттью был оператором первого фильма Джона, «Смертельная битва 2: Истребление»");
//            calendar.set(1941, Calendar.JULY, 31);
//            statement.setDate(20, new Date(calendar.getTimeInMillis()));
//            statement.setString(21, "Оператор");
//
//            statement.setString(22, "Дэвид Арнольд");
//            statement.setString(23, "Дэвид Арнольд — английский кинокомпозитор, наиболее известный своей музыкой в пяти фильмах про Джеймса Бонда, в фильме «Звёздные врата», «День независимости», «Годзилла» и в телесериалах «Маленькая Британия» и «Шерлок».");
//            calendar.set(1962, Calendar.JANUARY, 23);
//            statement.setDate(24, new Date(calendar.getTimeInMillis()));
//            statement.setString(25, "Композитор");
//
//            statement.executeUpdate();
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//    }

    public static void editData(Connection connection) {
        try {
//            PreparedStatement statement = connection.prepareStatement("UPDATE movie SET actors[1] = row(row(?,?,?),?) where movie.id=1");
            PreparedStatement statement = connection.prepareStatement("UPDATE purchases SET purchase.purchaseId = ?, purchase.purchaseDate = ?, purchase.car.model = ?, purchase.car.price = ?, purchase.client.firstname = ?, purchase.client.surname = ? where purchases.id=1");
            statement.setObject(1, 2);
            statement.setDate(2, Date.valueOf("2222-10-10"));
            statement.setObject(3, "TOYOTA RAV4");
            statement.setObject(4, 40000);
            statement.setObject(5, "Tim");
            statement.setObject(6, "Cook");

//            statement.setString(2, "Девон Эдвенна Аоки — американская актриса и модель. Девон Аоки — самая невысокая в мире супермодель. Свою дебютную роль в кино Девон исполнила в фильме «Смерть династии». Самая известная её работа — роль немой убийцы Михо в фильме Роберта Родригеса «Город грехов");
//            GregorianCalendar calendar = new GregorianCalendar();
//            calendar.set(1982, Calendar.AUGUST, 10);
//            statement.setDate(3, new Date(calendar.getTimeInMillis()));
//            statement.setString(4, "Суши");
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
