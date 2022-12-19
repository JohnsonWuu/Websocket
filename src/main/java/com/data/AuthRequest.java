package com.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotEmpty(message = "User name should not be null or empty.")
    private String userName;

    @NotEmpty(message = "Password should not be null or empty.")
    private String userPassword;
}

