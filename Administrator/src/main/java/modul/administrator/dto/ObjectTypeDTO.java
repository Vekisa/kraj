package modul.administrator.dto;

import modul.administrator.model.ObjectType;

public class ObjectTypeDTO {

    private Long id;
    private String name;
    private String description;

    public ObjectTypeDTO() {
    }

    public ObjectTypeDTO(Long id, String name, String description){
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public ObjectTypeDTO(ObjectType objectType){
        this(objectType.getId(),objectType.getName(),objectType.getDescription());
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
}
