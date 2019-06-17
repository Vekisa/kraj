package module.pretraga.search.dto;
import module.pretraga.search.model.Adress;
import module.pretraga.search.model.Object;

import java.math.BigInteger;

public class ObjectDTO {

    private Long id;
    private String name;
    private String description;
    private BigInteger cancellation;
    private Integer category;
    private AddressDTO address;

    public ObjectDTO() {
    }

    public ObjectDTO(Long id, String name, String description, BigInteger cancellation, Integer category, Adress address){
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setCancellation(cancellation);
        this.setCategory(category);
        this.setAddress(new AddressDTO(address));
    }

    public ObjectDTO(Object object){
        this(object.getId(),object.getName(),object.getDescription(),object.getCancellation(),object.getCategory(),object.getAdress());
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

    public BigInteger getCancellation() {
        return cancellation;
    }

    public void setCancellation(BigInteger cancellation) {
        this.cancellation = cancellation;
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
