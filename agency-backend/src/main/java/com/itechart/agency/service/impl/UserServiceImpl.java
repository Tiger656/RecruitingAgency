package com.itechart.agency.service.impl;


import com.itechart.agency.dto.UserDto;
import com.itechart.agency.entity.Role;
import com.itechart.agency.entity.User;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.AgencyRepository;
import com.itechart.agency.repository.RoleRepository;
import com.itechart.agency.repository.UserRepository;
import com.itechart.agency.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailServiceImpl")
public class UserServiceImpl implements UserDetailsService, CrudService<UserDto> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    RoleRepository roleRepository;



    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not exist"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role ->authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    @Override
    public Long create(UserDto userDto) {
       User user = UserDto.convertDtoToEntity(userDto);
        user.setAgency(agencyRepository.findById(userDto.getAgencyId()).orElseThrow(()->new NotFoundException("No agency with id:"+userDto.getAgencyId())));

        /*Generate password*/
        user.setPassword("password");
       user.setRoles(userDto.getRoleId().stream()
               .map(id->roleRepository.findById(id)
                       .orElseThrow(()->new NotFoundException("Not found role with id:"+id)))
               .collect(Collectors.toList()));


      User user1 = userRepository.save(user);
       return user1.getId();
    }

    @Override
    public UserDto findById(Long id) {
        return UserDto.convertEntityToDto(userRepository.findById(id).orElseThrow(()->new NotFoundException("User by id:"+id+"not found")));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Long update(UserDto userDto) {
if(userRepository.findById(userDto.getId()).isPresent()) {
    User user = UserDto.convertDtoToEntity(userDto);
    user.setAgency(agencyRepository.findById(userDto.getAgencyId()).orElseThrow(() -> new NotFoundException("No agency with id:" + userDto.getAgencyId())));

    /*Generate password*/
    user.setPassword("password");
    user.setRoles(userDto.getRoleId().stream()
            .map(id -> roleRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Not found role with id:" + id)))
            .collect(Collectors.toList()));

    System.out.println(user);
    /*After save can i get user.id*/
    userRepository.save(user);
    return user.getId();
}else{
    throw new NotFoundException("Cannot update user with id:"+userDto.getId()+".Cause: not found in db");
}

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(UserDto userDto) {

    }
}
