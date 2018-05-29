package by.gsu.epamlab;

import by.gsu.epamlab.beans.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private static final String insertIntoFrequencies = "insert into Frequencies (len,num) values (?,?)";
    private static final String CLEAR_LOGINS = "DELETE FROM logins";
    private static final String CLEAR_TESTS = "DELETE FROM tests";
    private static final String CLEAR_RESULTS = "DELETE FROM results";
    private static final String selectFromFrequencies = "select len,num from Frequencies where len > num";

    private static final String calcLen = "Int(ABS(x1 - x2) + 0.5)";
    private static final String selectFromCoordinates =
            "SELECT " + calcLen + " AS len, COUNT(*) AS num"
                    + " FROM Coordinates"
                    + " GROUP BY " + calcLen
                    + " HAVING " + calcLen + " > 0"
                    + " ORDER BY " + calcLen;


    private static final String CLASS_NAME = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String DB_URL = "jdbc:odbc:results";
    private static final String DB_USER = "jse";
    private static final String DB_PASS = "jse";
    private static final String INSERT_INTO_LOGINS = "INSERT INTO logins (login) values(?)";
    private static final String INSERT_INTO_RESULTS = "INSERT INTO results (loginid, testid, dat, mark) VALUES(?,?,?,?)";
    private static final String SELECT_IDLOGIN = "SELECT idLogin FROM logins WHERE login = ?";
    private static final String SELECT_IDTEST = "SELECT idTest FROM tests WHERE test = ?";

    private static final String SELECT_RESULTS = "SELECT * FROM results";

    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    private DB() {
    }

    public static void buildConnection() {

        if (connection != null) {
            return;
        }

        try {
            Class.forName(CLASS_NAME);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void prepareDB() {
        clear(CLEAR_RESULTS);
    }

    private static void clear(String sql) {
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private static List select() {

        List<Result> results = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_RESULTS);

            resultSet = statement.executeQuery();

            /*while (result.next()) {
                int len = result.getInt("len");
                int num = result.getInt("num");
                results.add(new LenNum(len, num));
            }*/

        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
        return results;
    }

    private static int getId(String sql, String value) {
        int id = -1;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, value);
            resultSet = statement.executeQuery();

            if (resultSet == null && resultSet.next()) {
                throw new IllegalStateException(Constants.ERROR_NOT_FOUND_ID_HEAD + value + Constants.ERROR_NOT_FOUND_ID_TAIL);
            }
            id = resultSet.getInt(1);

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return id;
    }

    public static void add(Result result) {

        int loginId = getId(SELECT_IDLOGIN, result.getStudent().getLogin());
        int testId = getId(SELECT_IDTEST, result.getTest().getName());

        if (loginId == -1 || testId == -1) {
            throw new IllegalStateException(Constants.ERROR_INVALID_ID);
        }

        try {
            statement = connection.prepareStatement(INSERT_INTO_RESULTS);
            statement.setInt(1, loginId);
            statement.setInt(2, testId);
            statement.setDate(3, result.getDate());
            statement.setInt(4, result.getMark());
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
