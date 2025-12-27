package com.bankingsystem.user.service.implementation;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.account.repository.AccountRepository;
import com.bankingsystem.dto.requestdto.BalanceEnquiryRequestDTO;
import com.bankingsystem.dto.responsedto.LoginResponseDTO;
import com.bankingsystem.dto.responsedto.ProfileResponseDto;
import com.bankingsystem.enums.AccountStatus;
import com.bankingsystem.exception.AccountNotFoundException;
import com.bankingsystem.exception.InvalidCredentialsException;
import com.bankingsystem.user.entity.User;
import com.bankingsystem.user.repository.UserRepository;
import com.bankingsystem.user.service.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

    AccountRepository accountRepository;
    UserRepository userRepository;

    @Override
    public LoginResponseDTO login(BalanceEnquiryRequestDTO accountCredentialsDTO) {

        Account account = accountRepository
                .findByAccountNumber(accountCredentialsDTO.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Incorrect account number"));

        if (account.getStatus() == AccountStatus.BLOCKED) {
            throw new InvalidCredentialsException("Account is blocked");
        }

        User user = account.getUser();

        if (!accountCredentialsDTO.getPassword().equals(user.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password");
        }

        LoginResponseDTO dto = new LoginResponseDTO();

        dto.setUserId(user.getUserId());
        dto.setUserName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        dto.setAccountNumber(account.getAccountNumber());
        dto.setAccountType(account.getAccountType());
        dto.setAccountStatus(account.getStatus());

        return dto;
    }

    @Override
    public ProfileResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Invalid user id"));

        Account account = user.getAccount(); // association works here

        ProfileResponseDto dto = new ProfileResponseDto();

        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());

        dto.setAccountNumber(account.getAccountNumber());
        dto.setAccountType(account.getAccountType().name());
        dto.setBalance(account.getBalance());
        dto.setStatus(account.getStatus().name());

        return dto;
    }

}
