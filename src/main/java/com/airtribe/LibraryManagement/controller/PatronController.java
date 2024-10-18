package com.airtribe.LibraryManagement.controller;

import com.airtribe.LibraryManagement.entity.Patron;
import com.airtribe.LibraryManagement.exception.DataAlreadyExistException;
import com.airtribe.LibraryManagement.exception.ResourceNotFoundException;
import com.airtribe.LibraryManagement.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/patron")
public class PatronController {

    @Autowired
    private PatronService _patronService;

    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) throws DataAlreadyExistException {
        Patron _patron = _patronService.addPatron(patron);
        return new ResponseEntity<>(_patron, HttpStatus.CREATED);
    }

    @PutMapping("/{patronId}")
    public ResponseEntity<Patron> updatePatron(@PathVariable long patronId, @RequestBody Patron patron) throws ResourceNotFoundException {
        Patron _patron = _patronService.updatePatron(patronId, patron);
        return new ResponseEntity<>(_patron, HttpStatus.OK);
    }

    @DeleteMapping("/{patronId}")
    public ResponseEntity<Void> removePatron(@PathVariable int patronId) throws ResourceNotFoundException {
        _patronService.removePatron(patronId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons() throws ResourceNotFoundException {
        List<Patron> listOfPatron = _patronService.getAllPatrons();
        return listOfPatron != null || listOfPatron.size()>0 ? new ResponseEntity<>(listOfPatron, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{patronId}")
    public ResponseEntity<Patron> getPatronById(@PathVariable int patronId) throws ResourceNotFoundException {
        Patron listOfPatron = _patronService.getPatronById(patronId);
        return listOfPatron != null ? new ResponseEntity<>(listOfPatron, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
