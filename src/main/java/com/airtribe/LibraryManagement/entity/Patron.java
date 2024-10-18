package com.airtribe.LibraryManagement.entity;

//import com.airtribe.LibraryManagement.model.BorrowedBook;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.util.List;


@Entity
public class Patron {

    @Column(nullable = false)
    private String name;

    @Id
    @Column(nullable = false)
    private long patronId;

//    @ElementCollection
//    private List<BorrowedBook> borrowedBookList;
//
//    public List<BorrowedBook> getBorrowedBookList() {
//        return borrowedBookList;
//    }
//
//    public void setBorrowedBookList(List<BorrowedBook> borrowedBookList) {
//        this.borrowedBookList = borrowedBookList;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPatronId() {
        return patronId;
    }

    public void setPatronId(long patronId) {
        this.patronId = patronId;
    }
}
