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
        // EXAMPLE PATH VARIABLE :
        // -> {URL}/register/entry => TO get all row of entry
        return registerService.findAllRegisters(type);
    }

    @PostMapping("/{id}/{type}")
    public String addRegister(@PathVariable Integer id,@PathVariable String type) {
        // EXAMPLE PATH VARIABLE :
        //  ->  {URL}/register/1/entry TO add student with id=1 in entry register
        //  ->  {URL}/register/2/exit  TO add student with id=2 in exit register
        registerService.addRegister(id, type);
        return "Add student to " + type + " successfully";
    }

    @GetMapping("/present")
    public List<Student> findAllStudentsPresent(@RequestBody String datetime) {
        // TYPE OF REQUEST BODY IS "TEXT" :
        //      Example : 2023-08-22 or 2023-08-22T08:00:00 or 2023-08-22 08:00:00
        return registerService.findAllStudentsPresent(datetime);
    }

    @GetMapping("/absent")
    public List<Student> findAllStudentsAbsent(@RequestBody String datetime) {
        // TYPE OF REQUEST BODY IS "TEXT" :
        //      Example : 2023-08-22 or 2023-08-22T08:00:00 or 2023-08-22 08:00:00
        return registerService.findAllStudentsAbsent(datetime);
    }
}
