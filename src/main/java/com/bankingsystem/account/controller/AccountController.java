package com.bankingsystem.account.controller;

import com.bankingsystem.account.service.AccountServiceInterface;
import com.bankingsystem.dto.requestdto.BalanceEnquiryRequestDTO;
import com.bankingsystem.dto.requestdto.TransferRequestDTO;
import com.bankingsystem.dto.responsedto.BalanceEnquiryResponseDTO;
import com.bankingsystem.dto.responsedto.TransferResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountServiceInterface accountServiceInterface;

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponseDTO> transfer(
            @Valid @RequestBody TransferRequestDTO dto) {

        TransferResponseDTO response =
                accountServiceInterface.transfer(dto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/balance")
    public ResponseEntity<BalanceEnquiryResponseDTO> balanceEnquiry(
            @Valid @RequestBody BalanceEnquiryRequestDTO balanceEnquiryRequestDTO) {

        BalanceEnquiryResponseDTO response =
                accountServiceInterface.balanceEnquiry(balanceEnquiryRequestDTO);
        return ResponseEntity.ok(response);
    }


}

