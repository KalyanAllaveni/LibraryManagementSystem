package com.example.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library.Entities.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions,Long>{
    
}
