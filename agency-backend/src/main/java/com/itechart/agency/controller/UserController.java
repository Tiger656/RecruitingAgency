package com.itechart.agency.controller;


import com.itechart.agency.dto.UserDto;
import com.itechart.agency.entity.User;
import com.itechart.agency.service.CrudService;
import com.itechart.agency.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {
    private UserServiceImpl userService;


    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll();
    }

    //    @GetMapping("/{id}")
//    public User findById(@PathVariable(name = "id") Long id) {
//        return userService.findById(id);
//    }
    @PostMapping
    public Long createUser(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

}
