package com.airtribe.LibraryManagement.service;

import com.airtribe.LibraryManagement.entity.Book;
import com.airtribe.LibraryManagement.exception.DataAlreadyExistException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException; // Custom exception
import com.airtribe.LibraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository _bookRepository;

    public Book addBook(Book book) throws DataAlreadyExistException {
        if (_bookRepository.existsById(book.getIsbn())) {
            throw new DataAlreadyExistException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        return (Book) _bookRepository.save(book);
    }

    public void removeBook(int isbn) throws ResourceNotFoundException {
        if (!_bookRepository.existsById(isbn)) {
            throw new ResourceNotFoundException("No book found with ISBN " + isbn);
        }
        _bookRepository.deleteById(isbn);
    }

    public Book updateBook(Book updatedBook) throws ResourceNotFoundException {
        Optional<Book> existingBook = _bookRepository.findById(updatedBook.getIsbn());
        if (!existingBook.isPresent()) {
            throw new ResourceNotFoundException("No book found with ISBN " + updatedBook.getIsbn());
        }
        Book book = existingBook.get();
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setPublicationYear(updatedBook.getPublicationYear());

        return (Book) _bookRepository.save(book);
    }

    public List<Book> getBookByTitle(String title) throws ResourceNotFoundException {
        Optional<List<Book>> existingBook = _bookRepository.findByTitleIgnoreCase(title);
        if(!existingBook.isPresent())
        {
            throw new ResourceNotFoundException("No book found with title " + title);
        }
        return existingBook.get();
    }

    public List<Book> getBookByAuthor(String author) throws ResourceNotFoundException {
        Optional<List<Book>> existingBook = _bookRepository.findByAuthorIgnoreCase(author);
        if(!existingBook.isPresent())
        {
            throw new ResourceNotFoundException("No book found with author " + author);
        }
        return existingBook.get();
    }

    public Book getBookByISBN(int isbn) throws ResourceNotFoundException {
        Optional<Book> existingBook = _bookRepository.findById(isbn);
        if(!existingBook.isPresent())
        {
            throw new ResourceNotFoundException("No book found with isbn " + isbn);
        }
        return existingBook.get();
    }
}
