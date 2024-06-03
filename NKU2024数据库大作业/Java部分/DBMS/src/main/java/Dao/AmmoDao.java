package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Ammo;



public class AmmoDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pvz";
    private String jdbcUsername = "root";
    private String jdbcPassword = "syw5861668syw";

    private static final String INSERT_AMMO_SQL = "INSERT INTO ammo (name, damage, effect, image_url) VALUES (?, ?, ?, ?)";
    private static final String SELECT_AMMO_BY_ID = "SELECT * FROM ammo WHERE id = ?";
    private static final String SELECT_ALL_AMMO = "SELECT * FROM ammo";
    private static final String SELECT_AMMO_BY_DAMAGE_THRESHOLD = "SELECT * FROM ammo WHERE damage < ?";
    private static final String DELETE_AMMO_SQL = "DELETE FROM ammo WHERE id = ?";
    private static final String UPDATE_AMMO_SQL = "UPDATE ammo SET name = ?, damage = ?, effect = ?, image_url = ? WHERE id = ?";
	private static final String SELECT_AMMO_BY_NAME = "SELECT * FROM ammo WHERE name LIKE ?";

    public AmmoDao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertAmmo(Ammo ammo) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AMMO_SQL)) {
            preparedStatement.setString(1, ammo.getName());
            preparedStatement.setInt(2, ammo.getDamage());
            preparedStatement.setString(3, ammo.getEffect());
            preparedStatement.setString(4, ammo.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ammo selectAmmoById(long id) {
        Ammo ammo = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AMMO_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int damage = rs.getInt("damage");
                String effect = rs.getString("effect");
                String imageUrl = rs.getString("image_url");
                ammo = new Ammo();
                ammo.setId(id);
                ammo.setName(name);
                ammo.setDamage(damage);
                ammo.setEffect(effect);
                ammo.setImageUrl(imageUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ammo;
    }

    public List<Ammo> selectAllAmmo() {
        List<Ammo> ammoList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AMMO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int damage = rs.getInt("damage");
                String effect = rs.getString("effect");
                String imageUrl = rs.getString("image_url");
                Ammo ammo = new Ammo();
                ammo.setId(id);
                ammo.setName(name);
                ammo.setDamage(damage);
                ammo.setEffect(effect);
                ammo.setImageUrl(imageUrl);
                ammoList.add(ammo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ammoList;
    }

    public List<Ammo> selectAmmoByDamageThreshold(int threshold) {
        List<Ammo> ammoList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AMMO_BY_DAMAGE_THRESHOLD)) {
            preparedStatement.setInt(1, threshold);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int damage = rs.getInt("damage");
                String effect = rs.getString("effect");
                String imageUrl = rs.getString("image_url");
                Ammo ammo = new Ammo();
                ammo.setId(id);
                ammo.setName(name);
                ammo.setDamage(damage);
                ammo.setEffect(effect);
                ammo.setImageUrl(imageUrl);
                ammoList.add(ammo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ammoList;
    }

    public boolean deleteAmmo(long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_AMMO_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateAmmo(Ammo ammo) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_AMMO_SQL)) {
            statement.setString(1, ammo.getName());
            statement.setInt(2, ammo.getDamage());
            statement.setString(3, ammo.getEffect());
            statement.setString(4, ammo.getImageUrl());
            statement.setLong(5, ammo.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<Ammo> selectAmmoByName(String name) {
        List<Ammo> ammoList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AMMO_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String ammoName = rs.getString("name");
                int damage = rs.getInt("damage");
                String effect = rs.getString("effect");
                String imageUrl = rs.getString("image_url");
                Ammo ammo = new Ammo();
                ammo.setId(id);
                ammo.setName(ammoName);
                ammo.setDamage(damage);
                ammo.setEffect(effect);
                ammo.setImageUrl(imageUrl);
                ammoList.add(ammo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ammoList;
    }
}
