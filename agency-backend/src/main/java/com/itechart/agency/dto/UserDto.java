package com.itechart.agency.Dto;

import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.Role;
import com.itechart.agency.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String email;
    private String password;
    private String agencyName;
    private List<String> roleName;
    private String city;

    public UserDto(String email, String password, String agencyName, List<String> roleName) {
        this.email = email;
        this.password = password;
        this.agencyName = agencyName;
        this.roleName = roleName;
    }

    public static UserDto convertToDto(User user) {
        return new UserDto(
                user.getEmail(),
                user.getPassword(),
                user.getAgency().getName(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toList())
        );
    }


}
