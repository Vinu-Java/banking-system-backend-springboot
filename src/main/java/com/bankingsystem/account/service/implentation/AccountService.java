package com.bankingsystem.account.service.implentation;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.account.repository.AccountRepository;
import com.bankingsystem.account.service.AccountServiceInterface;
import com.bankingsystem.dto.DepositRequestDTO;
import com.bankingsystem.dto.TransferRequestDTO;
import com.bankingsystem.dto.WithdrawRequestDTO;
import com.bankingsystem.enum_pack.TransactionType;
import com.bankingsystem.exception.AccountNotFoundException;
import com.bankingsystem.exception.InsufficientBalanceException;
import com.bankingsystem.transaction.entity.Transaction;
import com.bankingsystem.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class AccountService implements AccountServiceInterface {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public String transfer(TransferRequestDTO dto) {

        if (dto.getFromAccountNumber().equals(dto.getToAccountNumber())) {
            throw new AccountNotFoundException("From and To account cannot be same");
        }

        Account fromAccount = accountRepository
                .findByAccountNumber(dto.getFromAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found"));

        Account toAccount = accountRepository
                .findByAccountNumber(dto.getToAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found"));

        if (fromAccount.getBalance() < dto.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance for transfer");
        }

        fromAccount.setBalance(fromAccount.getBalance() - dto.getAmount());

        toAccount.setBalance(toAccount.getBalance() + dto.getAmount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        saveTransaction(fromAccount, dto.getAmount(), TransactionType.TRANSFER);

        saveTransaction(toAccount, dto.getAmount(), TransactionType.TRANSFER);

        return "Transfer completed successfully";
    }
    public void saveTransaction(Account account, Double amount, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setBalanceAfter(account.getBalance());
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

}

