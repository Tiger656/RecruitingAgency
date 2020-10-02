package com.itechart.agency.service;

import com.itechart.agency.dto.UserDto;
import com.itechart.agency.entity.Agency;

import java.util.List;

public interface UserService {
    UserDto create(final UserDto t);

    UserDto findById(final Long id);

    List<UserDto> findAll();

    UserDto update(final UserDto t);

    void deleteById(final Long id);

    List<String> getRolesByEmail(final String email);

    Agency getAgencyByUserEmail(final String email);

    List<UserDto> getAllByAgencyName(String name);

}
