package orm;

import Test.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

public class EntityManagerFactory {

    /**
     * Подключение к БД. Это подключение передается в класс реализацию EntityManager
     * при его создании.
     */
    private Connection connection;
    private static final String url = "jdbc:postgresql://localhost:5432/lab7";
    private static final String user = "postgres";
    private static final String password = "458900";


    /**
     * Параметры подключения к базе данных:
     * <p> url - путь к БД для JDBC драйвера (например "jdbc:postgresql://localhost:5432/lab8")
     * <p> username - имя пользователя СУБД
     * <p> password - пароль пользователя СУБД
     */
    private Properties dbProperties;

    /**
     * Структура для хранения сведений о базе данных (таблицы, их поля)
     */
    private HashMap<String, HashSet<String>> tables = new HashMap<>();

    /**
     * Конструктор класса
     * @param dbProperties - параметры подключения к базе данных
     */
    public EntityManagerFactory(Properties dbProperties) {
        this.dbProperties = dbProperties;
        connection = getConnection();
    }

    /**
     * Метод создает, инициализирует экземпляр класса, реализующего EntityManager
     */
    public EntityManager createEntityManager() {
        if (scanModel()) {
            return new EntityManagerImpl(connection);
        }
        return null;
    }

    /**
     * Метод создает (если еще не создано) и возвращает подключение к БД
     */
    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(dbProperties.getProperty("url"),
                        dbProperties.getProperty("user"), dbProperties.getProperty("password"));
                System.out.println("Successfully connected!");
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database!");
            }
        }
        return connection;
    }

    /**
     * Метод сканирует структуру классов модели, структуру базы данных,
     * сравнивает их и возвращает булевский результат сравнения
     */
    private boolean scanModel() {
        return Test.getComparisonResults();
    }
}
