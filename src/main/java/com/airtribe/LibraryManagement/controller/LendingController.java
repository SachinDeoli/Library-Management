package com.airtribe.LibraryManagement.controller;

import com.airtribe.LibraryManagement.exception.InvalidDataException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;
import com.airtribe.LibraryManagement.entity.BorrowedBook;
import com.airtribe.LibraryManagement.service.LendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/lending")
public class LendingController {

    @Autowired
    private LendingService _lendingService;

    @PostMapping("/checkout/{bookTitle}/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable String bookTitle, @PathVariable long patronId) throws InvalidDataException, ResourceNotFoundException {
        _lendingService.borrowBook(bookTitle, patronId);
        return new ResponseEntity<>("Book borrowed successfully.",HttpStatus.OK);
    }

    @PostMapping("/return/{title}/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable String title, @PathVariable long patronId) throws ResourceNotFoundException, InvalidDataException {
        _lendingService.returnBook(title,patronId);
        return new ResponseEntity<>("Book returned successfully.", HttpStatus.OK);
    }

    @GetMapping("/history/{patronId}")
    public ResponseEntity<?> getPatronHistory(@PathVariable long patronId) throws InvalidDataException, ResourceNotFoundException {
       List<BorrowedBook> borrowedBookList = _lendingService.getPatronHistory(patronId);
       return borrowedBookList!=null && borrowedBookList.size()>0 ? new ResponseEntity<>(borrowedBookList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?>GetAllPatronHistory() throws InvalidDataException, ResourceNotFoundException {
       List<BorrowedBook> borrowedBookList = _lendingService.getAllPatronHistory();
       return borrowedBookList!=null && borrowedBookList.size()>0 ? new ResponseEntity<>(borrowedBookList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
