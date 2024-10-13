package com.airtribe.LibraryManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Book {
    @Setter
    @Getter
    @Column(nullable = false)
    private String title;
    @Setter
    @Getter
    private String author;
    @Setter
    @Getter
    @Id
    @Column(nullable = false)
    private int isbn;
    @Setter
    @Getter
    private int publicationYear;


}
