package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Equipment;



public class EquipmentDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pvz";
    private String jdbcUsername = "root";
    private String jdbcPassword = "syw5861668syw";

    private static final String INSERT_EQUIPMENT_SQL = "INSERT INTO equipment (name, defense, damage, effect, image_url) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_EQUIPMENT_BY_ID = "SELECT * FROM equipment WHERE id = ?";
    private static final String SELECT_ALL_EQUIPMENT = "SELECT * FROM equipment";
    private static final String SELECT_EQUIPMENT_BY_DEFENSE_THRESHOLD = "SELECT * FROM equipment WHERE defense < ?";
    private static final String DELETE_EQUIPMENT_SQL = "DELETE FROM equipment WHERE id = ?";
    private static final String UPDATE_EQUIPMENT_SQL = "UPDATE equipment SET name = ?, defense = ?, damage = ?, effect = ?, image_url = ? WHERE id = ?";
	private static final String SELECT_EQUIPMENT_BY_NAME = "SELECT * FROM equipment WHERE name LIKE ?";

    public EquipmentDao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertEquipment(Equipment equipment) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EQUIPMENT_SQL)) {
            preparedStatement.setString(1, equipment.getName());
            preparedStatement.setInt(2, equipment.getDefense());
            preparedStatement.setInt(3, equipment.getDamage());
            preparedStatement.setString(4, equipment.getEffect());
            preparedStatement.setString(5, equipment.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Equipment selectEquipmentById(long id) {
        Equipment equipment = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EQUIPMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int defense = rs.getInt("defense");
                int damage = rs.getInt("damage");
                String effect = rs.getString("effect");
                String imageUrl = rs.getString("image_url");
                equipment = new Equipment();
                equipment.setId(id);
                equipment.setName(name);
                equipment.setDefense(defense);
                equipment.setDamage(damage);
                equipment.setEffect(effect);
                equipment.setImageUrl(imageUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipment;
    }

    public List<Equipment> selectAllEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EQUIPMENT)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int defense = rs.getInt("defense");
                int damage = rs.getInt("damage");
                String effect = rs.getString("effect");
                String imageUrl = rs.getString("image_url");
                Equipment equipment = new Equipment();
                equipment.setId(id);
                equipment.setName(name);
                equipment.setDefense(defense);
                equipment.setDamage(damage);
                equipment.setEffect(effect);
                equipment.setImageUrl(imageUrl);
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipmentList;
    }

    public List<Equipment> selectEquipmentByDefenseThreshold(int threshold) {
        List<Equipment> equipmentList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EQUIPMENT_BY_DEFENSE_THRESHOLD)) {
            preparedStatement.setInt(1, threshold);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int defense = rs.getInt("defense");
                int damage = rs.getInt("damage");
                String effect = rs.getString("effect");
                String imageUrl = rs.getString("image_url");
                Equipment equipment = new Equipment();
                equipment.setId(id);
                equipment.setName(name);
                equipment.setDefense(defense);
                equipment.setDamage(damage);
                equipment.setEffect(effect);
                equipment.setImageUrl(imageUrl);
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipmentList;
    }

    public boolean deleteEquipment(long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EQUIPMENT_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEquipment(Equipment equipment) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EQUIPMENT_SQL)) {
            statement.setString(1, equipment.getName());
            statement.setInt(2, equipment.getDefense());
            statement.setInt(3, equipment.getDamage());
            statement.setString(4, equipment.getEffect());
            statement.setString(5, equipment.getImageUrl());
            statement.setLong(6, equipment.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public List<Equipment> selectEquipmentByName(String name) {
        List<Equipment> equipmentList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EQUIPMENT_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String equipmentName = rs.getString("name");
                int defense = rs.getInt("defense");
                int damage = rs.getInt("damage");
                String effect = rs.getString("effect");
                String imageUrl = rs.getString("image_url");
                Equipment equipment = new Equipment();
                equipment.setId(id);
                equipment.setName(equipmentName);
                equipment.setDefense(defense);
                equipment.setDamage(damage);
                equipment.setEffect(effect);
                equipment.setImageUrl(imageUrl);
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipmentList;
    }
}
