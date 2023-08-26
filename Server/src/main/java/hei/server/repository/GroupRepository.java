package hei.server.repository;

import hei.server.model.Group;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class GroupRepository {
    private final Connection connection;

    public List<Group> getAll() {
        String sql = """
                    SELECT * FROM "group" ORDER BY id;
                    """;
        List<Group> allGroup = new ArrayList<>(0);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                allGroup.add(new Group(
                        resultSet.getInt("id"),
                        resultSet.getString("name"))
                );
            }
            return allGroup;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Group getById(Integer id) {
        String sql = """
                    SELECT * FROM "group" WHERE id = ?
                    """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Group(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Group();
    }

    public Group add(Group group) {
        try {
            String sql = """
                        INSERT INTO "group" VALUES (?, ?);
                        """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, group.getId());
            preparedStatement.setString(2, group.getName());

            preparedStatement.executeUpdate();

            return group;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Group();
    }

    public Group updateGroup(Group group) {
        try {
            String sql = """
                        UPDATE "group" SET
                            name = ?
                        WHERE id = ?
                        """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getId());

            preparedStatement.executeUpdate();

            return getById(group.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getById(group.getId());
    }

    public void deleteGroupById(Integer id) {
        try {
            String sql = """
                        DELETE  FROM "group" WHERE id = ?;
                        """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
