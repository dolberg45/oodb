package orm.operations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Remove {
    private final Connection connection;

    public Remove(Connection connection) {
        this.connection = connection;
    }

    public void remove(Object object){
        String sql = createRemoveStatement(object);
        try {
            Method method = object.getClass().getMethod("getId", null);
            int id = (Integer.parseInt(String.valueOf(method.invoke(object))));
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String createRemoveStatement(Object object) {
        String table = object.getClass().getSimpleName().toLowerCase();
        return "Delete from " + table + " where id = ?";
    }

}
