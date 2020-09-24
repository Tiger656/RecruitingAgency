package com.itechart.agency.service.impl;

import com.itechart.agency.dto.EmployerApplicationDto;
import com.itechart.agency.dto.EmployerApplicationForManagerDto;
import com.itechart.agency.dto.converter.EmployerApplicationConvert;
import com.itechart.agency.dto.converter.EmployerApplicationForManagerConvert;
import com.itechart.agency.entity.EmployerApplication;
import com.itechart.agency.entity.lists.Feature;
import com.itechart.agency.exception.BadRequestException;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.*;
import com.itechart.agency.service.CrudService;
import com.itechart.agency.service.EmployerApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmployerApplicationServiceImpl implements EmployerApplicationService, CrudService<EmployerApplicationDto> {

    private final EmployerApplicationRepository employerApplicationRepository;
    private final FeatureRepository featureRepository;
    private final AgencyRepository agencyRepository;
    private final EmployerRepository employerRepository;
    private final EmploymentTypeRepository employmentTypeRepository;
    private final ProfessionRepository professionRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public EmployerApplicationServiceImpl(EmployerApplicationRepository employerApplicationRepository,
                                          FeatureRepository featureRepository, AgencyRepository agencyRepository,
                                          EmployerRepository employerRepository, EmploymentTypeRepository employmentTypeRepository,
                                          ProfessionRepository professionRepository, StatusRepository statusRepository) {
        this.employerApplicationRepository = employerApplicationRepository;
        this.featureRepository = featureRepository;
        this.agencyRepository = agencyRepository;
        this.employerRepository = employerRepository;
        this.employmentTypeRepository = employmentTypeRepository;
        this.professionRepository = professionRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public List<EmployerApplicationDto> getApplicationsByStatus(String status) {
        if (statusRepository.findByName(status).isPresent())
            throw new NotFoundException("Status doesn't exist");
        List<EmployerApplication> applicationList = employerApplicationRepository.findByStatus(statusRepository.findByName(status).get());
        return applicationList.stream().map((EmployerApplicationConvert::convertEntityToDto)).collect(Collectors.toList());
    }

    @Override
    public void changeApplicationStatus(Long applicationId, String newStatus) {
        if (employerApplicationRepository.findById(applicationId).isEmpty() || statusRepository.findByName(newStatus).isPresent())
            throw new NotFoundException("Employer application or/and status doesn't exist");
        else {
            EmployerApplication employerApplication = find(applicationId);
            employerApplication.setStatus(statusRepository.findByName(newStatus).get());
            employerApplicationRepository.save(employerApplication);
        }
    }

    private EmployerApplication find(Long id) {
        EmployerApplication application;
        if (id <= 0L) throw new BadRequestException("Not valid id");
        if (employerApplicationRepository.findById(id).isPresent()) {
            application = employerApplicationRepository.findById(id).get();
        } else {
            throw new NotFoundException("Employer application not found");
        }
        return application;
    }

    @Override
    public EmployerApplicationDto findById(Long id) {
        EmployerApplicationDto applicationDto;
        if (id <= 0L) throw new BadRequestException("Not valid id");
        if (employerApplicationRepository.findById(id).isPresent()) {
            applicationDto = EmployerApplicationConvert.convertEntityToDto
                    (employerApplicationRepository.findById(id).get());
        } else {
            throw new NotFoundException("Employer application not found");
        }
        return applicationDto;
    }

    @Override
    public List<EmployerApplicationDto> findAll() {
        List<EmployerApplication> applicationList = employerApplicationRepository.findAll();
        return applicationList.stream().map((EmployerApplicationConvert::convertEntityToDto)).collect(Collectors.toList());
    }

    public List<EmployerApplicationForManagerDto> findAllForManager() {
        List<EmployerApplication> applicationList = employerApplicationRepository.findAll();
        return applicationList.stream().map((EmployerApplicationForManagerConvert::convertEntityToDto)).collect(Collectors.toList());
    }

    @Override
    public Long create(EmployerApplicationDto applicationDto) {
        try {
            EmployerApplication application = setData(applicationDto);
            employerApplicationRepository.save(application);
            return application.getId();
        } catch (NoSuchElementException e) {
            throw new BadRequestException("Not valid data");
        }
    }

    @Override
    public Long update(EmployerApplicationDto applicationDto) {
        EmployerApplication application = setData(applicationDto);
        if (applicationDto.getId() <= 0L)
            throw new BadRequestException("Not valid data");
        if (employerApplicationRepository.findById(applicationDto.getId()).isPresent()) {
            //не помню, надо ли. Кажется, нет
            //employerApplicationRepository.delete(employerApplicationRepository.findById(applicationDto.getId()).get());
            employerApplicationRepository.save(application);
            return application.getId();
        } else {
            throw new NotFoundException("Employer application doesn't exist");
        }
    }


    private EmployerApplication setData(EmployerApplicationDto applicationDto) {
        for (long id : applicationDto.getFeaturesIds()) {
            if (featureRepository.findById(id).isEmpty())
                throw new NotFoundException("Feature doesn't exist");
        }
        if (agencyRepository.findById(applicationDto.getAgencyId()).isEmpty() || employerRepository.findById(applicationDto.getEmployerId()).isEmpty()
                || employmentTypeRepository.findById(applicationDto.getEmploymentTypeId()).isEmpty()
                || professionRepository.findById(applicationDto.getProfessionId()).isEmpty() || statusRepository.findById(applicationDto.getStatusId()).isEmpty())
            throw new NotFoundException("Agency, or/and employer, or/and employment type, or/and profession, or/and status  doesn't exist");

        EmployerApplication application = EmployerApplicationConvert.convertDtoToEntity(applicationDto);
        List<Feature> features = applicationDto.getFeaturesIds().stream()
                .map(a -> featureRepository.findById(a).get())
                .collect(Collectors.toList());
        application.setFeatures(features);
        application.setAgency(agencyRepository.findById(applicationDto.getAgencyId()).get());
        application.setEmployer(employerRepository.findById(applicationDto.getEmployerId()).get());
        application.setEmploymentType(employmentTypeRepository.findById(applicationDto.getEmploymentTypeId()).get());
        application.setProfession(professionRepository.findById(applicationDto.getProfessionId()).get());
        application.setStatus(statusRepository.findById(applicationDto.getStatusId()).get());
        return application;
    }

    @Override
    public void deleteById(Long id) {
        if (id <= 0L) throw new BadRequestException("Not valid id");
        if (employerApplicationRepository.findById(id).isPresent()) {
            employerApplicationRepository.deleteById(id);
        } else {
            throw new NotFoundException("Employer application doesn't exist");
        }
    }

    @Override
    public void delete(EmployerApplicationDto application) {
        deleteById(application.getId());
    }
}
