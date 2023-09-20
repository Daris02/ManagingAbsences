package hei.server.repository;

import hei.server.model.Student;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository {
    List<Student> getAll();

    Student getById(Integer id);

    Student add(Student student);

    Student updateStudent(Student student);

    void deleteStudentById(Integer id);
}
