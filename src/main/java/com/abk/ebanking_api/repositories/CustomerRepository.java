package com.abk.ebanking_api.repositories;

import com.abk.ebanking_api.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
