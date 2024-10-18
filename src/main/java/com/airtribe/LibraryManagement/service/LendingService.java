package com.airtribe.LibraryManagement.service;

import com.airtribe.LibraryManagement.entity.Book;
import com.airtribe.LibraryManagement.entity.Patron;
import com.airtribe.LibraryManagement.exception.InvalidDataException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;
import com.airtribe.LibraryManagement.model.BorrowedBook;
import com.airtribe.LibraryManagement.repository.BookRepository;
import com.airtribe.LibraryManagement.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LendingService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    private final List<BorrowedBook> borrowedBookList = new ArrayList<>();

    public void borrowBook(String bookTitle, long patronId) throws ResourceNotFoundException, InvalidDataException {
        Optional<Book> book = bookRepository.findByTitleIgnoreCase(bookTitle);
        Optional<Patron> patron = patronRepository.findById(patronId);

        if (!book.isPresent() || !patron.isPresent()) {
            throw new InvalidDataException("Invalid book or patronId!!");
        }
        Book dbBook = book.get();
        Patron dbPatron = patron.get();
        if(!dbBook.getIsAvailable()) {
            throw new ResourceNotFoundException("Book is currently unavailable.");
        }
        dbBook.setIsAvailable(false);
        bookRepository.save(dbBook);

        BorrowedBook borrowedBook = new BorrowedBook(dbBook, dbPatron, LocalDate.now(),null);
        borrowedBookList.add(borrowedBook);
    }

    public void returnBook(String title, long patronId) throws ResourceNotFoundException, InvalidDataException {
        Optional<Book> book = bookRepository.findByTitleIgnoreCase(title);
        Optional<Patron> patron = patronRepository.findById(patronId);

        if (!book.isPresent() || !patron.isPresent()) {
            throw new InvalidDataException("Invalid book or patronId!!");
        }
        if(borrowedBookList.size()>0)
        {
            for (BorrowedBook borrowedBook : borrowedBookList) {
                if (borrowedBook.getPatron().getPatronId() == patronId && borrowedBook.getBook().getTitle().equals(book.get().getTitle())) {
                    borrowedBookList.remove(borrowedBook);
                    book.get().setIsAvailable(true);
                    bookRepository.save(book.get());
                    break;
                }
                else {
                    throw new ResourceNotFoundException("No borrowed book found with title " + title);
                }
            }
        }
        else {
            throw new ResourceNotFoundException("No borrowed book found with title " + title);
        }
    }

    public List<BorrowedBook> getPatronHistory(long patronId) throws InvalidDataException, ResourceNotFoundException {
        Optional<Patron> patron = patronRepository.findById(patronId);
        if(!patron.isPresent())
        {
            throw new InvalidDataException("Invalid patronId!!");
        }
        if(borrowedBookList.size()<=0)
        {
            throw new ResourceNotFoundException("No borrowed books found for patron with id " + patronId);
        }
        List<BorrowedBook> patronList = new ArrayList<>();
        for (BorrowedBook book : borrowedBookList) {
            if (book.getPatron().getPatronId() == patronId) {
                patronList.add(book);
                break;
            }
            else {
                throw new ResourceNotFoundException("No borrowed books found for patron with id " + patronId);
            }
        }
        return patronList;
    }

    public List<BorrowedBook> getAllPatronHistory() throws ResourceNotFoundException {
        if(borrowedBookList.size()<=0 || borrowedBookList == null)
        {
            throw new ResourceNotFoundException("No borrowed book history");
        }
        return borrowedBookList;
    }
}
