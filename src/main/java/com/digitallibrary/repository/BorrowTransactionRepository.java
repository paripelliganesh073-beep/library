package com.digitallibrary.repository;

import com.digitallibrary.model.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction, Long> {

    long countByMemberIdAndStatus(Long memberId, String status);
}