package com.itechart.agency.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "question_variants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVariant {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "answer_variant")
    @NotNull
    private String answerVariant;

}
