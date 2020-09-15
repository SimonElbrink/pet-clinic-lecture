package se.lexicon.simon.petcliniclecture.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class OwnerFromDto {
    @Null(message = "ownerId should not be present.")
    private String ownerId;

    @NotBlank(message = "You need to provide a FirstName between 2 and 45 letters.")
    @Size(min = 2, max = 45)
    private String firstName;

    @NotBlank(message = "You need to provide a LastName between 2 and 100 letters.")
    @Size(min = 2, max = 100)
    private String lastName;

    @NotBlank(message = "You need to provide a address.")
    private String address;

    @NotBlank (message = "You need to Provide a Phone number")
    private String telephone;

    public OwnerFromDto() {
    }

    public OwnerFromDto(String ownerId, String firstName, String lastName, String address, String telephone) {
        this.ownerId = ownerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}