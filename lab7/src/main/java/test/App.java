package test;

public class App {

    private static final String domain = "src/main/java/model";

    /** ClassNames are equal to tables */

    public static void main(String[] args) {
        printClasses();
    }

    public static void printClasses(){
        System.out.println("---------------Table names-------------------");
        DbConnect.getTables().forEach(System.out::println);
        System.out.println("---------------Class names-------------------");
        PackageScan.getClasses().forEach(System.out::println);
        System.out.println("----------------------------------------------");
    }

    public static void printFields(){
        System.out.println("---------------Table columns-------------------");
    }


}
