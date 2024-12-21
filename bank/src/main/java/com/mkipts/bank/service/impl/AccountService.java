package com.mkipts.bank.service.impl;

import com.mkipts.bank.dto.request.MoneyTransferRequestDto;
import com.mkipts.bank.dto.request.UserRequestDto;
import com.mkipts.bank.dto.response.AccountResponseDto;
import com.mkipts.bank.dto.response.MoneyTransferResponseDto;
import com.mkipts.bank.entity.*;
import com.mkipts.bank.repository.*;
import com.mkipts.bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public List<AccountResponseDto> getAllUserAccounts(Integer userId) {
        List<Account> accountList = accountRepository.findAccountsByUserId(userId);
        List<AccountResponseDto> accountResponceDtoList = new ArrayList<>();
        for (Account account : accountList){
            AccountResponseDto accountResponceDto = convertUserModelToUserDto(account);
            accountResponceDtoList.add(accountResponceDto);
        }


        return accountResponceDtoList;
    }



    private AccountResponseDto convertUserModelToUserDto(Account account) {
        AccountResponseDto responseDto= AccountResponseDto.builder()
                .accType(account.getAccountType())
                .accNo(account.getAccNo())
                .balance(account.getBalance())
                .openingDate(account.getOpeningDate())
                .build();
        return responseDto;
}




    @Override
    public UserRequestDto addAccount(UserRequestDto userRequestDto) {
        // Fetch the user entity from the database using userId
        User user = userRepository.findById(userRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new account entity
        Account account = new Account();

        //generating account number here
        State state = stateRepository.findStateByName(userRequestDto.getState());
        District district =  districtRepository.findDistrictByName(userRequestDto.getDistrict());
        City city =  cityRepository.findCityByName(userRequestDto.getCity());

        //getting last account number for increasing new by one
        String lastAccountnumber = accountRepository.findLastAccountNumber();

        account.setAccNo(String.format("%02d",state.getId())+String.format("%04d",district.getId())+String.format("%04d",city.getId())+String.format("%04d",Integer.valueOf(lastAccountnumber.substring(lastAccountnumber.length()-4))+1));

        Integer branchId = branchRepository.findIdByCityId(String.format("%04d", city.getId()));

        account.setBranchId(branchId);
        account.setBalance(userRequestDto.getBalance());
        account.setAccountType(userRequestDto.getAccountType());
        account.setOpeningDate(LocalDate.now());
        account.setUserId(user.getId());


        // Save the account entity to the database
        Account savedAccount = accountRepository.save(account);


        return userRequestDto;
    }




    @Override
    public AccountResponseDto deleteAccount(String accNo) {
        String account = String.valueOf(accountRepository.findIdByAccountNumber(accNo));

            accountRepository.deleteById(Integer.valueOf(account));
            System.out.println("User data with id: " + account + " deleted successfully");

        return null;
    }

    @Override
    public Integer getUserIdByAccountNo(String accNo) {
         Integer userId = accountRepository.findUserIdByAccountNumber(accNo);
        return userId;
    }


    @Override
    public MoneyTransferResponseDto amountTransfer(MoneyTransferRequestDto  transferRequest, Integer senderUserId) {
        Optional<User> senderUserOpt = userRepository.findById(senderUserId);
        Optional<Account> senderAccountNoOpt = accountRepository.findByAccNo(transferRequest.getSenderAccountNumber());
        Optional<Account> receiverAccountNoOpt = accountRepository.findByAccNo(transferRequest.getReceiverAccountNumber());

        if (senderUserOpt.isEmpty()) {
            throw new RuntimeException("Sender user not found");
        }

        if (senderAccountNoOpt.isEmpty() || receiverAccountNoOpt.isEmpty()) {
            throw new RuntimeException("Sender or receiver account not found");
        }

        Account senderAccount = senderAccountNoOpt.get();
        Account receiverAccount = receiverAccountNoOpt.get();

        // Check if the sender account belongs to the sender user
        if (!senderAccount.getUserId().equals(senderUserId)) {
            throw new RuntimeException("The sender account does not belong to the logged-in user");
        }

        BigDecimal transferAmount = transferRequest.getAmount();

        if (senderAccount.getBalance().compareTo(transferAmount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        senderAccount.setBalance(senderAccount.getBalance().subtract(transferAmount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(transferAmount));

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        Transaction transferTransaction = new Transaction();
        transferTransaction.setSenderId(senderAccount.getUserId());
        transferTransaction.setSenderAccountNumber(senderAccount.getAccNo());
        transferTransaction.setReceiverId(receiverAccount.getUserId());
        transferTransaction.setReceiverAccountNumber(receiverAccount.getAccNo());
        transferTransaction.setTransferAmount( transferRequest.getAmount());
        transferTransaction.setSenderBalance(senderAccount.getBalance());
        transferTransaction.setReceiverBalance(receiverAccount.getBalance());
        transferTransaction.setCreatedBy(senderUserId); // Set the appropriate user ID
        transferTransaction.setCreatedAt(LocalDateTime.now());
        transferTransaction.setUpdatedBy(senderUserId); // Set the appropriate user ID
        transferTransaction.setUpdatedAt(LocalDateTime.now());
        transactionRepository.save(transferTransaction);

        MoneyTransferResponseDto transferResponse = new MoneyTransferResponseDto();
        transferResponse.setSenderId(senderAccount.getUserId());
        transferResponse.setSenderAccountNumber(senderAccount.getAccNo());
        transferResponse.setReceiverId(receiverAccount.getUserId());
        transferResponse.setReceiverAccountNumber(receiverAccount.getAccNo());
        transferResponse.setTransferredAmount(transferRequest.getAmount());
        transferResponse.setSenderBalance(senderAccount.getBalance());
        transferResponse.setReceiverBalance(receiverAccount.getBalance());
        List<Transaction> transactions = transactionRepository.findBySenderId((long) senderAccount.getUserId());
        transferResponse.setTransactions(transactions);

        return transferResponse;
    }



}
