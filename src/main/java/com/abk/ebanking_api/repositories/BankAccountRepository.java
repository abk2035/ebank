package com.abk.ebanking_api.repositories;

import com.abk.ebanking_api.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
