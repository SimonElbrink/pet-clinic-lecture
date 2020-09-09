package se.lexicon.simon.petcliniclecture.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.simon.petcliniclecture.entity.Pet;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, String> {

    List<Pet> findAll();

    List<Pet> findAllByNameContainsIgnoreCase(String petName);
}
