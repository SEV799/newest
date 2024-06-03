package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.User;

public class AdminDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pvz";
    private String jdbcUsername = "root";
    private String jdbcPassword = "syw5861668syw";


    private static final String INSERT_ADMIN_SQL = "INSERT INTO admins (name, account, password) VALUES (?, ?, ?)";
    private static final String SELECT_ADMIN_BY_ID = "SELECT * FROM admins WHERE id = ?";
    private static final String SELECT_ALL_ADMINS = "SELECT * FROM admins";
    private static final String DELETE_ADMIN_SQL = "DELETE FROM admins WHERE id = ?";
    private static final String UPDATE_ADMIN_SQL = "UPDATE admins SET name = ?, account = ?, password = ? WHERE id = ?";
    private static final String SELECT_ADMIN_BY_ACCOUNT = "SELECT * FROM admin WHERE account = ?";


    public AdminDao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertAdmin(Admin admin) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getAccount());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Admin selectAdminById(long id) {
        Admin admin = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String account = rs.getString("account");
                String password = rs.getString("password");
                admin = new Admin();
                admin.setId(id);
                admin.setName(name);
                admin.setAccount(account);
                admin.setPassword(password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return admin;
    }

    public List<Admin> selectAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADMINS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String account = rs.getString("account");
                String password = rs.getString("password");
                Admin admin = new Admin();
                admin.setId(id);
                admin.setName(name);
                admin.setAccount(account);
                admin.setPassword(password);
                admins.add(admin);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return admins;
    }

    public boolean deleteAdmin(long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateAdmin(Admin admin) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_SQL)) {
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getAccount());
            statement.setString(3, admin.getPassword());
            statement.setLong(4, admin.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    public User selectAdminByAccount(String account) {
        User admin = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ACCOUNT)) {
            preparedStatement.setString(1, account);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                admin = new User();
                admin.setId(rs.getLong("id"));
                admin.setName(rs.getString("name"));
                admin.setAccount(rs.getString("account"));
                admin.setPassword(rs.getString("password"));
                admin.setRole("VIP");  // 设置角色为VIP，供后续使用
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }


}
