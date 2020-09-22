package com.itechart.agency.controller;


import com.itechart.agency.dto.EmployerDto;
import com.itechart.agency.dto.UserDto;
import com.itechart.agency.dto.UserDtoRequest;
import com.itechart.agency.dto.UserDtoResponse;
import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.User;
import com.itechart.agency.entity.lists.Role;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.AgencyRepository;
import com.itechart.agency.repository.EmployerRepository;
import com.itechart.agency.repository.RoleRepository;
import com.itechart.agency.service.CrudService;
import com.itechart.agency.service.impl.EmployerServiceImpl;
import com.itechart.agency.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {
    private UserServiceImpl userService;
    @Autowired
    private EmployerServiceImpl employerService;
    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private RoleRepository roleRepository;
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto > getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable(name = "id") Long id) {

return userService.findById(id);

//        UserDto userDto = ;
//        return Objects.isNull(userDto)?:ResponseEntity.ok().body(userDto);
    }

    @PostMapping
    public Long createUser(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

//    @PutMapping
//    public Long updateUser(@RequestBody UserDto userDto) {
//        return userService.update(userDto);
//    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
    }






}
