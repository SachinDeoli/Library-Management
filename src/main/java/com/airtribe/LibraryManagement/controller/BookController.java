package com.airtribe.LibraryManagement.controller;

import com.airtribe.LibraryManagement.entity.Book;
import com.airtribe.LibraryManagement.exception.DataAlreadyExistException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;
import com.airtribe.LibraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService _bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) throws DataAlreadyExistException {
        Book resultBook = _bookService.addBook(book);
        return new ResponseEntity<>(resultBook, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeBook(@RequestParam int isbn) throws ResourceNotFoundException {
        _bookService.removeBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) throws ResourceNotFoundException {
        Book updatedBook = _bookService.updateBook(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllAvailableBooks() throws ResourceNotFoundException {
        List<Book> book = _bookService.getAllAvailableBooks();
        return book != null || book.size()>0 ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/title")
    public ResponseEntity<List<Book>> getBookByTitle(@RequestParam String title) throws ResourceNotFoundException {
        List<Book> book = _bookService.getBookByTitle(title);
        return book != null && book.size()>0 ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam String author) throws ResourceNotFoundException {
        List<Book> book = _bookService.getBookByAuthor(author);
        return book != null && book.size()>0 ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/isbn")
    public ResponseEntity<Book> getBookByISBN(@RequestParam int isbn) throws ResourceNotFoundException {
        Book book = _bookService.getBookByISBN(isbn);
        return book != null ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
