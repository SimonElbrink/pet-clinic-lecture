package se.lexicon.simon.petcliniclecture.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.simon.petcliniclecture.entity.Owner;

public interface OwnerRepository extends CrudRepository<Owner, String> {
}
