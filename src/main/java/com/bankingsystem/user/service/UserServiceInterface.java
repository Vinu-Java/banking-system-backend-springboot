package com.bankingsystem.user.service;


import com.bankingsystem.dto.requestdto.BalanceEnquiryRequestDTO;
import com.bankingsystem.dto.responsedto.LoginResponseDTO;
import jakarta.validation.Valid;

public interface UserServiceInterface {
    LoginResponseDTO login(@Valid BalanceEnquiryRequestDTO accountCredentialsDTO);
}
