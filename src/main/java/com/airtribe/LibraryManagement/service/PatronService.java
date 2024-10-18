package com.airtribe.LibraryManagement.service;

import com.airtribe.LibraryManagement.entity.Book;
import com.airtribe.LibraryManagement.entity.Patron;
import com.airtribe.LibraryManagement.exception.DataAlreadyExistException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;
import com.airtribe.LibraryManagement.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    @Autowired
    private PatronRepository _patronRepository;

    public Patron addPatron(Patron patron) throws DataAlreadyExistException {
        if (_patronRepository.existsById(patron.getPatronId())) {
            throw new DataAlreadyExistException("Patron with ID " + patron.getPatronId() + " already exists.");
        }
        return (Patron) _patronRepository.save(patron);
    }

    public Patron updatePatron(long patronId, Patron patron) throws ResourceNotFoundException {
        Optional<Patron> _patron = _patronRepository.findById(patronId);
        if (!_patron.isPresent()) {
            throw new ResourceNotFoundException("No Patron found with ID " + patronId);
        }
        Patron updatedPatron = _patron.get();
        updatedPatron.setName(patron.getName());
        return (Patron) _patronRepository.save(updatedPatron);
    }


    public void removePatron(long patronId) throws ResourceNotFoundException {
        Optional<Patron> _patron = _patronRepository.findById(patronId);
        if (!_patron.isPresent()) {
            throw new ResourceNotFoundException("No Patron found with ID " + patronId);
        }
       _patronRepository.deleteById(patronId);
    }

    public List<Patron> getAllPatrons() throws ResourceNotFoundException {
        List<Patron> listofPatrons = _patronRepository.findAll();
        if(listofPatrons == null || listofPatrons.size()<=0)
        {
            throw new ResourceNotFoundException("No patrons found!");
        }
        return listofPatrons;
    }

    public Patron getPatronById(long patronId) throws ResourceNotFoundException {
        Optional<Patron> patron = _patronRepository.findById(patronId);
        if(!patron.isPresent())
        {
            throw new ResourceNotFoundException("No patron found with id " + patronId);
        }
        return patron.get();
    }
}
