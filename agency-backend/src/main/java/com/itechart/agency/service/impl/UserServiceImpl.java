package com.itechart.agency.service.impl;


import com.itechart.agency.dto.UserDto;
import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.User;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.AgencyRepository;
import com.itechart.agency.repository.RoleRepository;
import com.itechart.agency.repository.UserRepository;
import com.itechart.agency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailServiceImpl")
public class UserServiceImpl implements UserDetailsService, UserService<UserDto> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not exist"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (userRepository.getUserByEmail(userDto.getEmail()).isEmpty()) {
            User user = userRepository.save(createUserData(userDto));

            return UserDto.convertEntityToDto(user);
        } else {
            throw new NotFoundException("User with email:" + userDto.getEmail() + "is exist");
        }
    }

    @Override
    public UserDto findById(Long id) {
        return UserDto.convertEntityToDto(userRepository.findById(id).filter(user -> user.getIsDeleted().equals(false)).orElseThrow(() -> new NotFoundException("User with id:" + id + " not found")));
    }

    /*fix get Agency Name*/
    @Override
    public List<UserDto> findAll() {


        List<UserDto> usersDto = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream().filter(u -> u.getIsDeleted().equals(false)).map(UserDto::convertEntityToDto).collect(Collectors.toList());

        for (UserDto userDto : usersDto
        ) {
            Agency agency = agencyRepository.findById(userDto.getAgencyId()).orElseThrow(() -> new NotFoundException("Agency with id:" + userDto.getAgencyId() + "not found"));
            userDto.setAgencyName(agency.getName());
        }
        if (usersDto.isEmpty()) {
            throw new NotFoundException("No users");
        }
        return usersDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        if (userRepository.findById(userDto.getId()).isPresent()) {
            User user = createUserData(userDto);
            userRepository.save(user);
            return UserDto.convertEntityToDto(user);
        } else {
            throw new NotFoundException("Cannot update user with id:" + userDto.getId() + ".Cause: not found in db");
        }

    }

    @Override
    public void deleteById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No user with id:" + id));
        user.setIsDeleted(true);
        userRepository.save(user);


    }


    private User createUserData(UserDto userDto) {
        System.out.println(userDto);
        User user = UserDto.convertDtoToEntity(userDto);
        user.setAgency(agencyRepository.findById(userDto.getAgencyId()).orElseThrow(() -> new NotFoundException("No agency with id:" + userDto.getAgencyId())));
//        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setPassword("root");
        user.setRoles(userDto.getRoles());

        user.setIsDeleted(false);
        System.out.println(user);
        return user;
    }
}
