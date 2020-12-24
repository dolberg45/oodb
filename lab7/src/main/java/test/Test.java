package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        System.out.println(" ----------------Classes-------------------");

        List<String> entities = PackageScan.getClasses();
        List<String> tables = DbConnect.getTables();

        Collections.sort(entities);
        Collections.sort(tables);

        System.out.println(entities);
        System.out.println(tables);

        System.out.println(compareEntities(entities, tables) + " --- Равность сущностей");

        System.out.println(" ----------------Fields-------------------");

        List<String> classFields = PackageScan.printClassFields();
        List<String> tableColumns = DbConnect.printDatabase();
        tableColumns = modifyNames(tableColumns);

        Collections.sort(classFields);
        Collections.sort(tableColumns);

        System.out.println(classFields);
        System.out.println(tableColumns);

        System.out.println(compareFields(classFields, tableColumns) + " --- Равность полей");

        System.out.println("\n" + getComparisonResults() + " --- Общая равность");

    }

    public static boolean getComparisonResults(){
        List<String> entities = PackageScan.getClasses();
        List<String> tables = DbConnect.getTables();
        if (compareFields(entities, tables)){
            List<String> classFields = PackageScan.printClassFields();
            List<String> tableColumns = DbConnect.printDatabase();
            tableColumns = modifyNames(tableColumns);
            return compareFields(classFields, tableColumns);
        }
        return false;
    }

    public static boolean compareEntities(List<String> entities, List<String> tables){
        if (entities.size() != tables.size()){
            return false;
        }
        int size = entities.size();
        for (int i = 0; i < size; i++) {
            if (!entities.get(i).equalsIgnoreCase(tables.get(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean compareFields(List<String>classFields, List<String> tableColumns){
        for (int i = 0; i < classFields.size(); i++){
            if (!classFields.contains(tableColumns.get(i)) && tableColumns.contains(classFields.get(i))){
                return false;
            }
        }
        return true;
    }

    public static String cutString(String word){
        int index = 0;
        String temp = "";
        index = word.indexOf('_');
        if (word.contains("_id")){
            for (int i = 0; i < index; i++){
                temp += word.charAt(i);
            }
        }else {
            for (int i = 0; i < word.length(); i++){
                if (i != index) {
                    temp += word.charAt(i);
                }
            }
        }
        return temp;
    }

    public static List<String> modifyNames(List<String> list){
        List<String> names = new ArrayList<>();
        for (String word : list){
            names.add(cutString(word));
        }
        return names;
    }

}
