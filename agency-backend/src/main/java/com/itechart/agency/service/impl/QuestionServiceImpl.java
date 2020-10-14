package com.itechart.agency.service.impl;

import com.itechart.agency.dto.QuestionDto;
import com.itechart.agency.dto.QuestionVariantDto;
import com.itechart.agency.dto.converter.QuestionVariantConverter;
import com.itechart.agency.entity.Question;
import com.itechart.agency.entity.QuestionType;
import com.itechart.agency.entity.QuestionVariant;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.QuestionRepository;
import com.itechart.agency.repository.QuestionTypeRepository;
import com.itechart.agency.repository.QuestionVariantRepository;
import com.itechart.agency.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements ManagerService {

    private final QuestionRepository questionRepository;
    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionVariantRepository questionVariantRepository;


    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionTypeRepository questionTypeRepository, QuestionTypeRepository questionTypeRepository1, QuestionVariantRepository questionVariantRepository) {
        this.questionRepository = questionRepository;
        this.questionTypeRepository = questionTypeRepository1;
        this.questionVariantRepository = questionVariantRepository;
    }

    public void createQuestion(QuestionDto questionDto) {
        if (questionDto.getVariants() == null) {
            String questionName = questionDto.getQuestionName();
            QuestionType questionType = questionTypeRepository.findById(1L).orElseThrow(() -> new NotFoundException("Question type with status " + 1L + " not found"));
            Question question = new Question(null, questionName, questionType);
            questionRepository.save(question);
        } else {
            String questionName = questionDto.getQuestionName();
            QuestionType questionType = questionTypeRepository.findById(2L).orElseThrow(() -> new NotFoundException("Question type with status " + 2L + " not found"));
            Question question = new Question(null, questionName, questionType);
            question = questionRepository.save(question);

            List<QuestionVariantDto> questionVariantDtos = questionDto.getVariants();
            List<QuestionVariant> questionVariants = questionVariantDtos.stream().map(QuestionVariantConverter::toEntity).collect(Collectors.toList());
            for(QuestionVariant questionVariant: questionVariants){
                questionVariant.setQuestion(question);
            }

            questionVariantRepository.saveAll(questionVariants);
        }
    }



}
