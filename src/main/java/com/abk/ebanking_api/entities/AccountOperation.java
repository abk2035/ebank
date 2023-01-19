package com.abk.ebanking_api.entities;

import com.abk.ebanking_api.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;

    @Enumerated(EnumType.STRING)
    private OperationType type ;
    private Date operationDate;
    private double amount;
    @ManyToOne
    private BankAccount bankAccount;
    private String description ;
}
