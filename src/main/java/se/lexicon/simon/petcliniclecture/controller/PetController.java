package se.lexicon.simon.petcliniclecture.controller;

import org.springframework.http.ResponseEntity;
import se.lexicon.simon.petcliniclecture.dto.PetFormDto;
import se.lexicon.simon.petcliniclecture.entity.Pet;

public interface PetController {

    ResponseEntity<Pet> findById(String petId);

    ResponseEntity<?> find(final String type, final String value);

    ResponseEntity<Pet> save(PetFormDto pet);

    ResponseEntity<Pet> update (String id, Pet updated);

    //Delete?
}