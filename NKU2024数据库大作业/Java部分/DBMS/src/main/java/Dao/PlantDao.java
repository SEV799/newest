package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Plant;


public class PlantDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pvz";
    private String jdbcUsername = "root";
    private String jdbcPassword = "syw5861668syw";

    private static final String INSERT_PLANT_SQL = "INSERT INTO plant (name, toughness, power, `range`, ammo, features, cost, cooldown, `description`, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PLANT_BY_ID = "SELECT * FROM plant WHERE id = ?";
    private static final String SELECT_ALL_PLANTS = "SELECT * FROM plant";
    private static final String DELETE_PLANT_SQL = "DELETE FROM plant WHERE id = ?";
    private static final String UPDATE_PLANT_SQL = "UPDATE plant SET name = ?, toughness = ?, power = ?, `range` = ?, ammo = ?, features = ?, cost = ?, cooldown = ?, `description` = ?, image_url = ? WHERE id = ?";
	private static final String SELECT_PLANTS_BY_NAME = "SELECT * FROM plant WHERE name LIKE ?";

    public PlantDao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertPlant(Plant plant) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLANT_SQL)) {
            preparedStatement.setString(1, plant.getName());
            preparedStatement.setInt(2, plant.getToughness());
            preparedStatement.setInt(3, plant.getPower());
            preparedStatement.setString(4, plant.getRange());
            preparedStatement.setString(5, plant.getAmmo());
            preparedStatement.setString(6, plant.getFeatures());
            preparedStatement.setInt(7, plant.getCost());
            preparedStatement.setInt(8, plant.getCooldown());
            preparedStatement.setString(9, plant.getDescription());
            preparedStatement.setString(10, plant.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Plant selectPlantById(long id) {
        Plant plant = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PLANT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int toughness = rs.getInt("toughness");
                int power = rs.getInt("power");
                String range = rs.getString("range");
                String ammo = rs.getString("ammo");
                String features = rs.getString("features");
                int cost = rs.getInt("cost");
                int cooldown = rs.getInt("cooldown");
                String description = rs.getString("description");
                String imageUrl = rs.getString("image_url");
                plant = new Plant();
                plant.setId(id);
                plant.setName(name);
                plant.setToughness(toughness);
                plant.setPower(power);
                plant.setRange(range);
                plant.setAmmo(ammo);
                plant.setFeatures(features);
                plant.setCost(cost);
                plant.setCooldown(cooldown);
                plant.setDescription(description);
                plant.setImageUrl(imageUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plant;
    }

    public List<Plant> selectAllPlants() {
        List<Plant> plantList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PLANTS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int toughness = rs.getInt("toughness");
                int power = rs.getInt("power");
                String range = rs.getString("range");
                String ammo = rs.getString("ammo");
                String features = rs.getString("features");
                int cost = rs.getInt("cost");
                int cooldown = rs.getInt("cooldown");
                String description = rs.getString("description");
                String imageUrl = rs.getString("image_url");
                Plant plant = new Plant();
                plant.setId(id);
                plant.setName(name);
                plant.setToughness(toughness);
                plant.setPower(power);
                plant.setRange(range);
                plant.setAmmo(ammo);
                plant.setFeatures(features);
                plant.setCost(cost);
                plant.setCooldown(cooldown);
                plant.setDescription(description);
                plant.setImageUrl(imageUrl);
                plantList.add(plant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plantList;
    }

    public List<Plant> selectPlantsByName(String name) {
        List<Plant> plants = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PLANTS_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String plantName = rs.getString("name");
                int toughness = rs.getInt("toughness");
                int power = rs.getInt("power");
                String range = rs.getString("range");
                String ammo = rs.getString("ammo");
                String features = rs.getString("features");
                int cost = rs.getInt("cost");
                int cooldown = rs.getInt("cooldown");
                String description = rs.getString("description");
                String imageUrl = rs.getString("image_url");
                Plant plant = new Plant();
                plant.setId(id);
                plant.setName(plantName);
                plant.setToughness(toughness);
                plant.setPower(power);
                plant.setRange(range);
                plant.setAmmo(ammo);
                plant.setFeatures(features);
                plant.setCost(cost);
                plant.setCooldown(cooldown);
                plant.setDescription(description);
                plant.setImageUrl(imageUrl);
                plants.add(plant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants;
    }

    public boolean deletePlant(long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLANT_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updatePlant(Plant plant) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLANT_SQL)) {
            statement.setString(1, plant.getName());
            statement.setInt(2, plant.getToughness());
            statement.setInt(3, plant.getPower());
            statement.setString(4, plant.getRange());
            statement.setString(5, plant.getAmmo());
            statement.setString(6, plant.getFeatures());
            statement.setInt(7, plant.getCost());
            statement.setInt(8, plant.getCooldown());
            statement.setString(9, plant.getDescription());
            statement.setString(10, plant.getImageUrl());
            statement.setLong(11, plant.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }


}

