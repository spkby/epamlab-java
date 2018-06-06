package by.gsu.epamlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DAOConnBuilder {

    INSTANCE;

    private final Connection cn;

    private final String CLASS_NAME = "sun.jdbc.odbc.JdbcOdbcDriver";
    private final String DB_URL = "jdbc:odbc:results";
    private final String DB_USER = "jse";
    private final String DB_PASS = "jse";

    DAOConnBuilder(){
        try {
            Class.forName(CLASS_NAME);
            cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static DAOConnBuilder getInstance(){
        return INSTANCE;
    }

    public Connection getConnection(){
        return cn;
    }

}
