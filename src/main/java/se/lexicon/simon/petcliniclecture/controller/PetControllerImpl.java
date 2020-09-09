package se.lexicon.simon.petcliniclecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.simon.petcliniclecture.entity.Pet;
import se.lexicon.simon.petcliniclecture.service.PetService;

@RestController
public class PetControllerImpl implements PetController{

    public static final String ALL = "all";
    public static final String PET_ID = "petid";
    public static final String PET_NAME = "petname";

    private PetService petService;

    @Autowired
    public PetControllerImpl(PetService petService) {
        this.petService = petService;
    }


    @Override
    @GetMapping("/api/pets/{petId}")
    public ResponseEntity<Pet> findById(@PathVariable String petId) {

        return ResponseEntity.ok(petService.findById(petId));
    }

    @Override
    @GetMapping("/api/pets")
    public ResponseEntity<?> find(
            @RequestParam(name = "type", defaultValue = ALL) String type,
            @RequestParam(name = "value", defaultValue = ALL) String value) {

        switch (type.toLowerCase().trim()){

            case PET_ID:
                return ResponseEntity.ok(petService.findById(value));

            case PET_NAME:
                return ResponseEntity.ok(petService.findByName(value));

            case ALL:
                return ResponseEntity.ok(petService.findAll());

            default:
                return ResponseEntity.badRequest().body("Not A Valid Type: " + type);

        }
    }

    @Override
    @PostMapping("/api/pets")
    public ResponseEntity<Pet> save(@RequestBody Pet pet) {

        return ResponseEntity.status(HttpStatus.CREATED).body(petService.save(pet));
    }

    @Override
    public ResponseEntity<Pet> update(String id, Pet updated) {
        return null;
    }
}
