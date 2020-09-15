package com.itechart.agency.dto;

import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.Role;
import com.itechart.agency.entity.User;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {

private Long id;
    private String email;
    private Long agencyId;
    private List<Long> roleId;






    public static UserDto convertEntityToDto(User entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, UserDto.class);
    }

    public static User convertDtoToEntity(UserDto dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, User.class);
    }


}
