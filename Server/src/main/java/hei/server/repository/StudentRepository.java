package hei.server.repository;

import hei.server.model.Student;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class StudentRepository {
    private Connection connection;

    public List<Student> getAll() {
        String sql = "SELECT * FROM \"student\" ORDER BY id ;";

        List<Student> allStudent = new ArrayList<>(0);

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                allStudent.add(new Student(
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
            resultSet.close();
            return allStudent;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Student getById(Integer id) {
        String sql = "SELECT * FROM \"student\" WHERE id = " + id + " ;";

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            if (resultSet.next()) {
                return new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("ref_student"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("gender"),
                        resultSet.getBoolean("active"),
                        resultSet.getInt("id_group"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Student();
    }

    public Student add(Student student) {
        try {
            String sql = "INSERT INTO \"student\" VALUES (" + student.getId() + ", " +
                    " '" + student.getRefStudent() + "', " +
                    " '" + student.getFirstName() + "', " +
                    " '" + student.getLastName() + "', " +
                    " '" + student.getEmail() + "', " +
                    " '" + student.getGender() + "', " +
                    student.getActive() + ", " +
                    student.getIdGroup() + "); ";
            connection.createStatement().executeUpdate(sql);
            return student;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Student();
    }

    public Student updateStudent(Student student) {
        try {
            String sql = "UPDATE \"student\" SET " +
                    "ref_student = '" + student.getRefStudent() +
                    "', first_name = '" + student.getFirstName() +
                    "', last_name = '" + student.getLastName() +
                    "', email = '" + student.getEmail() +
                    "', gender = '" + student.getGender() +
                    "', active = " + student.getActive() +
                    ", id_group = " + student.getIdGroup() +
                    " WHERE id = " + student.getId() + " ;";

            connection.createStatement().executeUpdate(sql);
            return getById(student.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getById(student.getId());
    }

    public void deleteStudentById(Integer id) {
        try {
            String sql = "DELETE FROM \"student\" WHERE id = " + id + " ;";

            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
