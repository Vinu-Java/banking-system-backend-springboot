package com.bankingsystem.dto;

import com.bankingsystem.transaction.entity.Transaction;
import lombok.Data;

@Data
public class TransactionHistoryRequestDTO {

    private String accountNumber;
    private int pageNumber;
    private int size;

}
