package com.bankingsystem.user.service;

import com.bankingsystem.dto.requestdto.AccountCredentialsDTO;
import com.bankingsystem.dto.responsedto.LoginResponseDTO;
import com.bankingsystem.dto.responsedto.ProfileResponseDto;
import jakarta.validation.Valid;

public interface UserServiceInterface {
    LoginResponseDTO login(@Valid AccountCredentialsDTO accountCredentialsDTO);

    ProfileResponseDto getUserById(Long id);
}
