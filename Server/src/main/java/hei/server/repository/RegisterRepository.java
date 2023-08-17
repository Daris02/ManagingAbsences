package hei.server.repository;

import hei.server.model.Register;
import hei.server.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RegisterRepository {
    private final Connection connection;

    public RegisterRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Register> getAll(String type) {
        String sql = "SELECT * FROM "+ type +" ORDER BY date ; ";

        List<Register> allRegisters = new ArrayList<>(0);

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                allRegisters.add(new Register(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("date"),
                    resultSet.getInt("id_student")
                ));
            }
            return allRegisters;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<>();
    }

    public void addRegister(Integer id, String type) {
        String sql = "INSERT INTO "+ type +" (id_student) VALUES (" + id + "); ";
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> getAllStudentsPresent(String datetime) {
        String sql = "SELECT " +
                        "    st.* " +
                        "FROM \"student\" st " +
                        "    INNER JOIN \"entry\" en ON st.id = en.id_student " +
                        "    FULL OUTER JOIN \"exit\" ex ON st.id = ex.id_student " +
                        "WHERE " +
                        "    ((st.id IN (" +
                        "        SELECT entry.id_student FROM entry" +
                        "    )) OR (date_part('hour', ex.date) >= date_part('hour', en.date) + 3))" +
                        "    AND date_part('year', '" + datetime + "'::TIMESTAMP) = date_part('year', en.date)" +
                        "    AND date_part('month', '" + datetime + "'::TIMESTAMP) = date_part('month', en.date)" +
                        "    AND date_part('day', '" + datetime + "'::TIMESTAMP) = date_part('day', en.date)" +
                        "    ;";

        List<Student> allStudents = new ArrayList<>(0);

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                allStudents.add(new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("ref_student"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("gender"),
                        resultSet.getBoolean("active"),
                        resultSet.getInt("id_group"))
                );
            }
            return allStudents;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<>();
    }

    public List<Student> getAllStudentsAbsent(String datetime) {
        String sql = "SELECT" +
                "    st.* " +
                "FROM \"student\" st " +
                "    FULL OUTER JOIN \"entry\" en ON st.id = en.id_student " +
                "WHERE " +
                "    st.id NOT IN (" +
                "        SELECT" +
                "            st.id" +
                "        FROM \"student\" st" +
                "            INNER JOIN \"entry\" en ON st.id = en.id_student " +
                "        WHERE date_part('year', en.date) = date_part('year', '" + datetime + "'::TIMESTAMP)" +
                "        AND date_part('month', en.date) = date_part('month', '" + datetime + "'::TIMESTAMP)" +
                "        AND date_part('day', en.date) = date_part('day', '" + datetime + "'::TIMESTAMP)" +
                "  );";

        List<Student> allStudents = new ArrayList<>(0);

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                allStudents.add(new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("ref_student"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("gender"),
                        resultSet.getBoolean("active"),
                        resultSet.getInt("id_group"))
                );
            }
            return allStudents;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<>();
    }
}
