package com.itechart.agency.service.impl;

import com.itechart.agency.entity.Expert;
import com.itechart.agency.repository.ExpertRepository;
import com.itechart.agency.service.CrudService;
import com.itechart.agency.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExpertServiceImpl implements ExpertService {

    private final ExpertRepository expertRepository;

    @Autowired
    public ExpertServiceImpl(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }


    public Expert create(Expert expert) {
        Expert savedExpert = expertRepository.save(expert);
        if (savedExpert != null) {
            //sending email with credentials;

        }
        return savedExpert;
    }


    public Expert findById(Long id) {
        return null;
    }


    public List<Expert> findAll() {
        return expertRepository.findAll();
    }


    public Long update(Expert expert) {
        return null;
    }


    public void deleteById(Long id) {

    }

    public void delete(Expert expert) {

    }
}
