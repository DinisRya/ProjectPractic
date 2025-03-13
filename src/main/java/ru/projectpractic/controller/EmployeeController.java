package ru.projectpractic.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.projectpractic.dto.response.ApplicationResponse;
import ru.projectpractic.service.ApplicationsService;
import ru.projectpractic.service.EmployeeService;
import ru.projectpractic.service.JobService;
import ru.projectpractic.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final ApplicationsService applicationsService;
    private final JobService jobService;

    public EmployeeController(UserService userService, EmployeeService employeeService, ApplicationsService applicationsService, JobService jobService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.applicationsService = applicationsService;
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public String getAllJobs(Model model) {
        var jobs = jobService.findAllByUserId(getUserId());
        model.addAttribute("jobs", jobs);
        return "employee_jobs";
    }

    @GetMapping("/applications/{job_id}")
    public String applications(
            @PathVariable("job_id") Long jobId,
            Model model) {
        List<ApplicationResponse> applications = applicationsService.findAllByJobId(jobId);
        model.addAttribute("applications", applications);
        return "applications";
    }

    @PostMapping("/application/reject/{application_id}")
    public String reject(
            @PathVariable("application_id") Long applicationId
    ) {
        applicationsService.reject(applicationId);
        return "applications";
    }

    @PostMapping("/application/approve/{application_id}")
    public String approve(
            @PathVariable("application_id") Long applicationId
    ) {
        applicationsService.approve(applicationId);
        return "applications";
    }


    //Создание работы
    //Создание employee
    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.findByUsername(username).id();
    }
}
