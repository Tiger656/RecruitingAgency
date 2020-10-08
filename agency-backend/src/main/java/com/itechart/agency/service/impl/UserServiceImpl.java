package com.itechart.agency.service.impl;


import com.itechart.agency.dto.UserDto;
import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.User;
import com.itechart.agency.entity.lists.Role;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.AgencyRepository;
import com.itechart.agency.repository.RoleRepository;
import com.itechart.agency.repository.UserRepository;
import com.itechart.agency.service.UserService;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;
import java.util.stream.Collectors;

@Service("userDetailServiceImpl")
public class UserServiceImpl implements UserDetailsService, UserService {
    private static final String MESSAGE_AFTER_CREATED_AGENCY="";
    private static final String CREATE_AGENCY_FOR_OWNER="";
    private static final Properties props = new Properties();
    private final UserRepository userRepository;
    private final AgencyRepository agencyRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public UserServiceImpl(UserRepository userRepository, AgencyRepository agencyRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.agencyRepository = agencyRepository;
        this.roleRepository = roleRepository;
    }

    public UserDetails loadUserByUsername(String email) {

        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("The username or password you entered is incorrect"));

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
            throw new NotFoundException("User with email: " + userDto.getEmail() + " is exist");
        }
    }

    @Override
    public UserDto findById(Long id) {
        return UserDto.convertEntityToDto(userRepository.findById(id).filter(user -> user.getIsDeleted().equals(false)).orElseThrow(() -> new NotFoundException("User with id:" + id + " not found")));
    }


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

    @Override
    public List<String> getRolesByEmail(String email) {

        return userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("User with email " + email + " not exist")).getRoles().stream().map(Role::getName).collect(Collectors.toList());
    }

    @Override
    public Agency getAgencyByUserEmail(String email) {
        System.out.println(userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("No user with " + email + " email")).getAgency());
        return userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("No user with " + email + " email")).getAgency();
    }

    @Override
    public List<UserDto> getAllByAgencyName(String name) {
        return userRepository.findAllByAgency_Id(agencyRepository.findByName(name).orElseThrow(() -> new NotFoundException("No agency with name" + name)).getId())

                .stream()
                .filter(user -> user.getIsDeleted().equals(false))
                .map(UserDto::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAllByAgencyId(Long id) {
         userRepository.findAllByAgency_Id(id).forEach(user -> user.setIsDeleted(true));
    }


    private User createUserData(UserDto userDto) {

        User user = UserDto.convertDtoToEntity(userDto);
        user.setAgency(agencyRepository.findById(userDto.getAgencyId()).orElseThrow(() -> new NotFoundException("No agency with id:" + userDto.getAgencyId())));


        if (userDto.getId() == null) {
            String password = generatePassword();
            user.setPassword(bCryptPasswordEncoder.encode((password)));
            try {
                EmailServiceImpl.send(user.getEmail(), "User is create!!!", "Your password is\n" + password);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else
            user.setPassword(userRepository.findById(userDto.getId()).orElseThrow(() -> new NotFoundException("No user with id " + userDto.getId())).getPassword());

        user.setRoles(userDto.getRoles());
        user.setIsDeleted(false);
        return user;
    }

    void createUserByRole(String email, Long agencyId, String role) {
        List<Role> roles = new ArrayList<>(List.of(roleRepository.findByName(role)));

        if (userRepository.getUserByEmail(email).isEmpty()) {
            String password = generatePassword();
            userRepository.save(new User(email, bCryptPasswordEncoder.encode((password)), agencyRepository.findById(agencyId).orElseThrow(() -> new NotFoundException("fsdf")), false, roles));
            try {
                EmailServiceImpl.send(email, "Agency was registered successfully!!!", "Hi, "+email+" your role is "+role+"\nYour password is\n" + password);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            throw new NotFoundException("User with email: " + email + " is exist");

        }
    }

    private String generatePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        return passwordGenerator.generatePassword(10, new CharacterRule(EnglishCharacterData.UpperCase, 1)
                , new CharacterRule(EnglishCharacterData.LowerCase, 1)
                , new CharacterRule(EnglishCharacterData.Digit, 1));
    }


}
