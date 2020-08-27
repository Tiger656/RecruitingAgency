package com.itechart.agency.Dto;

import lombok.Data;

@Data
public class AuthenticateRequestDto {
    private String email;
    private String password;
}
