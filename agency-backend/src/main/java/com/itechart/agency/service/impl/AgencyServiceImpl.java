package com.itechart.agency.service.impl;

import com.itechart.agency.entity.Agency;
import com.itechart.agency.repository.AgencyRepository;
import com.itechart.agency.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AgencyServiceImpl implements CrudService<Agency> {
   @Autowired
    private AgencyRepository agencyRepository;
    @Override
    public Long create(Agency agency) {
        return null;
    }

    @Override
    public Agency findById(Long id) {
        return null;
    }
/*fix it catch exception*/
    @Override
    public List<Agency> findAll() {
        return agencyRepository.findAll();
    }

    @Override
    public Long update(Agency agency) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Agency agency) {

    }
}
