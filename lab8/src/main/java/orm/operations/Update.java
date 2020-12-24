package orm.operations;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Update {

    private final Connection connection;
    private final Insert insert;
    public Update(Connection connection) {
        this.connection = connection;
        insert = new Insert(connection);
    }

    public void update(Object object, Integer id){
        try {
            List<Object> values = insert.getValues(object);
            String sql = createUpdateQuery(object);
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < values.size(); i++){
                String value = (String) values.get(i);
                String type = insert.defineType(value);
                if (type.equalsIgnoreCase("Integer")){
                    statement.setInt(i+1, Integer.parseInt(value));
                }else if(type.equalsIgnoreCase("String")){
                    statement.setString(i+1,(value));
                }else if (type.equalsIgnoreCase("Boolean")){
                    statement.setBoolean(i+1, Boolean.parseBoolean(value));
                }
            }
            statement.setInt(values.size()+1, id);
            statement.execute();
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String createUpdateQuery(Object object){
        String table = object.getClass().getSimpleName().toLowerCase();
        List<String> columns = insert.retrieveColumns(object);
//        System.out.println(columns);
        String sql = "Update " + table + " set ";
        for (int i = 0; i < columns.size(); i++){
            sql += "\"" + columns.get(i) + "\" = ?";
            if (i + 1 < columns.size()){
                sql += ", ";
            }
        }
        sql += " where id = ?";
        return sql;
    }


}
