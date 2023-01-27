package com.abk.ebanking_api.services;

import com.abk.ebanking_api.dtos.*;
import com.abk.ebanking_api.entities.BankAccount;
import com.abk.ebanking_api.entities.CurrentAccount;
import com.abk.ebanking_api.entities.Customer;
import com.abk.ebanking_api.exception.BalanceNotSufficientException;
import com.abk.ebanking_api.exception.BankAccountNotFoundException;
import com.abk.ebanking_api.exception.CustomerNotFoundException;

import java.util.List;


public interface BankAccountService {
       CustomerDTO saveCustomer(CustomerDTO customer) ;
       CustomerDTO getCustomer (Long customerId)throws CustomerNotFoundException ;
       CustomerDTO updateCustomer(CustomerDTO customerDTO);
       void  deleteCustomer(Long customerId) ;

       CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    List<BankAccountDTO> bankAccountList();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException ;
    List<AccountOperationDTO> accountHistory(String accountId);


    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
}
