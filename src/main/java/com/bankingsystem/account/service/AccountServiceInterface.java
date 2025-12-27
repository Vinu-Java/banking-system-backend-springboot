package com.bankingsystem.account.service;




import com.bankingsystem.dto.requestdto.BalanceEnquiryRequestDTO;
import com.bankingsystem.dto.requestdto.TransferRequestDTO;
import com.bankingsystem.dto.responsedto.BalanceEnquiryResponseDTO;
import com.bankingsystem.dto.responsedto.TransferResponseDTO;

public interface AccountServiceInterface {
    TransferResponseDTO transfer(TransferRequestDTO dto);

    BalanceEnquiryResponseDTO balanceEnquiry(BalanceEnquiryRequestDTO dto);
}
