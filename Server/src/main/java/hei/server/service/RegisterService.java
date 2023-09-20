package hei.server.service;

import hei.server.model.Entry;
import hei.server.model.Exit;
import hei.server.model.Register;
import hei.server.model.Student;
import hei.server.repository.EntryRepository;
import hei.server.repository.ExitRepository;
import hei.server.repository.StudentRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegisterService {
    private final EntryRepository entryRepository;
    private final ExitRepository exitRepository;
    private final StudentRepository studentRepository;

    public List<Register> findAllRegisters(String type) {
        if (type == "entry") {
            return (List<Register>) entryRepository.findAll().stream().map(entry -> (Register) entry);
        } else if (type == "exit") {
            return (List<Register>) exitRepository.findAll().stream().map(entry -> (Register) entry);
        }
        return null;
    }

    public Register addRegister(Integer id, String type) {
        if (type == "entry") {
            Student student = studentRepository.findById(id).orElse(null);

            if (student == null) { return null; }

            Entry new_entry = new Entry(student);

            return entryRepository.save(new_entry);
        } else if (type == "exit") {
            Student student = studentRepository.findById(id).orElse(null);

            if (student == null) { return null; }

            Exit new_exit = new Exit(student);

            return exitRepository.save(new_exit);
        }
        return null;
    }

    public List<Student> findAllStudentsPresent(String datetime) {
        datetime = datetime.replace("T", " ");
        return entryRepository.getAllStudentsPresent(datetime);
    }

    public List<Student> findAllStudentsAbsent(String datetime) {
        datetime = datetime.replace("T", " ");
        return entryRepository.getAllStudentsAbsent(datetime);
    }
}
