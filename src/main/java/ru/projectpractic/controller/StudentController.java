package ru.projectpractic.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.projectpractic.dto.request.ApplicationRequest;
import ru.projectpractic.dto.request.StudentRequest;
import ru.projectpractic.dto.response.JobResponse;
import ru.projectpractic.dto.response.StudentResponse;
import ru.projectpractic.service.ApplicationsService;
import ru.projectpractic.service.JobService;
import ru.projectpractic.service.StudentService;
import ru.projectpractic.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private static final Logger LOGGER = LogManager.getLogger();
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

    @GetMapping("/job/subscribe/{job_id}")
    public String getSub(
            @PathVariable("job_id") Long jobId,
            Model model
    ) {
        model.addAttribute("job_id", jobId);
        return "subscribe";
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
    public String getProfile(
            Model model
    ) {
        model.addAttribute("student", studentService.findByUserId(getUserId()));
        return "student_profile";
    }

//    @GetMapping("/create/profile")
//    public String createProfile() {
//        return "create_profile";
//    }

    @GetMapping("/update/profile/{student_id}")
    public String updateProfile(
            @PathVariable("student_id") Long studentId,
            Model model
    ) {
        model.addAttribute("student_id", studentId);
        return "update_profile";
    }

//    @PostMapping("/create/profile")
//    public String createProfile(
//            @RequestParam("course_of_study") Integer courseOfStudy,
//            @RequestParam("faculty") String faculty,
//            @RequestParam("resume") String resume
//    ) {
//        studentService.create(new StudentRequest(
//                courseOfStudy,
//                faculty,
//                resume,
//                getUserId()
//        ));
//        return "redirect:/welcome";
//    }

    @PostMapping("/update/profile/{student_id}")
    public String updateProfile(
            @PathVariable("student_id") Long studentId,
            @RequestParam("course_of_study") Integer courseOfStudy,
            @RequestParam("faculty") String faculty,
            @RequestParam("resume") String resume
    ) {
        var a = studentService.update(new StudentRequest(
                courseOfStudy,
                faculty,
                resume,
                getUserId()
        ), studentId);
        LOGGER.info(a.courseOfStudy());
        return "redirect:/students/profile";
    }
}
