import by.gsu.epamlab.LenNum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    private static final String insertIntoFrequencies = "insert into Frequencies (len,num) values (?,?)";
    private static final String clearFrequencies = "delete from Frequencies";
    private static final String selectFromFrequencies = "select len,num from Frequencies where len > num";

    private static final String calcLen = "Int(ABS(x1 - x2) + 0.5)";
    private static final String selectFromCoordinates =
            "SELECT " + calcLen + " AS len, COUNT(*) AS num"
                    + " FROM Coordinates"
                    + " GROUP BY " + calcLen
                    + " HAVING " + calcLen + " > 0"
                    + " ORDER BY " + calcLen;

    public static void main(String[] args) {

        String className = "sun.jdbc.odbc.JdbcOdbcDriver";
        String dbUrl = "jdbc:odbc:jdbc";
        String user = "";
        String password = "";

        try {
            Class.forName(className);
            Connection cn = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                cn = DriverManager.getConnection(dbUrl, user, password);

                List<LenNum> lenNums = select(cn, selectFromCoordinates);

                clear(cn, clearFrequencies);

                insert(cn, lenNums, insertIntoFrequencies);

                lenNums = select(cn, selectFromFrequencies);

                for (LenNum lenNum : lenNums) {
                    System.out.println(lenNum);
                }

            } finally {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            }

        } catch (ClassNotFoundException | SQLException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void clear(Connection connection, String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }

    }

    private static List select(Connection connection, String sql) {

        List<LenNum> lenNums = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int len = result.getInt("len");
                int num = result.getInt("num");
                lenNums.add(new LenNum(len, num));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
        return lenNums;
    }

    private static void insert(Connection connection, List<LenNum> lenNums, String sql) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            for (LenNum lenNum : lenNums) {
                statement.setInt(1, lenNum.getLen());
                statement.setInt(2, lenNum.getNum());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
