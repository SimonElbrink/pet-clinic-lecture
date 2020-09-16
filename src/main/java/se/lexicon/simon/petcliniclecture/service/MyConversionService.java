package se.lexicon.simon.petcliniclecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.simon.petcliniclecture.dto.PetFormDto;
import se.lexicon.simon.petcliniclecture.entity.Owner;
import se.lexicon.simon.petcliniclecture.entity.Pet;
import se.lexicon.simon.petcliniclecture.entity.PetType;
import se.lexicon.simon.petcliniclecture.exception.ResourceNotFoundException;
import se.lexicon.simon.petcliniclecture.repository.OwnerRepository;
import se.lexicon.simon.petcliniclecture.repository.PetRepository;
import se.lexicon.simon.petcliniclecture.repository.PetTypeRepository;


@Service
public class MyConversionService {

    PetRepository petRepository;
    OwnerRepository ownerRepository;
    PetTypeRepository petTypeRepository;

    @Autowired
    public MyConversionService(PetRepository petRepository, OwnerRepository ownerRepository, PetTypeRepository petTypeRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
        this.petTypeRepository = petTypeRepository;
    }


    public Pet convertDTOtoPet(PetFormDto dto){

        Owner owner = ownerRepository.findById(dto.getOwnerId()).orElseThrow(() -> new ResourceNotFoundException("Could not find Owner With ID: " + dto.getOwnerId()));

        PetType petType = petTypeRepository.findById(dto.getPetTypeId()).orElseThrow(() -> new ResourceNotFoundException("Could not find PetType by ID: "+ dto.getPetTypeId().toString()));


        return new Pet(dto.getPetId(), dto.getName(), dto.getBirthDate(), petType, owner);


    }



}
