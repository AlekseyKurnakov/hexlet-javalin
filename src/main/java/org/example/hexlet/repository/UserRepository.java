package org.example.hexlet.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.User;

public class UserRepository extends BaseRepository {
    private static List<User> entities = new ArrayList<User>();

    public static void save(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Optional<User> find(Long id) throws SQLException{
        String sql = "SELECT * FROM users WHERE id = ?";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                User user = new User(name, email, password);
                user.setId(id);

                return Optional.of(user);
            }

            return Optional.empty();
        }
    }

    public static void delete(Long id) {
        entities.removeIf(user -> user.getId() == id);
    }

    public static void removeAll() {
        entities = new ArrayList<User>();
    }

    public static List<User> getEntities() throws SQLException{
        var sql = "SELECT * FROM users";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            List<User> result = new ArrayList<User>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(name, email, password);
                user.setId(id);
                result.add(user);
            }
            return result;
        }
    }

    public static Optional<User> findByNickname(String nickname) throws SQLException{
        String sql = "SELECT * FROM users WHERE name = ?";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, nickname);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                User user = new User(name, email, password);
                user.setId(id);

                return Optional.of(user);
            }

            return Optional.empty();
        }
    }
}