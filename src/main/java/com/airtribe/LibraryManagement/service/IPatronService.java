package com.airtribe.LibraryManagement.service;

import com.airtribe.LibraryManagement.entity.Patron;
import com.airtribe.LibraryManagement.exception.DataAlreadyExistException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;

import java.util.List;

public interface IPatronService {

    Patron addPatron(Patron patron) throws DataAlreadyExistException;

    Patron updatePatron(long patronId, Patron patron) throws ResourceNotFoundException;

    void removePatron(long patronId) throws ResourceNotFoundException;

    List<Patron> getAllPatrons() throws ResourceNotFoundException;

    Patron getPatronById(long patronId) throws ResourceNotFoundException;
}
