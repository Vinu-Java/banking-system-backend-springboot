package com.bankingsystem.user.controller;

import com.bankingsystem.dto.requestdto.BalanceEnquiryRequestDTO;
import com.bankingsystem.dto.responsedto.LoginResponseDTO;
import com.bankingsystem.dto.responsedto.ProfileResponseDto;
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
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody BalanceEnquiryRequestDTO balanceEnquiryRequestDTO){
       return ResponseEntity.ok( userServiceInterface.login(balanceEnquiryRequestDTO));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ProfileResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userServiceInterface.getUserById(id));
    }
}
