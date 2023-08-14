package hei.server.repository;

import hei.server.DataBase.StatementDB;
import hei.server.model.Group;
import hei.server.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    public List<Student> getAll() {
        String sql = "SELECT * FROM \"student\" ORDER BY id ;";

        List<Student> allStudent = new ArrayList<>(0);

        try {
            ResultSet resultSet = StatementDB.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                allStudent.add(new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("ref_student"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("active"),
                        resultSet.getInt("id_group"))
                );
            }
            return allStudent;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Student getById(Integer id) {
        String sql = "SELECT * FROM \"student\" WHERE id = " + id + " ;";

        try {
            ResultSet resultSet = StatementDB.createStatement().executeQuery(sql);

            if (resultSet.next()) {
                return new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("ref_student"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getBoolean("active"),
                        resultSet.getInt("id_group"));
            }

        } catch (Exception e) {
            e.printStackTrace();
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
                    student.getActive() + ", " +
                    student.getIdGroup() + "); ";
            StatementDB.createStatement().executeUpdate(sql);
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
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
                    "', active = " + student.getActive() +
                    ", id_group = " + student.getIdGroup() +
                    " WHERE id = " + student.getId() + " ;";
            StatementDB.createStatement().executeUpdate(sql);
            return getById(student.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(student.getId());
    }

    public void deleteStudentById(Integer id) {
        try {
            String sql = "DELETE FROM \"group\" WHERE id = " + id + " ;";

            StatementDB.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
