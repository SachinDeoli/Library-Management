package com.airtribe.LibraryManagement.entity;

//import com.airtribe.LibraryManagement.entity.BorrowedBook;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Patron {

    @Column(nullable = false)
    private String name;

    @Id
    @Column(nullable = false)
    private long patronId;

    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    private List<BorrowedBook> borrowingHistory = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPatronId() {
        return patronId;
    }

    public List<BorrowedBook> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void setBorrowingHistory(BorrowedBook borrowedBook) {
        this.borrowingHistory.add(borrowedBook);
    }
}
