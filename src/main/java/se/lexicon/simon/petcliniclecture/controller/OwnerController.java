package se.lexicon.simon.petcliniclecture.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.simon.petcliniclecture.dto.OwnerFormDto;
import se.lexicon.simon.petcliniclecture.entity.Owner;
import se.lexicon.simon.petcliniclecture.exception.ResourceNotFoundException;

import javax.validation.Valid;

public interface OwnerController {
    String ALL = "all";
    String OWNER_ID = "ownerid";
    String FIRST_NAME = "firstname";
    String LAST_NAME = "lastname";

    /**
     *Method for Fetching Owner By Id.
     *
     * @param ownerId Given id to find.
     * @return found Owner.
     * @throws ResourceNotFoundException If given ID doesn't exist.
     */
    @GetMapping(path = "/{ownerId}")
    ResponseEntity<Owner> findById(@PathVariable String ownerId) throws ResourceNotFoundException;

    @GetMapping
    ResponseEntity<Object> find(
            @RequestParam(value = "type", defaultValue = ALL) String type,
            @RequestParam(value = "value", defaultValue = ALL) String value
    );

    @PostMapping
    ResponseEntity<Owner> create (@Valid @RequestBody OwnerFormDto dto);

    /**
     * Method for Updating a already existing Owner.
     *
     * @param updated DTO object of Owner to be updated.
     * @param ownerId PathVariable of OwnerId to be updated.
     * @return The Updated Entity
     * @throws IllegalArgumentException If Id's sent in doesn't match.
     * @throws ResourceNotFoundException If Owner was not found in the database.
     */
    @PutMapping("/{ownerId}")
    ResponseEntity<Owner> update(@RequestBody OwnerFormDto updated, @PathVariable String ownerId) throws IllegalArgumentException, ResourceNotFoundException;
}
