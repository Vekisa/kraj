package modul.administrator.dto;


import modul.administrator.model.AccommodationType;
import modul.administrator.model.Image;
import modul.administrator.model.Object;
import modul.administrator.model.Unit;

import java.util.List;

public class UnitDTO {

    private Long id;
    private Integer person;
    private Integer beds;
    private ObjectDTO object;
    private List<Image> image;
    private AccommodationTypeDTO accommodationType;

    public UnitDTO(Long id, Integer person, Integer beds, Object object, AccommodationType accommodationType, List<Image> image) {
        this.setId(id);
        this.setPerson(person);
        this.setBeds(beds);
        this.setObject(new ObjectDTO(object));
        this.setImage(image);
        this.setAccommodationType(new AccommodationTypeDTO(accommodationType));
    }

    public UnitDTO(Unit unit) {
        this(unit.getId(), unit.getPerson(), unit.getBeds(), unit.getObject(), unit.getAccommodationType(), unit.getImage());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public ObjectDTO getObject() {
        return object;
    }

    public void setObject(ObjectDTO object) {
        this.object = object;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public AccommodationTypeDTO getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(AccommodationTypeDTO accommodationType) {
        this.accommodationType = accommodationType;
    }
}