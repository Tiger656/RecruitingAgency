package com.itechart.agency.controller;

import com.itechart.agency.Dto.UserDto;
import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.Role;
import com.itechart.agency.entity.User;
import com.itechart.agency.entity.location.Address;
import com.itechart.agency.entity.location.City;
import com.itechart.agency.entity.location.Country;
import com.itechart.agency.entity.location.Region;
import com.itechart.agency.repository.*;
import com.itechart.agency.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll().stream().map(UserDto::convertToDto).collect(Collectors.toList());
    }
}
