package com.airtribe.LibraryManagement.repository;

import com.airtribe.LibraryManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<List<Book>> findByTitleIgnoreCase(String title);

    Optional<List<Book>> findByAuthorIgnoreCase(String author);
}
