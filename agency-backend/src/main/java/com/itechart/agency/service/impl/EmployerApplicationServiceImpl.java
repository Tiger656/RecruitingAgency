package com.itechart.agency.service.impl;

import com.itechart.agency.entity.EmployerApplication;
import com.itechart.agency.entity.lists.Status;
import com.itechart.agency.repository.EmployerApplicationRepository;
import com.itechart.agency.service.CrudService;
import com.itechart.agency.service.EmployerApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerApplicationServiceImpl implements EmployerApplicationService, CrudService<EmployerApplication> {

    private EmployerApplicationRepository employerApplicationRepository;

    @Autowired
    public EmployerApplicationServiceImpl(EmployerApplicationRepository employerApplicationRepository){
        this.employerApplicationRepository = employerApplicationRepository;
    }

    @Override
    public List<EmployerApplication> getApplicationsByStatus(Status status) {
        return null;
    }

    @Override
    public void changeApplicationStatus(EmployerApplication application, Status newStatus) {

    }

    @Override
    public Long create(EmployerApplication application) {
        EmployerApplication app = employerApplicationRepository.save(application);
        return app.getId();
    }

    @Override
    public Optional<EmployerApplication> findById(Integer id) {
        return null;
    }

    @Override
    public List<EmployerApplication> findAll() {
        return null;
    }

    @Override
    public Long update(EmployerApplication application) {
        return 0L;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(EmployerApplication application) {

    }
}
