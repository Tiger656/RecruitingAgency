//package com.itechart.agency.entity;
//
//import com.sun.istack.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "interviews")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Interview {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Column(name = "name")
//    @NotNull
//    private String name;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "agency_id")
//    private Agency agency;
//
//
//    /*@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employer_application_id")
//    private EmployerApplication employerApplication;*/
//
//    /*@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_contract_id")
//    private EmployeeContract employeeContract;*/
//
//    /*@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "manager_id")
//    private Manager manager;  or user*/
//
//    /*@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "expert_id")
//    private Expert expert;  or user*/
//
//    @Column(name = "date_time")
//    @NotNull
//    private LocalDateTime dateTime;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "interview_status_id")
//    private InterviewStatus interviewStatus;
//
//    @Column(name = "manager_comment")
//    @NotNull
//    private String managerComment;
//
//    @Column(name = "expert_comment")
//    @NotNull
//    private String expertComment;
//
//}