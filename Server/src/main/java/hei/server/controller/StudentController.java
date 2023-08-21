package hei.server.controller;

import hei.server.model.Student;
import org.springframework.web.bind.annotation.*;
import hei.server.service.StudentService;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> findAllStudent() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable Integer id) {
        return studentService.findStudentById(id);
    }

    @PostMapping
    public Student addNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @PutMapping
    public Student updateStudentById(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable Integer id) {
        return studentService.deleteStudentById(id);
    }
}
