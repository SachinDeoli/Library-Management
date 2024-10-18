package com.airtribe.LibraryManagement.service;

import com.airtribe.LibraryManagement.entity.Book;
import com.airtribe.LibraryManagement.entity.Patron;
import com.airtribe.LibraryManagement.exception.InvalidDataException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;
import com.airtribe.LibraryManagement.entity.BorrowedBook;
import com.airtribe.LibraryManagement.repository.BookRepository;
import com.airtribe.LibraryManagement.repository.PatronRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LendingService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    private final Logger logger = LoggerFactory.getLogger(LendingService.class);

    public void borrowBook(String bookTitle, long patronId) throws ResourceNotFoundException, InvalidDataException {
        Optional<Book> book = bookRepository.findByTitleIgnoreCase(bookTitle);
        Optional<Patron> patron = patronRepository.findById(patronId);

        if (!book.isPresent() || !patron.isPresent()) {
            logger.info("Invalid book or patronId!!");
            throw new InvalidDataException("Invalid book or patronId!!");
        }
        Book dbBook = book.get();
        Patron dbPatron = patron.get();
        if(!dbBook.getIsAvailable()) {
            logger.info("Book is currently unavailable.");
            throw new ResourceNotFoundException("Book is currently unavailable.");
        }
        BorrowedBook borrowedBook = new BorrowedBook(dbBook, dbPatron, LocalDateTime.now());
        dbBook.setIsAvailable(false);
        bookRepository.save(dbBook);

        dbPatron.setBorrowingHistory(borrowedBook);
        patronRepository.save(dbPatron);
    }

    public void returnBook(String title, long patronId) throws ResourceNotFoundException, InvalidDataException {
        Optional<Book> book = bookRepository.findByTitleIgnoreCase(title);
        Optional<Patron> patron = patronRepository.findById(patronId);

        if (!book.isPresent() || !patron.isPresent()) {
            logger.info("Invalid book or patronId!!");
            throw new InvalidDataException("Invalid book or patronId!!");
        }

        List<BorrowedBook> borrowedBookList = patron.get().getBorrowingHistory();
        if(borrowedBookList.size()>0)
        {
            for (BorrowedBook borrowedBook : borrowedBookList) {
                if (borrowedBook.getBook().getTitle().equals(book.get().getTitle())) {
                    borrowedBookList.remove(borrowedBook);
                    book.get().setIsAvailable(true);
                    bookRepository.save(book.get());
                    patronRepository.save(patron.get());
                    break;
                }
                else {
                    logger.info("No borrowed book found with title " + title);
                    throw new ResourceNotFoundException("No borrowed book found with title " + title);
                }
            }
        }
        else {
            logger.info("No borrowed book found with title " + title);
            throw new ResourceNotFoundException("No borrowed book found with title " + title);
        }
    }

    public List<BorrowedBook> getPatronHistory(long patronId) throws InvalidDataException, ResourceNotFoundException {
        Optional<Patron> patron = patronRepository.findById(patronId);
        if(patron.get().getBorrowingHistory().size()<=0)
        {
            logger.info("No borrowed books found for patron with id " + patronId);
            throw new ResourceNotFoundException("No borrowed books found for patron with id " + patronId);
        }
        return patron.get().getBorrowingHistory();
    }

    public List<BorrowedBook> getAllPatronHistory() throws ResourceNotFoundException {
        List<Patron> patrons = patronRepository.findAll();
        List<BorrowedBook> borrowedBookList = new ArrayList<>();
        for (Patron patron : patrons) {
            borrowedBookList.addAll(patron.getBorrowingHistory());
        }
        if(borrowedBookList.size()<=0)
        {
            logger.info("No borrowed books found for any patron");
            throw new ResourceNotFoundException("No borrowed books found for any patron");
        }
        return borrowedBookList;
    }
}
