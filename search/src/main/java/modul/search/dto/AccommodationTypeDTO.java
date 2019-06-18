package modul.search.dto;


import modul.search.model.AccommodationType;

public class AccommodationTypeDTO {

    private Long id;
    private String name;
    private String description;

    public AccommodationTypeDTO() {
    }

    public AccommodationTypeDTO(Long id, String name, String description){
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public AccommodationTypeDTO(AccommodationType accommodationType){
        this(accommodationType.getId(),accommodationType.getName(), accommodationType.getDescription());
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
