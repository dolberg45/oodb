import sun.management.resources.agent;

//Этот класс реализует возможность добавления, удаления и изменения кастомного типа данных в PostgreSql
public class Application {

    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}


//MARK: - 1)Занести все классы как user define type в БД PostgreSQL. CREATE TYPE ....

//CREATE TYPE human AS (
//        firstname character varying (255),
//    surname character varying (255),
//    age integer,
//    phoneNumber character varying (255),
//    email character varying (255)
//);

//    CREATE TYPE client AS (
//        firstname character varying (255),
//    surname character varying (255),
//    age integer,
//    phoneNumber character varying (255),
//    email character varying (255),
//    balance integer
//);

//    CREATE TYPE car AS (
//        model character varying (255),
//    price integer
//);

//    CREATE TYPE agent AS (
//        agentId character varying (255),
//    client client
//);

//    CREATE TYPE purchase AS (
//        purchaseId integer,
//        purchaseDate date,
//        car car,
//        client client
//);
