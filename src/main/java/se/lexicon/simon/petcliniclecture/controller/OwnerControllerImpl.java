package se.lexicon.simon.petcliniclecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.simon.petcliniclecture.dto.OwnerFormDto;
import se.lexicon.simon.petcliniclecture.entity.Owner;
import se.lexicon.simon.petcliniclecture.exception.ResourceNotFoundException;
import se.lexicon.simon.petcliniclecture.repository.OwnerRepository;

import javax.validation.Valid;

/*
Todo - Document methods.
 */


@RestController
@RequestMapping(path = "/api/owners")
public class OwnerControllerImpl implements OwnerController {


    OwnerRepository ownerRepository;

    @Autowired
    public OwnerControllerImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     *Method for Fetching Owner By Id.
     *
     * @param ownerId Given id to find.
     * @return found Owner.
     * @throws ResourceNotFoundException If given ID doesn't exist.
     */
    @Override
    @GetMapping(path = "/{ownerId}")
    public ResponseEntity<Owner> findById(@PathVariable String ownerId) throws ResourceNotFoundException{

        Owner found = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Could not find Owner With ID: " + ownerId));

            return ResponseEntity.ok(found);
    }



    @Override
    @GetMapping
    public ResponseEntity<Object> find(
            @RequestParam(value = "type", defaultValue = ALL) String type,
            @RequestParam(value = "value", defaultValue = ALL) String value
    ){

        switch(type.toLowerCase().trim()){

            case OWNER_ID: return ResponseEntity.ok(ownerRepository.findById(value));

            case ALL: return ResponseEntity.ok(ownerRepository.findAll());

            case FIRST_NAME: return ResponseEntity.ok(ownerRepository.findAllByFirstName(value));

            case LAST_NAME: return ResponseEntity.ok(ownerRepository.findAllByLastName(value));

            default: throw new IllegalArgumentException("Not a Valid type: " + type);
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<Owner> create(@Valid @RequestBody OwnerFormDto dto){

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


    /**
     * Method for Updating a already existing Owner.
     *
     * @param updated DTO object of Owner to be updated.
     * @param ownerId PathVariable of OwnerId to be updated.
     * @return The Updated Entity
     * @throws IllegalArgumentException If Id's sent in doesn't match.
     * @throws ResourceNotFoundException If Owner was not found in the database.
     */
    @Override
    @PutMapping("/{ownerId}")
    public ResponseEntity<Owner> update(@RequestBody OwnerFormDto updated, @PathVariable String ownerId) throws IllegalArgumentException, ResourceNotFoundException{

        if (!updated.getOwnerId().equals(ownerId)) throw new IllegalArgumentException("ID does not match.");

        Owner original = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Could not find Owner With ID: " + ownerId));

        original.setFirstName(updated.getFirstName());
        original.setLastName(updated.getLastName());
        original.setAddress(updated.getAddress());
        original.setTelephone(updated.getTelephone());

        original = ownerRepository.save(original);

        return ResponseEntity.ok(original);
    }


}