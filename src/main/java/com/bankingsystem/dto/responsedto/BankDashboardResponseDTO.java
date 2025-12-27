package com.bankingsystem.dto.responsedto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BankDashboardResponseDTO {

    //Basic details

    private long totalAccounts;

    private double totalBalance;

    private long todayTransactions;

    private double todayDeposits;

    private double todayWithdrawals;

    //Account types details

    private long savingsAccounts;

    private long currentAccounts;

    //Account staus details

    private long activeAccounts;

    private long blockedAccounts;


}
