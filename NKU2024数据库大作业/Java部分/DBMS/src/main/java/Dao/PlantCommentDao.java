package Dao;

import model.PlantComment;
import model.PlantCommentUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantCommentDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pvz";
    private String jdbcUsername = "root";
    private String jdbcPassword ="syw5861668syw";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertPlantComment(PlantComment comment) throws SQLException {
        String INSERT_COMMENT_SQL = "INSERT INTO plant_comment (user_id, plant_id,user_account,content) VALUES (?, ?, ?,?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT_SQL)) {
            preparedStatement.setLong(1, comment.getUserId());
            preparedStatement.setLong(2, comment.getPlantId());
            preparedStatement.setString(3, comment.getUserAccount());
            preparedStatement.setString(4, comment.getContent());
            preparedStatement.executeUpdate();
        }
    }

    public List<PlantComment> selectCommentsByPlantId(long plantId) {
        List<PlantComment> comments = new ArrayList<>();
        String SELECT_COMMENTS_SQL = "SELECT * FROM plant_comment WHERE plant_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENTS_SQL)) {
            preparedStatement.setLong(1, plantId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PlantComment comment = new PlantComment();
                comment.setId(rs.getLong("id"));
                comment.setUserId(rs.getLong("user_id"));
                comment.setPlantId(rs.getLong("plant_id"));
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
    



    public boolean deletePlantComment(long commentId) throws SQLException {
        String DELETE_COMMENT_SQL = "DELETE FROM plant_comment WHERE id = ?";
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COMMENT_SQL)) {
            statement.setLong(1, commentId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public List<PlantCommentUser> selectPlantCommentUserViewByPlantId(long plantId) {
        List<PlantCommentUser> comments = new ArrayList<>();
        String SELECT_COMMENTS_SQL = "SELECT * FROM plant_comment_user_view WHERE plant_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENTS_SQL)) {
            preparedStatement.setLong(1, plantId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PlantCommentUser comment = new PlantCommentUser();
                comment.setCommentId(rs.getLong("comment_id"));
                comment.setUserId(rs.getLong("user_id"));
                comment.setPlantId(rs.getLong("plant_id"));
                comment.setUserAccount(rs.getString("user_account"));
                comment.setContent(rs.getString("content"));
                comment.setCommentTime(rs.getTimestamp("comment_time"));
                comment.setUserName(rs.getString("user_name"));
                comment.setUserAge(rs.getInt("user_age"));
                comment.setUserGender(rs.getString("user_gender"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public List<PlantCommentUser> selectPlantCommentUserViewByUserName(String userName) {
        List<PlantCommentUser> comments = new ArrayList<>();
        String SELECT_COMMENTS_SQL = "SELECT * FROM plant_comment_user_view WHERE user_name LIKE ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENTS_SQL)) {
            preparedStatement.setString(1, "%" + userName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PlantCommentUser comment = new PlantCommentUser();
                comment.setCommentId(rs.getLong("comment_id"));
                comment.setUserId(rs.getLong("user_id"));
                comment.setPlantId(rs.getLong("plant_id"));
                comment.setUserAccount(rs.getString("user_account"));
                comment.setContent(rs.getString("content"));
                comment.setCommentTime(rs.getTimestamp("comment_time"));
                comment.setUserName(rs.getString("user_name"));
                comment.setUserAge(rs.getInt("user_age"));
                comment.setUserGender(rs.getString("user_gender"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
}
