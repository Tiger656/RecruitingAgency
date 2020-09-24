package com.itechart.agency.service.impl;

import com.itechart.agency.entity.Interview;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.InterviewRepository;
import com.itechart.agency.service.CrudService;
import com.itechart.agency.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InterviewServiceImpl implements CrudService<Interview> {

    public final InterviewRepository interviewRepository;

    @Autowired
    public InterviewServiceImpl(InterviewRepository interviewRepository){
        this.interviewRepository = interviewRepository;
    }

    @Override
    public Long create(Interview interview) {
        return interviewRepository.save(interview).getId();
    }

    @Override
    public Interview findById(Long id) throws NotFoundException { //Why we should return optional????
        Optional<Interview> interviewOptional = interviewRepository.findById(id);
        if (interviewOptional.isPresent()){
            return interviewOptional.get();
        } else {
            throw new NotFoundException("Interview with id: " + id + " was not found");
        }
    }

    @Override
    public List<Interview> findAll() {
        return interviewRepository.findAll();
    }

    @Override
    public Long update(Interview interview) {
        interviewRepository.save(interview);
        return null;
    }

    @Override
    public void deleteById(Long id) {
        interviewRepository.deleteById(id);
    }

    @Override
    public void delete(Interview interview) {
        interviewRepository.delete(interview);
    }


}
