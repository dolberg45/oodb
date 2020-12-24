package test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PackageScan {

    private static final String domain = "model";
    private static final List<Class<?>> classes = PathScan.find(domain);

    public static List<String> getClasses(){
        List<String> list = new ArrayList<>();
        for (Class<?> cl : classes){
            list.add(cl.getSimpleName().toLowerCase());
        }
//        System.out.println(list);
        return list;
    }

    public static List<String> printClassFields(){
        List<String> allFields = new ArrayList<>();
//        System.out.println("--------------------------");
        for (Class<?> cl : classes){
//            System.out.println(cl.getSimpleName().toLowerCase() + ":");
            for (Field field : cl.getDeclaredFields()){
//                System.out.println(field.getName().toLowerCase());
                allFields.add(field.getName().toLowerCase());
            }
//            System.out.println("----------------------------");
        }
        return allFields;
    }

    public static void print(){
        printClassFields();
    }

}
