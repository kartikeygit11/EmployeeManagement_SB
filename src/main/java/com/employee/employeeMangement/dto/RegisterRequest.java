package com.employee.employeeMangement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data

public class RegisterRequest {
    private String username;
    private String email;
    private Set<String> role;
    private String password;
}
