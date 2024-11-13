package com.accountopeningapplication.repositories;

import com.accountopeningapplication.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<Transaction, String> {

    @Query(value = "SELECT * FROM TRANSACTION_HISTORY t where t.account_id =:accountId", nativeQuery = true)
    Page<Transaction> findAllByUserAccount(@Param("accountId")String accountId, Pageable pageable);
}
