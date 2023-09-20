package hei.server.service;

import hei.server.repository.StudentRepository;
import lombok.AllArgsConstructor;
import hei.server.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(Integer id) {
        if (studentRepository.getReferenceById(id) == null) {
            return null;
        }
        return studentRepository.getReferenceById(id);
    }

    public Student addNewStudent(Student std) {
        List<Student> students = studentRepository.findAll();
        Integer lastId = students.size()+1;
        Student new_student = new Student(lastId, std.getRefStudent(), std.getFirstName(), std.getLastName(), std.getEmail(), std.getGender(), std.getActive(), std.getGroup());
        return studentRepository.save(new_student);
    }

    public Student updateStudent(Student student) {
        Student oldStudent = studentRepository.getReferenceById(student.getId());
        if (student.getGroup().getId() != null && !Objects.equals(oldStudent.getGroup().getId(), student.getGroup().getId())) {
            oldStudent.setGroup(student.getGroup());
        }
        if (student.getActive() != null && oldStudent.getActive() != student.getActive()) {
            oldStudent.setActive(student.getActive());
        }
        if (student.getRefStudent() != null && !Objects.equals(oldStudent.getRefStudent(), oldStudent.getRefStudent())) {
            oldStudent.setRefStudent(student.getRefStudent());
        }
        if (student.getGender() != null && !Objects.equals(oldStudent.getGender(), student.getGender())) {
            oldStudent.setEmail(student.getGender());
        }
        if (student.getEmail() != null && !Objects.equals(oldStudent.getEmail(), student.getEmail())) {
            oldStudent.setEmail(student.getEmail());
        }
        if (student.getLastName() != null && !Objects.equals(oldStudent.getLastName(), student.getLastName())) {
            oldStudent.setLastName(student.getLastName());
        }
        if (student.getFirstName() != null && !Objects.equals(oldStudent.getFirstName(), student.getFirstName())) {
            oldStudent.setFirstName(student.getFirstName());
        }
        return studentRepository.updateStudent(oldStudent);
    }

    public String deleteStudentById(Integer id) {
        if (studentRepository.getReferenceById(id).getId() == null) {
            return "Student not exist";
        }
        studentRepository.deleteById(id);
        return "Delete student successfully";
    }
}
