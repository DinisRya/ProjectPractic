package ru.projectpractic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resume", nullable = true)
    private String resume;
    //Пока пусть будет так, будет время, можно будет разобраться с хранением

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "course_of_study")
    private Integer courseOfStudy;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
