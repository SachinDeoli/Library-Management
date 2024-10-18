package com.airtribe.LibraryManagement.service;

import com.airtribe.LibraryManagement.entity.Book;
import com.airtribe.LibraryManagement.exception.DataAlreadyExistException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException; // Custom exception
import com.airtribe.LibraryManagement.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository _bookRepository;

    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Override
    public Book addBook(Book book) throws DataAlreadyExistException {
        if (_bookRepository.existsById(book.getIsbn())) {
            logger.info("Book with ISBN " + book.getIsbn() + " already exists.");
            throw new DataAlreadyExistException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        return (Book) _bookRepository.save(book);
    }

    @Override
    public void removeBook(int isbn) throws ResourceNotFoundException {
        if (!_bookRepository.existsById(isbn)) {
            logger.info("No book found with ISBN " + isbn);
            throw new ResourceNotFoundException("No book found with ISBN " + isbn);
        }
        _bookRepository.deleteById(isbn);
    }

    @Override
    public Book updateBook(int isbn, Book updatedBook) throws ResourceNotFoundException {
        Optional<Book> existingBook = _bookRepository.findById(isbn);
        if (!existingBook.isPresent()) {
            logger.info("No book found with ISBN " + isbn);
            throw new ResourceNotFoundException("No book found with ISBN " + isbn);
        }
        Book book = existingBook.get();
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setIsAvailable(updatedBook.getIsAvailable());

        return _bookRepository.save(book);
    }

    @Override
    public Book getBookByTitle(String title) throws ResourceNotFoundException {
        Optional<Book> existingBook = _bookRepository.findByTitleIgnoreCase(title);
        if(!existingBook.isPresent())
        {
            logger.info("No book found with title " + title);
            throw new ResourceNotFoundException("No book found with title " + title);
        }
        return existingBook.get();
    }

    @Override
    public List<Book> getBookByAuthor(String author) throws ResourceNotFoundException {
        Optional<List<Book>> existingBook = _bookRepository.findByAuthorIgnoreCase(author);
        if(!existingBook.isPresent() || existingBook.get().size()<=0)
        {
            logger.info("No book found with author " + author);
            throw new ResourceNotFoundException("No book found with author " + author);
        }
        return existingBook.get();
    }

    @Override
    public Book getBookByISBN(int isbn) throws ResourceNotFoundException {
        Optional<Book> existingBook = _bookRepository.findById(isbn);
        if(!existingBook.isPresent())
        {
            logger.info("No book found with isbn " + isbn);
            throw new ResourceNotFoundException("No book found with isbn " + isbn);
        }
        return existingBook.get();
    }

    @Override
    public List<Book> getAllAvailableBooks(boolean isAvailable) throws ResourceNotFoundException {
        List<Book> listOfBooks = _bookRepository.findByIsAvailable(isAvailable);
        if(listOfBooks == null || listOfBooks.size()<=0)
        {
            logger.info("No available books found");
            throw new ResourceNotFoundException("No available books found");
        }
        return listOfBooks;
    }

    @Override
    public List<Book> getAllBooks() throws ResourceNotFoundException {
        List<Book> listOfBooks = _bookRepository.findAll();
        if(listOfBooks == null || listOfBooks.size()<=0)
        {
            logger.info("No books found");
            throw new ResourceNotFoundException("No books found");
        }
        return listOfBooks;
    }
}
