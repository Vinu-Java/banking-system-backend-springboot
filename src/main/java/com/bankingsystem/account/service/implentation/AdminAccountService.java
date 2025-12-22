package com.bankingsystem.account.service.implentation;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.account.repository.AccountRepository;
import com.bankingsystem.account.service.AdminAccountServiceInterface;
import com.bankingsystem.dto.*;
import com.bankingsystem.enum_pack.AccountStatus;
import com.bankingsystem.enum_pack.Role;
import com.bankingsystem.enum_pack.TransactionType;
import com.bankingsystem.exception.AccountNotFoundException;
import com.bankingsystem.exception.InsufficientBalanceException;
import com.bankingsystem.user.entity.User;
import com.bankingsystem.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminAccountService implements AdminAccountServiceInterface {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountService accountService;
    @Override
    public AccountResponseDTO createAccount(AccountCreateRequestDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setRole(Role.USER);

        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getInitialBalance());
        account.setStatus(AccountStatus.ACTIVE);
        account.setUser(user);

        user.setAccount(account);
        userRepository.save(user);


        return mapToResponse(account);
    }

    @Override
    public String updateAccount(Long accountId, UpdateAccountRequestDTO dto) {
        User user = userRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());

        userRepository.save(user);
        return "Account updated successfully";
    }

    @Override
    public String deleteAccount(Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        accountRepository.delete(account);
        return "Account deleted successfully";
    }

    @Override
    @Transactional
    public String deposit(DepositRequestDTO dto) {
        Account account = accountRepository.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (dto.getAmount() <= 0) {
            throw new InsufficientBalanceException("Deposit amount must be greater than zero");
        }

        account.setBalance(account.getBalance() + dto.getAmount());
        accountRepository.save(account);

        accountService.saveTransaction(account, dto.getAmount(), TransactionType.DEPOSIT);

        return "Deposit successful";
    }

    @Override
    @Transactional
    public String withdraw(WithdrawRequestDTO dto) {
        Account account = accountRepository.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (dto.getAmount() <= 0) {
            throw new InsufficientBalanceException("Withdraw amount must be greater than zero");
        }

        if (account.getBalance() < dto.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - dto.getAmount());
        accountRepository.save(account);

        accountService.saveTransaction(account, dto.getAmount(), TransactionType.WITHDRAW);

        return "Withdraw successful";
    }
    private String generateAccountNumber() {
        return String.valueOf(
                Math.abs(UUID.randomUUID().getMostSignificantBits())
        ).substring(0, 10);
    }
    private AccountResponseDTO mapToResponse(Account account) {
        AccountResponseDTO response = new AccountResponseDTO();
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountType(account.getAccountType());
        response.setBalance(account.getBalance());
        response.setStatus(account.getStatus());
        return response;
    }

}
