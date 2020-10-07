package com.itechart.agency.service.impl;

import com.itechart.agency.dto.BusyHoursDto;
import com.itechart.agency.entity.Interview;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.InterviewRepository;
import com.itechart.agency.service.CrudService;
import com.itechart.agency.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class InterviewServiceImpl {

    public final InterviewRepository interviewRepository;

    @Autowired
    public InterviewServiceImpl(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }


    public Long create(Interview interview) {
        return interviewRepository.save(interview).getId();
    }


    public Interview findById(Long id) throws NotFoundException { //Why we should return optional????
        Optional<Interview> interviewOptional = interviewRepository.findById(id);
        if (interviewOptional.isPresent()) {
            return interviewOptional.get();
        } else {
            throw new NotFoundException("Interview with id: " + id + " was not found");
        }
    }


    public List<Interview> findAllByAgencyAndManager(Long agencyId, Long managerId) {
        List<Interview> interviews = interviewRepository.findByAgencyIdAndManagerId(agencyId, managerId);
        return interviews;
    }

    public List<Interview> findAllByAgencyAndExpertAndInterviewStatus(Long agencyId, Long expertId, Long interviewStatusId) {
        List<Interview> interviews = interviewRepository.findByAgencyIdAndExpertIdAndStatusId(agencyId, expertId, interviewStatusId);
        return interviews;
    }

    public List<Interview> findAllByAgency(Long agencyId) {
        List<Interview> interviews = interviewRepository.findByAgencyId(agencyId);
        return interviews;
    }


    public Long update(Interview interview) {
        interviewRepository.save(interview);
        return null;
    }


    public void deleteById(Long id) {
        interviewRepository.deleteById(id);
    }


    public void delete(Interview interview) {
        interviewRepository.delete(interview);
    }


    public BusyHoursDto getBusyHours(Long agencyId, Long expertId, Integer year, Integer month, Integer day) {
        List<Object[]> listHours = interviewRepository.getManagersBusyHours(agencyId, expertId, year, month, day);
        Map<Integer, Integer> map = null;
        Set<Integer> setOfHours = new HashSet();
        Set<Integer> setOfStartHours = new HashSet();
        Set<Integer> setOfEndHours = new HashSet();
        if (listHours != null && !listHours.isEmpty()) {
            map = new HashMap<Integer, Integer>();
            for (Object[] object : listHours) {
                map.put(((Double) object[0]).intValue(), ((Double) object[1]).intValue());
            }
        }
        else {
            return new BusyHoursDto(setOfHours, setOfStartHours, setOfEndHours);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer start = entry.getKey();
            setOfStartHours.add(start);
            Integer end = entry.getValue();
            setOfEndHours.add(end);
            for (; start <= end; start++) {
                setOfHours.add(start);
            }
        }
        return new BusyHoursDto(setOfHours, setOfStartHours, setOfEndHours);
    }
}
