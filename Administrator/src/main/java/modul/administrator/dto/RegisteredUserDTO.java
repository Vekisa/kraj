package modul.administrator.dto;

import modul.administrator.model.Adress;
import modul.administrator.model.RegisteredUser;

public class RegisteredUserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO address;
    private Boolean active;

    public RegisteredUserDTO() {
    }

    public RegisteredUserDTO(Long id, String firstName, String lastName, String email, Adress adress, Boolean aktivan){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        if(adress!=null)
             this.setAddress(new AddressDTO(adress));
        this.setActive(aktivan);
    }

    public RegisteredUserDTO(RegisteredUser registeredUser){
        this(registeredUser.getId(),registeredUser.getFirstName(),registeredUser.getLastName(),registeredUser.getEmail(),registeredUser.getAdress(),registeredUser.isEnabled());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAktivan() {
        return getActive();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
