package hei.server.service;

import hei.server.model.Register;
import hei.server.model.Student;
import hei.server.repository.RegisterRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegisterService {
    private final RegisterRepository registerRepository;

    public List<Register> findAllRegisters(String type) {
        return registerRepository.getAll(type);
    }

    public void addRegister(Integer id, String type) {
        registerRepository.addRegister(id, type);
    }

    public List<Student> findAllStudentsPresent(String datetime) {
        datetime = datetime.replace("T", " ");
        return registerRepository.getAllStudentsPresent(datetime);
    }

    public List<Student> findAllStudentsAbsent(String datetime) {
        datetime = datetime.replace("T", " ");
        return registerRepository.getAllStudentsAbsent(datetime);
    }
}
