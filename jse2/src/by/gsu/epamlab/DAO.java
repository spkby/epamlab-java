package by.gsu.epamlab;

import by.gsu.epamlab.beans.MeanMark;
import by.gsu.epamlab.beans.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DAO {

    private static final String INSERT_LOGIN = "INSERT INTO logins (login) values(?)";
    private static final String INSERT_TEST = "INSERT INTO tests (test) values(?)";
    private static final String INSERT_INTO_RESULTS = "INSERT INTO results (loginid, testid, dat, mark) VALUES(?,?,?,?)";
    private static final String SELECT_IDLOGIN = "SELECT idlogin FROM logins WHERE login = ?";
    private static final String SELECT_IDTEST = "SELECT idtest FROM tests WHERE test = ?";

    private static final String SELECT_AVERAGE = "SELECT login, avg(mark) FROM results " +
            "INNER JOIN logins ON results.loginid = logins.idlogin " +
            "GROUP BY login " +
            "ORDER BY 2 DESC ";

    private static final String SELECT_RESULTS_BY_MONTH_YEAR = "SELECT login,test,dat,mark FROM ((results " +
            "INNER JOIN tests ON tests.idtest = results.testid) " +
            "INNER JOIN logins ON logins.idlogin = results.loginid) " +
            "WHERE MONTH(dat) = MONTH(now()) AND YEAR(dat) = YEAR(now()) " +
            "ORDER BY dat";

    private static Connection cn;
    private static PreparedStatement st;
    private static ResultSet rs;

    static {
        cn = DAOConnBuilder.getInstance().getConnection();
    }

    public static void close() {
        closeResultSet();

        closeStatement();

        closeConnection();
    }

    private static void closeResultSet() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println(Constants.RESOURCE_CLOSING_PROBLEM + e);
            }
        }
    }

    private static void closeStatement() {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                System.err.println(Constants.RESOURCE_CLOSING_PROBLEM + e);
            }
        }
    }

    private static void closeConnection() {
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(Constants.RESOURCE_CLOSING_PROBLEM + e);
            }
        }
    }

    public static void prepareDB() {
        final String CLEAR_LOGINS = "DELETE FROM logins";
        final String CLEAR_TESTS = "DELETE FROM tests";
        final String CLEAR_RESULTS = "DELETE FROM results";

        clear(CLEAR_LOGINS);
        clear(CLEAR_TESTS);
        clear(CLEAR_RESULTS);
    }

    private static void clear(String sql) {
        try {
            st = cn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static List selectByMonthYear() {

        List<Result> results = new LinkedList<>();
        try {
            st = cn.prepareStatement(SELECT_RESULTS_BY_MONTH_YEAR);
            rs = st.executeQuery();

            while (rs.next()) {
                results.add(new Result(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getInt(4)
                ));
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return results;
    }

    private static int getId(String value, String sql) {
        int id = -1;

        try {
            st = cn.prepareStatement(sql);
            st.setString(1, value);
            rs = st.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return id;
    }

    private static void addNewValue(String value, String sql) {

        try {
            st = cn.prepareStatement(sql);
            st.setString(1, value);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void add(Result result) {

        int loginId = getId(result.getLogin(), SELECT_IDLOGIN);
        if (loginId == -1) {
            addNewValue(result.getLogin(), INSERT_LOGIN);
            loginId = getId(result.getLogin(), SELECT_IDLOGIN);
        }

        int testId = getId(result.getTest(), SELECT_IDTEST);
        if (testId == -1) {
            addNewValue(result.getTest(), INSERT_TEST);
            testId = getId(result.getTest(), SELECT_IDTEST);
        }
        try {
            st = cn.prepareStatement(INSERT_INTO_RESULTS);
            st.setInt(1, loginId);
            st.setInt(2, testId);
            st.setDate(3, result.getDate());
            st.setInt(4, result.getMark());
            st.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static List<MeanMark> selectMeanMarks() {

        List<MeanMark> meanMarks = new ArrayList<>();

        try {
            st = cn.prepareStatement(SELECT_AVERAGE);
            rs = st.executeQuery();

            while (rs.next()) {
                meanMarks.add(new MeanMark(rs.getString(1), rs.getDouble(2)));
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return meanMarks;
    }
}
