package com.itechart.agency.service;

import com.itechart.agency.dto.EmployerApplicationDto;
import com.itechart.agency.entity.lists.Status;

import java.util.List;

public interface EmployerApplicationService {
    List<EmployerApplicationDto> getApplicationsByStatus(final Status status);
    void changeApplicationStatus(Long applicationId, final Status newStatus);
}
