package se.lexicon.simon.petcliniclecture.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.simon.petcliniclecture.entity.Pet;

public interface PetRepository extends CrudRepository<Pet, String> {
}
