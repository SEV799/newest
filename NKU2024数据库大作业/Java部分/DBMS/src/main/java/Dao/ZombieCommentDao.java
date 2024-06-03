package Dao;

import model.ZombieComment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZombieCommentDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pvz";
    private String jdbcUsername = "root";
    private String jdbcPassword = "syw5861668syw";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertZombieComment(ZombieComment comment) throws SQLException {
        String INSERT_COMMENT_SQL = "INSERT INTO zombie_comment (user_id, plant_id,user_account,content) VALUES (?, ?, ?,?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT_SQL)) {
            preparedStatement.setLong(1, comment.getUserId());
            preparedStatement.setLong(2, comment.getZombieId());
            preparedStatement.setString(3, comment.getUserAccount());
            preparedStatement.setString(4, comment.getContent());
            preparedStatement.executeUpdate();
        }
    }

    public List<ZombieComment> selectCommentsByZombieId(long zombieId) {
        List<ZombieComment> comments = new ArrayList<>();
        String SELECT_COMMENTS_SQL = "SELECT * FROM zombie_comment WHERE zombie_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENTS_SQL)) {
            preparedStatement.setLong(1, zombieId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ZombieComment comment = new ZombieComment();
                comment.setId(rs.getLong("id"));
                comment.setUserId(rs.getLong("user_id"));
                comment.setZombieId(rs.getLong("zombie_id"));
                comment.setUserAccount(rs.getString("user_account"));
                comment.setContent(rs.getString("content"));
                comment.setCommentTime(rs.getTimestamp("comment_time"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public boolean deleteZombieComment(long commentId) throws SQLException {
        String DELETE_COMMENT_SQL = "DELETE FROM zombie_comment WHERE id = ?";
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COMMENT_SQL)) {
            statement.setLong(1, commentId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
