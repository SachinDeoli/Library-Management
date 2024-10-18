package com.airtribe.LibraryManagement.repository;

import com.airtribe.LibraryManagement.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
