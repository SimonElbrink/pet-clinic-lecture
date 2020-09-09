package se.lexicon.simon.petcliniclecture.service;

import se.lexicon.simon.petcliniclecture.entity.Pet;

import java.util.List;

public interface PetService {

    //findPetByOwner?
    //findPetBetweenDates?

    List<Pet> findAll();
    Pet findById(String petId);
    List<Pet> findByName(String petName);

    Pet save(Pet pet);
    Pet update(Pet pet);

    void delete(Pet pet);

}
