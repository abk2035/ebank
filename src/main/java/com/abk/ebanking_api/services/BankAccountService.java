package com.abk.ebanking_api.services;

import com.abk.ebanking_api.dtos.CustomerDTO;
import com.abk.ebanking_api.entities.BankAccount;
import com.abk.ebanking_api.entities.CurrentAccount;
import com.abk.ebanking_api.entities.Customer;
import com.abk.ebanking_api.exception.BalanceNotSufficientException;
import com.abk.ebanking_api.exception.BankAccountNotFoundException;
import com.abk.ebanking_api.exception.CustomerNotFoundException;

import java.util.List;


public interface BankAccountService {
   CustomerDTO saveCustomer(CustomerDTO customer) ;
   CustomerDTO getCustomer ( Long customerId)throws CustomerNotFoundException ;
   CustomerDTO updateCustomer(CustomerDTO customerDTO);
   void  deleteCustomer(Long customerId) ;
   CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    List<BankAccount> bankAccountList ();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
}
