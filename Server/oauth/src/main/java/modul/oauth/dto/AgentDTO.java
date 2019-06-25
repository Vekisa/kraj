package modul.oauth.dto;


import modul.oauth.model.Adress;
import modul.oauth.model.Users.Agent;

public class AgentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private AddressDTO addressDTO;
    private String bussinesRegistrationNumber;

    public AgentDTO() {
    }

    public AgentDTO(Long id, String firstName, String lastName, String email, String username, Adress adress, String bussinesRegistrationNumber){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        if(adress!=null)
        this.setAddressDTO(new AddressDTO(adress));
        this.username = username;
        this.setBussinesRegistrationNumber(bussinesRegistrationNumber);
    }

    public AgentDTO(Agent agent){
        this(agent.getId(), agent.getFirstName(),agent.getLastName(),agent.getEmail(),agent.getUsername(),agent.getAdress(),agent.getBussinesRegistrationNumber());
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

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public String getBussinesRegistrationNumber() {
        return bussinesRegistrationNumber;
    }

    public void setBussinesRegistrationNumber(String bussinesRegistrationNumber) {
        this.bussinesRegistrationNumber = bussinesRegistrationNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
