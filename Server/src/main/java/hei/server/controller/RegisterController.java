package hei.server.controller;

import hei.server.model.Register;
import hei.server.model.Student;
import hei.server.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/{type}")
    public List<Register> findAllRegisters(@PathVariable String type) {
        return registerService.findAllRegisters(type);
    }

    @PostMapping("/{id}/{type}")
    public String addRegister(@PathVariable Integer id,@PathVariable String type) {
        registerService.addRegister(id, type);
        return "Add student to " + type + " successfully";
    }

    @GetMapping("/present")
    public List<Student> findAllStudentsPresent(@RequestBody String datetime) {
        return registerService.findAllStudentsPresent(datetime);
    }

    @GetMapping("/absent")
    public List<Student> findAllStudentsAbsent(@RequestBody String datetime) {
        return registerService.findAllStudentsAbsent(datetime);
    }
}
