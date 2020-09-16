package se.lexicon.simon.petcliniclecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.simon.petcliniclecture.entity.Owner;
import se.lexicon.simon.petcliniclecture.entity.Pet;
import se.lexicon.simon.petcliniclecture.entity.PetType;
import se.lexicon.simon.petcliniclecture.exception.ResourceNotFoundException;
import se.lexicon.simon.petcliniclecture.repository.OwnerRepository;
import se.lexicon.simon.petcliniclecture.repository.PetRepository;
import se.lexicon.simon.petcliniclecture.repository.PetTypeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PetServiceImpl implements PetService{

    PetRepository petRepository;
    PetTypeRepository petTypeRepository;
    OwnerRepository ownerRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, PetTypeRepository petTypeRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
        this.ownerRepository = ownerRepository;
    }


    @Override
    public List<Pet> findAll() {
        List<Pet> foundItems = petRepository.findAll();

        if (foundItems.isEmpty()) throw new ResourceNotFoundException("Could not find any pets.");

        return foundItems;
    }

    @Override
    public Pet findById(String petId) {
        return petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Could not find any with this ID: " + petId));
    }

    @Override
    public List<Pet> findByName(String petName) {

        List<Pet> foundItems = petRepository.findAllByNameContainsIgnoreCase(petName);

        if (foundItems.isEmpty()) throw new ResourceNotFoundException("Could not find any pets with the name of: " + petName);

        return foundItems;
    }

    @Override
    @Transactional
    public Pet save(Pet pet) {

         if (!pet.getPetId().equals("") && pet.getPetId() != null) throw new IllegalArgumentException("Could NOT create a Pet Where ID is specified");

        Owner owner = null;
        PetType petType = null;


         if (pet.getOwner() != null) {
             owner = ownerRepository.findById(pet.getOwner().getOwnerId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Owner"));
         }

         if (pet.getPetType().getTypeId() == null){
             petType = petTypeRepository.save(pet.getPetType());
         }else{
             petType= petTypeRepository.findById(pet.getPetType().getTypeId()).orElseThrow(() -> new ResourceNotFoundException("Invalid PetType ID"));
         }


        Pet toSave = new Pet(
                pet.getName(),
                pet.getBirthDate() == null ? LocalDate.now() : pet.getBirthDate(),
                petType,
                owner
        );

        toSave = petRepository.save(toSave);

         toSave.setOwner(owner);
         toSave.setPetType(petType);

        return toSave;
    }

    @Override
    public Pet update(Pet pet) {
        return null;
    }

    @Override
    public void delete(Pet pet) {

        petRepository.delete(pet);

    }
}
