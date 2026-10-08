package com.digitallibrary.service;

import com.digitallibrary.model.Book;
import com.digitallibrary.model.BorrowTransaction;
import com.digitallibrary.model.Member;
import com.digitallibrary.repository.BookRepository;
import com.digitallibrary.repository.BorrowTransactionRepository;
import com.digitallibrary.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BorrowService {

    private static final double FINE_PER_DAY = 10.0;

    @Autowired
    private BorrowTransactionRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<BorrowTransaction> getAllTransactions() {
        return borrowRepository.findAll();
    }

    public BorrowTransaction issueBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberId));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already issued to another member!");
        }

        long activeIssues = borrowRepository.countByMemberIdAndStatus(memberId, "ISSUED");
        if (activeIssues >= 2) {
            throw new RuntimeException("Borrow limit reached! Member already has 2 active books.");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        BorrowTransaction transaction = new BorrowTransaction();
        transaction.setBook(book);
        transaction.setMember(member);
        transaction.setIssueDate(LocalDate.now());
        transaction.setDueDate(LocalDate.now().plusDays(7));
        transaction.setStatus("ISSUED");
        transaction.setFineAmount(0.0);

        return borrowRepository.save(transaction);
    }

    public BorrowTransaction returnBook(Long transactionId) {
        BorrowTransaction transaction = borrowRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));

        if ("RETURNED".equalsIgnoreCase(transaction.getStatus())) {
            throw new RuntimeException("Book is already returned!");
        }

        LocalDate returnDate = LocalDate.now();
        transaction.setReturnDate(returnDate);
        transaction.setStatus("RETURNED");

        long lateDays = ChronoUnit.DAYS.between(transaction.getDueDate(), returnDate);
        if (lateDays > 0) {
            transaction.setFineAmount(lateDays * FINE_PER_DAY);
        } else {
            transaction.setFineAmount(0.0);
        }

        Book book = transaction.getBook();
        if (book != null) {
            book.setAvailable(true);
            bookRepository.save(book);
        }

        return borrowRepository.save(transaction);
    }

    public BorrowTransaction renewBook(Long transactionId) {
        BorrowTransaction transaction = borrowRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));

        if (!"ISSUED".equalsIgnoreCase(transaction.getStatus())) {
            throw new RuntimeException("Only currently issued books can be renewed!");
        }

        // 7 din extend karna
        if (transaction.getDueDate() != null) {
            transaction.setDueDate(transaction.getDueDate().plusDays(7));
        } else {
            transaction.setDueDate(LocalDate.now().plusDays(7));
        }

        return borrowRepository.save(transaction);
    }
}