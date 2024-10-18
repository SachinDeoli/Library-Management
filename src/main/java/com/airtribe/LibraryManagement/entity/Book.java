package com.airtribe.LibraryManagement.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
public class Book {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Id
    @Column(nullable = false, unique = true)
    private int isbn;

    @Column(nullable = false)
    private int publicationYear;

    @Column(nullable = false)
    private boolean isAvailable;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }
}
