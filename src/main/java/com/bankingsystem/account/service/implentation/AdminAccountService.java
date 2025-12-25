package com.bankingsystem.account.service.implentation;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.account.repository.AccountRepository;
import com.bankingsystem.account.service.AdminAccountServiceInterface;
import com.bankingsystem.dto.requestdto.AccountCreateRequestDTO;
import com.bankingsystem.dto.requestdto.DepositRequestDTO;
import com.bankingsystem.dto.requestdto.UpdateAccountRequestDTO;
import com.bankingsystem.dto.requestdto.WithdrawRequestDTO;
import com.bankingsystem.dto.responsedto.AccountResponseDTO;
import com.bankingsystem.dto.responsedto.DepositResponseDTO;
import com.bankingsystem.dto.responsedto.UpdateAccountResponseDTO;
import com.bankingsystem.dto.responsedto.WithdrawResponseDTO;
import com.bankingsystem.enums.AccountStatus;
import com.bankingsystem.enums.Role;
import com.bankingsystem.enums.TransactionType;
import com.bankingsystem.exception.AccountBalanceNotZeroException;
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
    public UpdateAccountResponseDTO updateAccount(Long accountId, UpdateAccountRequestDTO dto) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        User user = account.getUser();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        userRepository.save(user);

        return new UpdateAccountResponseDTO(
                account.getAccountNumber(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    @Override
    @Transactional
    public void deleteAccount(Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (account.getBalance() > 0) {
            throw new AccountBalanceNotZeroException(
                    "Account cannot be deleted because balance is greater than zero"
            );
        }

        accountRepository.delete(account);
    }
    @Override
    @Transactional
    public DepositResponseDTO deposit(DepositRequestDTO dto) {

        Account account = accountRepository.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (dto.getAmount() <= 0) {
            throw new InsufficientBalanceException("Deposit amount must be greater than zero");
        }

        account.setBalance(account.getBalance() + dto.getAmount());
        accountRepository.save(account);

        accountService.saveTransaction(account, dto.getAmount(), TransactionType.DEPOSIT);

        return new DepositResponseDTO(
                account.getAccountNumber(),
                dto.getAmount(),
                account.getBalance()
        );
    }

    @Override
    @Transactional
    public WithdrawResponseDTO withdraw(WithdrawRequestDTO dto) {

        Account account = accountRepository.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (dto.getAmount() <= 0) {
            throw new InsufficientBalanceException("Withdraw amount must be positive");
        }

        if (account.getBalance() < dto.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - dto.getAmount());
        accountRepository.save(account);

        accountService.saveTransaction(account, dto.getAmount(), TransactionType.WITHDRAW);

        return new WithdrawResponseDTO(
                account.getAccountNumber(),
                dto.getAmount(),
                account.getBalance()
        );
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
