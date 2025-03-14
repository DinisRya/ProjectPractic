package ru.projectpractic.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.projectpractic.dto.request.EmployeeRequest;
import ru.projectpractic.dto.request.JobRequest;
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
        return "redirect:/welcome";
    }

    @PostMapping("/application/approve/{application_id}")
    public String approve(
            @PathVariable("application_id") Long applicationId
    ) {
        applicationsService.approve(applicationId);
        return "redirect:/welcome";
    }

    @GetMapping("/add/job/{employee_id}")
    public String getAddJob(
            @PathVariable("employee_id") Long employeeId,
            Model model
    ) {
        model.addAttribute("employee_id", employeeId);
        return "add_job";
    }

    @PostMapping("add/job/{employee_id}")
    public String addJob(
            @RequestParam String description,
            @RequestParam String title,
            @PathVariable("employee_id") Long employeeId
            ) {
        jobService.create(new JobRequest(
                description,
                null,
                title,
                employeeId
        ));
        return "redirect:/employees/jobs";
    }

    @GetMapping("update/employee/{employee_id}")
    public String addEmployee(
            @PathVariable("employee_id") Long employeeId,
            Model model) {
        model.addAttribute("employee_id", employeeId);
        return "add_employee";
    }

    @PostMapping("update/employee/{employee_id}")
    public String createEmployee(
            @PathVariable("employee_id") Long employeeId,
            @RequestParam String department,
            @RequestParam String organization
    ) {
        employeeService.update(new EmployeeRequest(
                department,
                organization,
                getUserId()),
                employeeId);
        return "redirect:/employees/profile";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("employee",
                employeeService.findByUserId(getUserId()));
        return "employee_profile";
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.findByUsername(username).id();
    }
}
