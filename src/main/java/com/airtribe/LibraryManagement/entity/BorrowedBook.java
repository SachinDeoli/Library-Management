package com.airtribe.LibraryManagement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Patron patron;

    @Column(nullable = false)
    private LocalDateTime borrowDate;

    public Long getId() {
        return id;
    }

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

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public BorrowedBook(Book book, Patron patron, LocalDateTime borrowDate) {
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
    }
}
