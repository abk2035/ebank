package com.abk.ebanking_api.repositories;

import com.abk.ebanking_api.entities.AccountOperation;
import com.abk.ebanking_api.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,String> {
}
