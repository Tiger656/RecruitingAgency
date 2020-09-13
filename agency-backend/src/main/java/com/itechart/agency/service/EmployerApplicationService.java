package com.itechart.agency.service;

import com.itechart.agency.entity.EmployerApplication;
import com.itechart.agency.entity.lists.Status;

import java.util.List;

public interface EmployerApplicationService {
    List<EmployerApplication> getApplicationsByStatus(final Status status);
    void changeApplicationStatus(EmployerApplication application, final Status newStatus);
}
