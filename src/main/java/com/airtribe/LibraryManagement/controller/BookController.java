package com.airtribe.LibraryManagement.controller;

import com.airtribe.LibraryManagement.entity.Book;
import com.airtribe.LibraryManagement.exception.DataAlreadyExistException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;
import com.airtribe.LibraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/book")
public class BookController {

    @Autowired
    private BookService _bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) throws DataAlreadyExistException {
        Book resultBook = _bookService.addBook(book);
        return new ResponseEntity<>(resultBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> removeBook(@PathVariable int isbn) throws ResourceNotFoundException {
        _bookService.removeBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable int isbn, @RequestBody Book book) throws ResourceNotFoundException {
        Book updatedBook = _bookService.updateBook(isbn, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllAvailableBooks() throws ResourceNotFoundException {
        List<Book> book = _bookService.getAllAvailableBooks();
        return book != null || book.size()>0 ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) throws ResourceNotFoundException {
        Book book = _bookService.getBookByTitle(title);
        return book != null ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author) throws ResourceNotFoundException {
        List<Book> book = _bookService.getBookByAuthor(author);
        return book != null && book.size()>0 ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByISBN(@PathVariable int isbn) throws ResourceNotFoundException {
        Book book = _bookService.getBookByISBN(isbn);
        return book != null ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
