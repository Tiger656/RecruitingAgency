package com.itechart.agency.service.impl;

import com.itechart.agency.dto.BusyHoursDto;
import com.itechart.agency.entity.Interview;
import com.itechart.agency.entity.InterviewStatus;
import com.itechart.agency.entity.Manager;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.ExpertRepository;
import com.itechart.agency.repository.InterviewRepository;
import com.itechart.agency.repository.InterviewStatusRepository;
import com.itechart.agency.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class InterviewServiceImpl {

    public final InterviewRepository interviewRepository;
    public final InterviewStatusRepository interviewStatusRepository;
    public final ManagerRepository managerRepository;
    public final ExpertRepository expertRepository;

    @Autowired
    public InterviewServiceImpl(InterviewRepository interviewRepository, InterviewStatusRepository interviewStatusRepository, ManagerRepository managerRepository, ExpertRepository expertRepository) {
        this.interviewRepository = interviewRepository;
        this.interviewStatusRepository = interviewStatusRepository;
        this.managerRepository = managerRepository;
        this.expertRepository = expertRepository;
    }


    public Interview create(Interview interview) {
        Manager manager = managerRepository.findByUserId(interview.getManager().getId());
        interview.setManager(manager);
        return interviewRepository.save(interview);
    }


    public Interview findById(Long id) throws NotFoundException { //Why we should return optional????
        Optional<Interview> interviewOptional = interviewRepository.findById(id);
        if (interviewOptional.isPresent()) {
            return interviewOptional.get();
        } else {
            throw new NotFoundException("Interview with id: " + id + " was not found");
        }
    }


    public List<Interview> findAllByAgencyAndManager(Long agencyId, Long managerUserId) {
        Manager manager = managerRepository.findByUserId(managerUserId);
        List<Interview> interviews = interviewRepository.findByAgencyIdAndManagerId(agencyId, manager.getId());
        return interviews;
    }

    public List<Interview> findAllByAgencyAndExpertAndInterviewStatus(Long agencyId, Long expertUserId, Long interviewStatusId) {
        Long expertId = expertRepository.findByUserId(expertUserId).getId();
        List<Interview> interviews = interviewRepository.findByAgencyIdAndExpertIdAndInterviewStatusId(agencyId, expertId, interviewStatusId);
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


    public BusyHoursDto getExpertsBusyHours(Long agencyId, Long expertId, Integer year, Integer month, Integer day) {
        List<Object[]> listHours = interviewRepository.getExpertsBusyHours(agencyId, expertId, year, month, day);
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

    public BusyHoursDto getManagersBusyHours(Long agencyId, Long managerUserId, Integer year, Integer month, Integer day) {
        Manager manager = managerRepository.findByUserId(managerUserId);
        List<Object[]> listHours = interviewRepository.getManagersBusyHours(agencyId, manager.getId(), year, month, day);
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

    public Interview updateInterviewStatus(Long interviewId, Long newStatusId) {
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new NotFoundException("No interview with id: " + interviewId));
        InterviewStatus interviewStatus = interviewStatusRepository.findById(newStatusId).orElseThrow(() -> new NotFoundException("No interview with id: " + interviewId));
        interview.setInterviewStatus(interviewStatus);
        return interviewRepository.save(interview);
    }

    public BusyHoursDto getEmployeeAppBusyHours(Long agencyId, Long employeeContractId, Integer year, Integer month, Integer day) {
        List<Object[]> listHours = interviewRepository.getEmployeeContrBusyHours(agencyId, employeeContractId, year, month, day);
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
