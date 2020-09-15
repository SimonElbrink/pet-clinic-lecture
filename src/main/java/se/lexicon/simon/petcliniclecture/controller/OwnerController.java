package se.lexicon.simon.petcliniclecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.simon.petcliniclecture.dto.OwnerFromDto;
import se.lexicon.simon.petcliniclecture.entity.Owner;
import se.lexicon.simon.petcliniclecture.repository.OwnerRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/owners")
public class OwnerController {


    OwnerRepository ownerRepository;

    @Autowired
    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @PostMapping
    public ResponseEntity<Owner> create (@Valid @RequestBody OwnerFromDto dto){

        if (dto.getOwnerId() != null && !dto.getOwnerId().equals("")){
            throw new IllegalArgumentException("ID is Present, not allowed.");
        }

        //Preparing
        Owner owner = new Owner(dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getTelephone());


        //Persisting
        owner = ownerRepository.save(owner);

        //Return Result
        return ResponseEntity.status(HttpStatus.CREATED).body(owner);
    }
}