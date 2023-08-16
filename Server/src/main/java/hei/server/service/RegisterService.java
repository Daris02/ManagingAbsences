package hei.server.service;

import hei.server.model.Register;
import hei.server.model.Student;
import hei.server.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    private final RegisterRepository registerRepository;

    @Autowired
    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public List<Register> findAllRegisters(String type) {
        if (!type.equals("entry") || !type.equals("exit")) {
            type = "entry";
        }
        return registerRepository.getAll(type);
    }

    public List<Student> findAllStudentsPresent(Register register) {
        String datetime = register.getDate().toString().replace("T", " ");
        return registerRepository.getAllStudentsPresent(datetime);
    }

    public List<Student> findAllStudentsAbsent(Register register) {
        String datetime = register.getDate().toString().replace("T", " ");
        return registerRepository.getAllStudentsAbsent(datetime);
    }
}
