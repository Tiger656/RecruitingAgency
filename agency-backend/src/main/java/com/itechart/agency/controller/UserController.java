package com.itechart.agency.controller;


import com.itechart.agency.dto.UserDto;
import com.itechart.agency.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('SYSADMIN')")
    @GetMapping("/{id}")
    public UserDto findById(@PathVariable(name = "id") Long id) {

        return userService.findById(id);

    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        return new ResponseEntity<>(userService.create(userDto), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {

        return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
    }


}
