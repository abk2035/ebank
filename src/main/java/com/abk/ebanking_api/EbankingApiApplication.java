package com.abk.ebanking_api;

import com.abk.ebanking_api.dtos.BankAccountDTO;
import com.abk.ebanking_api.entities.AccountOperation;
import com.abk.ebanking_api.entities.CurrentAccount;
import com.abk.ebanking_api.entities.Customer;
import com.abk.ebanking_api.entities.SavingAccount;
import com.abk.ebanking_api.enums.AccountStatus;
import com.abk.ebanking_api.enums.OperationType;
import com.abk.ebanking_api.exception.CustomerNotFoundException;
import com.abk.ebanking_api.repositories.AccountOperationRepository;
import com.abk.ebanking_api.repositories.BankAccountRepository;
import com.abk.ebanking_api.repositories.CustomerRepository;
import com.abk.ebanking_api.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingApiApplication implements CommandLineRunner {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	BankAccountRepository bankAccountRepository;
	@Autowired
	AccountOperationRepository accountOperationRepository;
	@Autowired
	BankAccountService bankAccountService ;

	public static void main(String[] args) {
		SpringApplication.run(EbankingApiApplication.class, args);
		System.out.println("hello wppush rld");
	}





	@Override
	public void run(String... args) throws Exception {
		Stream.of("Hassan","Yassine","Aicha").forEach(name->{
			Customer customer=new Customer();
			customer.setName(name);
			customer.setEmail(name+"@gmail.com");
			customerRepository.save(customer);
		});

		System.out.println("customer create");

		bankAccountService.listCustomers().forEach(customer->{
			try {
				bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
				bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

			} catch (CustomerNotFoundException e) {
				e.printStackTrace();
			}

		});


		System.out.println("accout create");

		bankAccountRepository.findAll().forEach(acc->{
			for (int i = 0; i <10 ; i++) {
				AccountOperation accountOperation=new AccountOperation();
				accountOperation.setOperationDate(new Date());
				accountOperation.setAmount(Math.random()*12000);
				accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
				accountOperation.setBankAccount(acc);
				accountOperationRepository.save(accountOperation);
			}

	});

		};



}
