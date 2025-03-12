package ru.projectpractic.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.projectpractic.dto.request.ApplicationRequest;
import ru.projectpractic.dto.request.StudentRequest;
import ru.projectpractic.dto.response.JobResponse;
import ru.projectpractic.service.ApplicationsService;
import ru.projectpractic.service.JobService;
import ru.projectpractic.service.StudentService;
import ru.projectpractic.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final JobService jobService;
    private final UserService userService;
    private final ApplicationsService applicationsService;
    private final StudentService studentService;

    public StudentController(JobService jobService, UserService userService, ApplicationsService applicationsService, StudentService studentService) {
        this.jobService = jobService;
        this.userService = userService;
        this.applicationsService = applicationsService;
        this.studentService = studentService;
    }

    @GetMapping("/get/jobs")
    public String getJobs(Model model) {
        List<JobResponse> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @PostMapping("/job/subscribe/{job_id}")
    public String subscribe(
            @RequestParam("cover_letter") String coverLetter,
            @PathVariable("job_id") Long jobId
    ) {
        applicationsService.create(new ApplicationRequest(coverLetter, jobId, getUserId()));
        return "redirect:/welcome";
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.findByUsername(username).id();
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "student_profile";
    }

    @GetMapping("/create/profile")
    public String createProfile() {
        return "create_profile";
    }

    @GetMapping("/update/profile")
    public String updateProfile() {
        return "update_profile";
    }

    @PostMapping("/create/profile")
    public String createProfile(
            @RequestParam("course_of_study") Integer courseOfStudy,
            @RequestParam("faculty") String faculty,
            @RequestParam("resume") String resume
    ) {
        studentService.create(new StudentRequest(
                courseOfStudy,
                faculty,
                resume,
                getUserId()
        ));
        return "redirect:/welcome";
    }

    @PostMapping("/update/profile")
    public String updateProfile(
            @RequestParam("course_of_study") Integer courseOfStudy,
            @RequestParam("faculty") String faculty,
            @RequestParam("resume") String resume
    ) {
        studentService.update(new StudentRequest(
                courseOfStudy,
                faculty,
                resume,
                getUserId()
        ), studentService.findByUserId(getUserId()).id());
        return "redirect:/welcome";
    }
    //оформление профиля
}
