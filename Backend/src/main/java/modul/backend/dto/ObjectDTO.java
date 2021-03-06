package modul.backend.dto;


import modul.backend.model.Adress;
import modul.backend.model.Object;

public class ObjectDTO {

    private Long id;
    private String name;
    private String description;
    private Integer category;
    private AddressDTO address;

    public ObjectDTO() {
    }

    public ObjectDTO(Long id, String name, String description, Integer category, Adress address){
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setCategory(category);
        this.setAddress(new AddressDTO(address));
    }

    public ObjectDTO(Object object){
        this(object.getId(),object.getName(),object.getDescription(),object.getCategory(),object.getAdress());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
