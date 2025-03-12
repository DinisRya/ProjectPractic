package ru.projectpractic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.projectpractic.dto.request.UserRequest;
import ru.projectpractic.service.UserService;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("registration")
    public String register(){
        return "registration_choice";
    }

    @GetMapping("/register/student")
    public String getRegisterStudentPage() {
        return "registration_students";
    }

    @PostMapping("/register/student")
    public String registerStudent(
        @RequestParam String username,
        @RequestParam String password
    ) {
        userService.createStudent(new UserRequest(username, password));
        return "redirect:/welcome";
    }

    @GetMapping("/register/employee")
    public String getRegisterEmployeePage() {
        return "registration_employees";
    }

    @PostMapping("/register/employee")
    public String registerEmployee(
            @RequestParam String username,
            @RequestParam String password
    ) {
        userService.createEmployee(new UserRequest(username, password));
        return "redirect:/welcome";
    }
}
