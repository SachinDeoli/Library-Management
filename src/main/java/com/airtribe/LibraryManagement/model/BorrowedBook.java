package com.airtribe.LibraryManagement.model;

import com.airtribe.LibraryManagement.entity.Book;
import com.airtribe.LibraryManagement.entity.Patron;
import jakarta.persistence.*;

import java.time.LocalDate;

@Embeddable
public class BorrowedBook {
    private Book book;
    private Patron patron;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowedBook(Book book, Patron patron, LocalDate borrowDate, LocalDate returnDate) {
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
