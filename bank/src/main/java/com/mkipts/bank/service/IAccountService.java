package com.mkipts.bank.service;

import com.mkipts.bank.dto.request.MoneyTransferRequestDto;
import com.mkipts.bank.dto.request.UserRequestDto;
import com.mkipts.bank.dto.response.AccountResponseDto;
import com.mkipts.bank.dto.response.MoneyTransferResponseDto;

import java.util.List;

public interface IAccountService {
    public List<AccountResponseDto> getAllUserAccounts(Integer userId);

    UserRequestDto addAccount(UserRequestDto userRequestDto) ;

    AccountResponseDto deleteAccount(String accNo);

    Integer getUserIdByAccountNo(String accNo);


    MoneyTransferResponseDto amountTransfer(MoneyTransferRequestDto transferRequest, Integer senderUserId);

//    @Transactional
//    void transferAmount(MoneyTransferRequestDto transferRequest);


}
