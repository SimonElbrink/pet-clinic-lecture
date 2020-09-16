package se.lexicon.simon.petcliniclecture.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.simon.petcliniclecture.entity.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, String> {

    List<Owner> findAllByFirstName(String firstName);
    List<Owner> findAllByLastName(String LastName);

}
