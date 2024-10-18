package com.airtribe.LibraryManagement.service;

import com.airtribe.LibraryManagement.entity.BorrowedBook;
import com.airtribe.LibraryManagement.exception.InvalidDataException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;

import java.util.List;

public interface ILendingService {
    void borrowBook(String bookTitle, long patronId) throws ResourceNotFoundException, InvalidDataException;
    void returnBook(String title, long patronId) throws ResourceNotFoundException, InvalidDataException;
    List<BorrowedBook> getPatronHistory(long patronId) throws InvalidDataException, ResourceNotFoundException;
    List<BorrowedBook> getAllPatronHistory() throws ResourceNotFoundException;
}
