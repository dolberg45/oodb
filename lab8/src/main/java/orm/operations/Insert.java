package orm.operations;

import annotations.Column;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Insert {

    private final Connection connection;

    public Insert(Connection connection) {
        this.connection = connection;
    }

    public void insert(Object object){
        try {
            List<Object> values = getValues(object);
            String sql = createInsertStatement(object);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < values.size(); i++){
                String value = (String) values.get(i);
                String type = defineType(value);
                if (type.equalsIgnoreCase("Integer")){
                    preparedStatement.setInt(i+1, Integer.parseInt(value));
                }else if(type.equalsIgnoreCase("String")){
                    preparedStatement.setString(i+1,(value));
                }else if (type.equalsIgnoreCase("Boolean")){
                    preparedStatement.setBoolean(i+1, Boolean.parseBoolean(value));
                }
            }

//            preparedStatement.executeUpdate();

//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){}
            System.out.println("Successfully added element");
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Object> getValues(Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<Object> values = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();;
        for (Field field : fields){
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations){
                if (annotation.annotationType().equals(Column.class)){
                    Method method = object.getClass().getMethod("get"+field.getName().substring(0,1).toUpperCase() +
                            field.getName().substring(1), null);
                    if (method.invoke(object) == null){
                        values.add("null");
                    }else values.add(String.valueOf(method.invoke(object)));
                }
            }
        }
        return values;
    }

    public  List<String> retrieveColumns(Object object){
        List<String> columnNames = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields){
            Annotation[] annotations = field.getAnnotations();
            String fieldName = changeFormat(field.getName());
            if (annotations.length >= 2){
                fieldName = addSuffix(fieldName);
            }
            columnNames.add(fieldName);
        }
        columnNames.remove("id");
        return columnNames;
    }

    public String addSuffix(String string){
        return string + "_id";
    }

    public String changeFormat(String string){
        String name = "";
        for (int i = 0; i < string.length(); i++){
            if (Character.isUpperCase(string.charAt(i))){
                name += "_"+ Character.toLowerCase(string.charAt(i));
            }else {
                name += string.charAt(i);
            }
        }
        return name;
    }

    public String createInsertStatement(Object object){
        String table = object.getClass().getSimpleName().toLowerCase();
        List<String> columns = retrieveColumns(object);
        int size = columns.size();
        String sql = "Insert into "+ table + " (";
        for (int i = 0; i < columns.size(); i++){
            sql += columns.get(i);
            if (i + 1 < columns.size()){
                sql += ", ";
            }
        }
        sql += ") values (";
        for (int i = 0; i < columns.size(); i++){
            sql += "?";
            if (i + 1 < columns.size()){
                sql += ", ";
            }
        }
        sql += ")";
        return sql;
    }

    public String defineType(String word){
        boolean type = true;
        for (int i = 0; i < word.length(); i++){
            if (!Character.isDigit(word.charAt(i))){
                type = false;
            }
        }
        if (type){
            return "Integer";
        }

        String temp = "";
        for (int i = 0; i < word.length(); i++){
            temp += word.charAt(i);
        }
        if (temp.equalsIgnoreCase("true") || temp.equalsIgnoreCase("false")){
            return "Boolean";
        }
        return "String";
    }


}
