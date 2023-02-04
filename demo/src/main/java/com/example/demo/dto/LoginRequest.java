package com.example.demo.dto;

import com.example.demo.annotations.EmailExtended;
import com.example.demo.annotations.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    @EmailExtended
    private String email;
    @Password
    private String password;
}
