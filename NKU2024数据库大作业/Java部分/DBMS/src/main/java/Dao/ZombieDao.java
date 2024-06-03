package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Zombie;

public class ZombieDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pvz";
    private String jdbcUsername = "root";
    private String jdbcPassword = "syw5861668syw";

    private static final String INSERT_ZOMBIE_SQL = "INSERT INTO zombie (name, toughness, equipment, bite_damage, throw_damage, crush_damage, speed, features, description, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ZOMBIES_BY_NAME = "SELECT * FROM zombie WHERE name LIKE ?";
    private static final String SELECT_ALL_ZOMBIES = "SELECT * FROM zombie";
    private static final String SELECT_ZOMBIES_BY_TOUGHNESS_THRESHOLD = "SELECT * FROM zombie WHERE toughness < ?";
    private static final String DELETE_ZOMBIE_SQL = "DELETE FROM zombie WHERE id = ?";
    private static final String UPDATE_ZOMBIE_SQL = "UPDATE zombie SET name = ?, toughness = ?, equipment = ?, bite_damage = ?, throw_damage = ?, crush_damage = ?, speed = ?, features = ?, description = ?, image_url = ? WHERE id = ?";

    public ZombieDao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertZombie(Zombie zombie) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ZOMBIE_SQL)) {
            preparedStatement.setString(1, zombie.getName());
            preparedStatement.setInt(2, zombie.getToughness());
            preparedStatement.setString(3, zombie.getEquipment());
            preparedStatement.setInt(4, zombie.getBiteDamage());
            preparedStatement.setInt(5, zombie.getThrowDamage());
            preparedStatement.setInt(6, zombie.getCrushDamage());
            preparedStatement.setString(7, zombie.getSpeed());
            preparedStatement.setString(8, zombie.getFeatures());
            preparedStatement.setString(9, zombie.getDescription());
            preparedStatement.setString(10, zombie.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Zombie> selectZombiesByName(String name) {
        List<Zombie> zombies = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ZOMBIES_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String zombieName = rs.getString("name");
                int toughness = rs.getInt("toughness");
                String equipment = rs.getString("equipment");
                int biteDamage = rs.getInt("bite_damage");
                int throwDamage = rs.getInt("throw_damage");
                int crushDamage = rs.getInt("crush_damage");
                String speed = rs.getString("speed");
                String features = rs.getString("features");
                String description = rs.getString("description");
                String imageUrl = rs.getString("image_url");
                Zombie zombie = new Zombie();
                zombie.setId(id);
                zombie.setName(zombieName);
                zombie.setToughness(toughness);
                zombie.setEquipment(equipment);
                zombie.setBiteDamage(biteDamage);
                zombie.setThrowDamage(throwDamage);
                zombie.setCrushDamage(crushDamage);
                zombie.setSpeed(speed);
                zombie.setFeatures(features);
                zombie.setDescription(description);
                zombie.setImageUrl(imageUrl);
                zombies.add(zombie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zombies;
    }

    public List<Zombie> selectAllZombies() {
        List<Zombie> zombieList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ZOMBIES)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int toughness = rs.getInt("toughness");
                String equipment = rs.getString("equipment");
                int biteDamage = rs.getInt("bite_damage");
                int throwDamage = rs.getInt("throw_damage");
                int crushDamage = rs.getInt("crush_damage");
                String speed = rs.getString("speed");
                String features = rs.getString("features");
                String description = rs.getString("description");
                String imageUrl = rs.getString("image_url");
                Zombie zombie = new Zombie();
                zombie.setId(id);
                zombie.setName(name);
                zombie.setToughness(toughness);
                zombie.setEquipment(equipment);
                zombie.setBiteDamage(biteDamage);
                zombie.setThrowDamage(throwDamage);
                zombie.setCrushDamage(crushDamage);
                zombie.setSpeed(speed);
                zombie.setFeatures(features);
                zombie.setDescription(description);
                zombie.setImageUrl(imageUrl);
                zombieList.add(zombie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zombieList;
    }

    public List<Zombie> selectZombiesByToughnessThreshold(int threshold) {
        List<Zombie> zombieList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ZOMBIES_BY_TOUGHNESS_THRESHOLD)) {
            preparedStatement.setInt(1, threshold);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int toughness = rs.getInt("toughness");
                String equipment = rs.getString("equipment");
                int biteDamage = rs.getInt("bite_damage");
                int throwDamage = rs.getInt("throw_damage");
                int crushDamage = rs.getInt("crush_damage");
                String speed = rs.getString("speed");
                String features = rs.getString("features");
                String description = rs.getString("description");
                String imageUrl = rs.getString("image_url");
                Zombie zombie = new Zombie();
                zombie.setId(id);
                zombie.setName(name);
                zombie.setToughness(toughness);
                zombie.setEquipment(equipment);
                zombie.setBiteDamage(biteDamage);
                zombie.setThrowDamage(throwDamage);
                zombie.setCrushDamage(crushDamage);
                zombie.setSpeed(speed);
                zombie.setFeatures(features);
                zombie.setDescription(description);
                zombie.setImageUrl(imageUrl);
                zombieList.add(zombie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zombieList;
    }

    public boolean deleteZombie(long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ZOMBIE_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateZombie(Zombie zombie) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ZOMBIE_SQL)) {
            statement.setString(1, zombie.getName());
            statement.setInt(2, zombie.getToughness());
            statement.setString(3, zombie.getEquipment());
            statement.setInt(4, zombie.getBiteDamage());
            statement.setInt(5, zombie.getThrowDamage());
            statement.setInt(6, zombie.getCrushDamage());
            statement.setString(7, zombie.getSpeed());
            statement.setString(8, zombie.getFeatures());
            statement.setString(9, zombie.getDescription());
            statement.setString(10, zombie.getImageUrl());
            statement.setLong(11, zombie.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }


}
