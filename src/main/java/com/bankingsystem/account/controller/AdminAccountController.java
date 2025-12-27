package com.bankingsystem.account.controller;

import com.bankingsystem.account.service.AccountServiceInterface;
import com.bankingsystem.account.service.AdminAccountServiceInterface;
import com.bankingsystem.dto.requestdto.*;
import com.bankingsystem.dto.responsedto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminAccountController {

    private final AccountServiceInterface accountService;
    private final AdminAccountServiceInterface adminAccountService;

    @PostMapping("/create")
    public ResponseEntity<AccountResponseDTO> createAccount(
            @Valid @RequestBody AccountCreateRequestDTO dto) {

        AccountResponseDTO response = adminAccountService.createAccount(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateAccount(
            @Valid @RequestBody UpdateAccountRequestDTO dto) {

        adminAccountService.updateAccount(dto);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/validate")
    public ResponseEntity<AccountNumberValidationResponseDTO> validateAccount(
            @Valid @RequestBody AccountNumberValidationRequestDTO dto) {

        AccountNumberValidationResponseDTO response =
                adminAccountService.validateAccountNumber(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAccount(@Valid @RequestBody DeleteRequestDto dto) {

        adminAccountService.deleteAccount(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponseDTO> deposit(
            @Valid @RequestBody DepositRequestDTO dto) {

        DepositResponseDTO response = adminAccountService.deposit(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawResponseDTO> withdraw(
            @Valid @RequestBody WithdrawRequestDTO dto) {

        WithdrawResponseDTO response = adminAccountService.withdraw(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponseDTO> transfer(
            @Valid @RequestBody TransferRequestDTO dto) {

        TransferResponseDTO response = accountService.transfer(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bank-details")
    public ResponseEntity<BankDashboardResponseDTO> bankDetails(){
        BankDashboardResponseDTO dto = adminAccountService.getDashboardData();
        return ResponseEntity.ok(dto);
    }

}
