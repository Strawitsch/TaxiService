package com.taxi.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DriverRequest {
    @NotBlank
    private String name;
    @Email
    private String email;
    private String phone;
    private String licenseNumber;
}