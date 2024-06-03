package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pvz";
    private String jdbcUsername = "root";
    private String jdbcPassword = "syw5861668syw";

    private static final String INSERT_USER_SQL = "INSERT INTO users (name, account, password, age, gender, bio, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_ACCOUNT = "SELECT * FROM users WHERE account = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET name = ?, account = ?, password = ?, age = ?, gender = ?, bio = ?, role = ? WHERE id = ?";
    private static final String CHECK_ACCOUNT_EXISTS_SQL = "SELECT COUNT(*) FROM users WHERE account = ?";
	private static final String SELECT_USERS_BY_NAME = "SELECT * FROM users WHERE name LIKE ?";
    
    public UserDao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        String sql = "{CALL insertUser(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setString(1, user.getName());
            callableStatement.setString(2, user.getAccount());
            callableStatement.setString(3, user.getPassword());
            callableStatement.setInt(4, user.getAge());
            callableStatement.setString(5, user.getGender());
            callableStatement.setString(6, user.getBio());
            callableStatement.setString(7, user.getRole());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    public User selectUserById(long id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String account = rs.getString("account");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String bio = rs.getString("bio");
                String role = rs.getString("role");
                user = new User();
                user.setId(id);
                user.setName(name);
                user.setAccount(account);
                user.setPassword(password);
                user.setAge(age);
                user.setGender(gender);
                user.setBio(bio);
                user.setRole(role);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public User selectUserByAccount(String account) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ACCOUNT)) {
            preparedStatement.setString(1, account);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String bio = rs.getString("bio");
                String role = rs.getString("role");
                user = new User();
                user.setId(id);
                user.setName(name);
                user.setAccount(account);
                user.setPassword(password);
                user.setAge(age);
                user.setGender(gender);
                user.setBio(bio);
                user.setRole(role);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM user_view")) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setAccount(rs.getString("account"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setBio(rs.getString("bio"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }


    public boolean deleteUser(long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall("{call deleteUserWithComments(?)}")) {
            callableStatement.setLong(1, id);
            rowDeleted = callableStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    private String getUserAccountById(long id) throws SQLException {
        String account = null;
        String sql = "SELECT account FROM users WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = resultSet.getString("account");
            }
        } catch (SQLException e) {
            throw new SQLException("Error while fetching user account: " + e.getMessage());
        }

        return account;
    }
    public void updateUser(User user) throws SQLException {
        String sql = "{CALL updateUserAndComments(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getAccount());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getGender());
            statement.setString(7, user.getBio());
            statement.setString(8, user.getRole());

            statement.executeUpdate();
        }
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
    
    public boolean accountExists(String account) {
        boolean exists = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT_EXISTS_SQL)) {
            preparedStatement.setString(1, account);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public List<User> selectUsersByName(String name) {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	String UserName = rs.getString("name");
                long id = rs.getLong("id");
                String account = rs.getString("account");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String bio = rs.getString("bio");
                String role = rs.getString("role");
                User user = new User();
                user.setId(id);
                user.setName(UserName);
                user.setAccount(account);
                user.setPassword(password);
                user.setAge(age);
                user.setGender(gender);
                user.setBio(bio);
                user.setRole(role);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}