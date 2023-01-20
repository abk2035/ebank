package com.abk.ebanking_api;

import com.abk.ebanking_api.entities.AccountOperation;
import com.abk.ebanking_api.entities.CurrentAccount;
import com.abk.ebanking_api.entities.Customer;
import com.abk.ebanking_api.entities.SavingAccount;
import com.abk.ebanking_api.enums.AccountStatus;
import com.abk.ebanking_api.enums.OperationType;
import com.abk.ebanking_api.repositories.AccountOperationRepository;
import com.abk.ebanking_api.repositories.BankAccountRepository;
import com.abk.ebanking_api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
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

		customerRepository.findAll().forEach(cust->{
			CurrentAccount currentAccount =new CurrentAccount();
			currentAccount.setId(UUID.randomUUID().toString());
			currentAccount.setBalance(Math.random()*90000);
			currentAccount.setCreatedAt(new Date());
			currentAccount.setStatus(AccountStatus.CREATED);
			currentAccount.setCustomer(cust);
			currentAccount.setOverDraft(9000);
			bankAccountRepository.save(currentAccount);

			SavingAccount savingAccount =new SavingAccount();
			savingAccount.setId(UUID.randomUUID().toString());
			savingAccount.setBalance(Math.random()*90000);
			savingAccount.setCreatedAt(new Date());
			savingAccount.setStatus(AccountStatus.CREATED);
			savingAccount.setCustomer(cust);
			savingAccount.setInterestRate(5.5);
			bankAccountRepository.save(savingAccount);

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
