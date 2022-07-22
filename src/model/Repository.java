package model;

import java.sql.*;

public class Repository {

    private static Connection connection;
    protected static Statement statement;

    static {
        try {
            connection = Repository.connectToDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assert false;
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectToDataBase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Project",user = "root", password = "mam3040625470";
        return DriverManager.getConnection(url,user,password);
    }
}
