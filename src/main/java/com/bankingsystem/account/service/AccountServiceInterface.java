package com.bankingsystem.account.service;


import com.bankingsystem.dto.AccountNumberAndPasswordDTO;
import com.bankingsystem.dto.TransferRequestDTO;
import jakarta.validation.Valid;

public interface AccountServiceInterface {
    String transfer( TransferRequestDTO dto);

    String balanceEnquiry(AccountNumberAndPasswordDTO accountNumberAndPasswordDTO);
}
