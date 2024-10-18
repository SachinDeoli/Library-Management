package com.airtribe.LibraryManagement.service;

import com.airtribe.LibraryManagement.entity.Book;
import com.airtribe.LibraryManagement.exception.DataAlreadyExistException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;

import java.util.List;

public interface IBookService {
    Book addBook(Book book) throws DataAlreadyExistException;
    void removeBook(int isbn) throws ResourceNotFoundException;
    Book updateBook(int isbn, Book updatedBook) throws ResourceNotFoundException;
    Book getBookByTitle(String title) throws ResourceNotFoundException;
    List<Book> getAllBooks() throws ResourceNotFoundException;
    List<Book> getBookByAuthor(String author) throws ResourceNotFoundException;
    Book getBookByISBN(int isbn) throws ResourceNotFoundException;
    List<Book> getAllAvailableBooks(boolean isAvailable) throws ResourceNotFoundException;
}
