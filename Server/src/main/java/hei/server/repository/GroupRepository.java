package hei.server.repository;

import hei.server.model.Group;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupRepository {
    private final Connection connection;

    public GroupRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Group> getAll() {
        String sql = "SELECT * FROM \"group\" ORDER BY id ;";

        List<Group> allGroup = new ArrayList<>(0);

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                allGroup.add(new Group(
                        resultSet.getInt("id"),
                        resultSet.getString("name"))
                );
            }
            return allGroup;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Group getById(Integer id) {
        String sql = "SELECT * FROM \"group\" WHERE id = " + id + " ;";

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            if (resultSet.next()) {
                return new Group(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Group();
    }

    public Group add(Group group) {
        try {
            String sql = "INSERT INTO \"group\" VALUES ("+ group.getId() + ", '" + group.getName() + "' );";
            connection.createStatement().executeUpdate(sql);
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Group();
    }

    public Group updateGroup(Group group) {
        try {
            String sql = "UPDATE \"group\" SET name = '" + group.getName()
                    + "' WHERE id = " + group.getId() + " ;";

            connection.createStatement().executeUpdate(sql);
            return getById(group.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(group.getId());
    }

    public void deleteGroupById(Integer id) {
        try {
            String sql = "DELETE FROM \"group\" WHERE id = " + id + ";";

            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
