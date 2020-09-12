//package com.itechart.agency.entity;
//
//
//import com.sun.istack.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "interviews")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class InterviewQuestion {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "interview_id")
//    private Interview interview;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "question_id")
//    private Question question;
//
//    @Column(name = "name")
//    @NotNull
//    private String answer;
//
//    @Column(name = "is_right")
//    @NotNull
//    private String isRight;
//
//}
