package by.gsu.epamlab;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private static final String CLASS_NAME = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String DB_URL = "jdbc:odbc:results";
    private static final String DB_USER = "jse";
    private static final String DB_PASS = "jse";

    private static final String CLEAR_LOGINS = "DELETE FROM logins";
    private static final String CLEAR_TESTS = "DELETE FROM tests";
    private static final String CLEAR_RESULTS = "DELETE FROM results";

    private static final String INSERT_INTO_LOGINS = "INSERT INTO logins (login) values(?)";
    private static final String INSERT_INTO_TESTS = "INSERT INTO tests (test) values(?)";
    private static final String INSERT_INTO_RESULTS = "INSERT INTO results (loginid, testid, dat, mark) VALUES(?,?,?,?)";
    private static final String SELECT_IDLOGIN = "SELECT idlogin FROM logins WHERE login = ?";
    private static final String SELECT_IDTEST = "SELECT idtest FROM tests WHERE test = ?";

    private static final String SELECT_RESULTS = "SELECT * FROM results";
    private static final String SELECT_AVERAGE = "select login, avg(mark) from results " +
            "inner join logins on results.loginid = logins.idlogin " +
            "group by login " +
            "order by 2 desc";

    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;


    private enum Table {
        LOGIN, TEST
    }

    private DAO() {
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
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void prepareDB() {
        clear(CLEAR_LOGINS);
        clear(CLEAR_TESTS);
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
            throw new IllegalStateException(e);
        }
        return results;
    }

    private static int getId(String value, Table table) {
        int id = -1;

        String sql = null;
        switch (table) {
            case LOGIN:
                sql = SELECT_IDLOGIN;
                break;
            case TEST:
                sql = SELECT_IDTEST;
                break;
        }

        try {
            statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, value);
            resultSet = statement.executeQuery();

            resultSet.last();
            int count = resultSet.getRow();
            resultSet.beforeFirst();
            if (count != 0) {
                resultSet.next();
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return id;
    }

    private static void addNewValue(String value, Table table) {

        String sql = null;
        switch (table) {
            case LOGIN:
                sql = INSERT_INTO_LOGINS;
                break;
            case TEST:
                sql = INSERT_INTO_TESTS;
                break;
        }

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, value);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void add(Result result) {

        int loginId = getId(result.getStudent(), Table.LOGIN);
        if (loginId == -1) {
            addNewValue(result.getStudent(), Table.LOGIN);
            loginId = getId(result.getStudent(), Table.LOGIN);
        }

        int testId = getId(result.getTest(), Table.TEST);
        if (testId == -1) {
            addNewValue(result.getTest(), Table.TEST);
            testId = getId(result.getTest(), Table.TEST);
        }

        try {
            statement = connection.prepareStatement(INSERT_INTO_RESULTS);
            statement.setInt(1, loginId);
            statement.setInt(2, testId);
            statement.setDate(3, result.getDate());
            statement.setInt(4, result.getMark());
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    public static List<MeanMark> selectMeanMarks() {

        List<MeanMark> meanMarks = new ArrayList<>();

        try {
            statement = connection.prepareStatement(SELECT_AVERAGE);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                meanMarks.add(new MeanMark(resultSet.getString(1), resultSet.getDouble(2)));
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return meanMarks;
    }
}
