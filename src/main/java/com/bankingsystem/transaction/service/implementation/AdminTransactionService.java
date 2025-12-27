package com.bankingsystem.transaction.service.implementation;

import com.bankingsystem.dto.requestdto.AdminTransactionHistoryRequestDTO;
import com.bankingsystem.dto.responsedto.AdminTransactionResponseDTO;
import com.bankingsystem.dto.responsedto.TransactionResponseDTO;
import com.bankingsystem.enums.TransactionType;
import com.bankingsystem.transaction.entity.Transaction;
import com.bankingsystem.transaction.repository.TransactionRepository;
import com.bankingsystem.transaction.service.AdminTransactionServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AdminTransactionService implements AdminTransactionServiceInterface {


    TransactionRepository transactionRepository;


    @Override
    public Page<AdminTransactionResponseDTO> findAllAccountTransaction(
            AdminTransactionHistoryRequestDTO dto) {

        //start date
        LocalDateTime start = dto.getFromDate().atStartOfDay();
        //end date
        LocalDateTime end = dto.getToDate().atTime(23, 59, 59);

        Pageable pageable = PageRequest.of(
                dto.getPageNumber(),dto.getSize(),
                Sort.by("timestamp").descending());


        Page<Transaction> page = transactionRepository.findAllByTimestampBetween(
                start,
                end,
                pageable
        );

        return page.map(this::mapToAdminDTO);
    }

    @Override
    public Page<AdminTransactionResponseDTO> getTransactionsByRequiredType(
            AdminTransactionHistoryRequestDTO dto, TransactionType transactionType) {


        LocalDateTime start = dto.getFromDate().atStartOfDay();
        LocalDateTime end = dto.getToDate().atTime(23, 59, 59);

        Pageable pageable = PageRequest.of(
                dto.getPageNumber(),
                dto.getSize(),
                Sort.by("timestamp").descending()
        );

        Page<Transaction> page = transactionRepository.findAllForManager(
                start,
                end,
                transactionType,
                pageable
        );

        return page.map(this::mapToAdminDTO);
    }



    // Converting required transaction information

    private AdminTransactionResponseDTO mapToAdminDTO(Transaction t) {

        AdminTransactionResponseDTO dto = new AdminTransactionResponseDTO();

        dto.setTransactionId(t.getTransactionId());
        dto.setTransactionType(t.getType().name());
        dto.setAmount(t.getAmount());
        dto.setBalanceAfter(t.getBalanceAfter());
        dto.setTimestamp(t.getTimestamp());

        dto.setAccountNumber(t.getAccount().getAccountNumber());
        dto.setAccountType(t.getAccount().getAccountType().name());

        dto.setUserId(t.getAccount().getUser().getUserId());
        dto.setUserName(t.getAccount().getUser().getName());
        dto.setUserEmail(t.getAccount().getUser().getEmail());

        return dto;
    }

}
