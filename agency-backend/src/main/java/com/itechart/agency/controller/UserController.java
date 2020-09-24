package com.itechart.agency.controller;


import com.itechart.agency.dto.UserDto;
import com.itechart.agency.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable(name = "id") Long id) {

        return userService.findById(id);

    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        return new ResponseEntity<>(userService.create(userDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {

        return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
    }


}
