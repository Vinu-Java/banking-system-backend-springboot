package com.bankingsystem.user.controller;

import com.bankingsystem.dto.requestdto.AccountCredentialsDTO;
import com.bankingsystem.dto.responsedto.LoginResponseDTO;
import com.bankingsystem.user.service.UserServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    UserServiceInterface userServiceInterface;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AccountCredentialsDTO accountCredentialsDTO){

       return ResponseEntity.ok( userServiceInterface.login(accountCredentialsDTO));

    }
}
