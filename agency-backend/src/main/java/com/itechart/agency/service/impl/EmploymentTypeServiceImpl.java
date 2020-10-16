package com.itechart.agency.service.impl;

import com.itechart.agency.entity.lists.EmploymentType;
import com.itechart.agency.exception.BadRequestException;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.*;
import com.itechart.agency.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmploymentTypeServiceImpl implements CrudService<EmploymentType> {

    private final EmploymentTypeRepository employmentTypeRepository;

    @Autowired
    public EmploymentTypeServiceImpl(EmploymentTypeRepository employmentTypeRepository) {
        this.employmentTypeRepository = employmentTypeRepository;
    }

    @Override
    public EmploymentType findById(Long id) {
        if (id <= 0L) throw new BadRequestException("Not valid id");
        if (employmentTypeRepository.findById(id).isPresent()) {
            return employmentTypeRepository.findById(id).get();
        } else {
            throw new NotFoundException("EmploymentType not found");
        }
    }

    @Override
    public List<EmploymentType> findAll() {
        return new ArrayList<>(employmentTypeRepository.findAll());
    }

    @Override
    public Long create(EmploymentType employmentType) {
        try {
            return employmentTypeRepository.save(employmentType).getId();
        } catch (NoSuchElementException e) {
            throw new BadRequestException("Not valid data");
        }
    }

    @Override
    public Long update(EmploymentType employmentType) {
        if (employmentType.getId() <= 0L)
            throw new BadRequestException("Not valid data");
        if (employmentTypeRepository.findById(employmentType.getId()).isPresent()) {
            return employmentTypeRepository.save(employmentType).getId();
        } else {
            throw new NotFoundException("EmploymentType doesn't exist");
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id <= 0L) throw new BadRequestException("Not valid id");
        if (employmentTypeRepository.findById(id).isPresent()) {
            employmentTypeRepository.deleteById(id);
        } else {
            throw new NotFoundException("EmploymentType doesn't exist");
        }
    }

    @Override
    public void delete(EmploymentType employmentType) {
        deleteById(employmentType.getId());
    }
}
