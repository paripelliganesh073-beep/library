package com.digitallibrary.controller;

import com.digitallibrary.model.BorrowTransaction;
import com.digitallibrary.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@CrossOrigin(origins = "*")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping
    public List<BorrowTransaction> getAllTransactions() {
        return borrowService.getAllTransactions();
    }

    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        try {
            BorrowTransaction transaction = borrowService.issueBook(bookId, memberId);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/return/{transactionId}")
    public ResponseEntity<?> returnBook(@PathVariable Long transactionId) {
        try {
            BorrowTransaction transaction = borrowService.returnBook(transactionId);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/renew/{transactionId}")
    public ResponseEntity<?> renewBook(@PathVariable Long transactionId) {
        try {
            BorrowTransaction transaction = borrowService.renewBook(transactionId);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}