package se.lexicon.simon.petcliniclecture.dto;



import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PetFormDto {

    private String petId;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    private Integer petTypeId;

    @NotBlank
    private String ownerId;

    public PetFormDto() {
    }

    public PetFormDto(String petId, String name, LocalDate birthDate, Integer petTypeId, String ownerId) {
        this.petId = petId;
        this.name = name;
        this.birthDate = birthDate;
        this.petTypeId = petTypeId;
        this.ownerId = ownerId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}