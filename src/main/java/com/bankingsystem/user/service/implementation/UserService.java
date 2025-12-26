package com.bankingsystem.user.service.implementation;

import com.bankingsystem.account.entity.Account;
import com.bankingsystem.account.repository.AccountRepository;
import com.bankingsystem.dto.requestdto.AccountCredentialsDTO;
import com.bankingsystem.dto.responsedto.LoginResponseDTO;
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
    public LoginResponseDTO login(AccountCredentialsDTO accountCredentialsDTO) {

        Account account = accountRepository.findByAccountNumber(accountCredentialsDTO.getAccountNumber()).orElseThrow(
        ()-> new AccountNotFoundException("Incorrect account number"));

       User user = account.getUser();

       if(!user.getPassword().equals(accountCredentialsDTO.getPassword()))
       {
           throw new InvalidCredentialsException("Incorrect password");
       }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        loginResponseDTO.setUserId(user.getUserId());
        loginResponseDTO.setUserName(user.getName());
        loginResponseDTO.setRole(user.getRole());
        loginResponseDTO.setAccountNumber(account.getAccountNumber());
        loginResponseDTO.setEmail(user.getEmail());
        loginResponseDTO.setAccountType(account.getAccountType());
        loginResponseDTO.setAccountStatus(account.getStatus());

        return loginResponseDTO;


    }
}
