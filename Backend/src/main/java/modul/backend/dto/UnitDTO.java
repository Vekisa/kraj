package modul.backend.dto;

import modul.backend.model.Object;
import modul.backend.model.Unit;

import java.math.BigInteger;

public class UnitDTO {

    private Long id;
    private int adults;
    private int beds;
    private ObjectDTO object;

    public UnitDTO(Long id, int adults,int beds,  Object object){
        this.setId(id);
        this.setAdults(adults);
        this.setBeds(beds);
        this.setObject(new ObjectDTO(object));
    }

    public UnitDTO(Unit unit){
        this(unit.getId(),unit.getPerson(),unit.getBeds(),unit.getObject());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public ObjectDTO getObject() {
        return object;
    }

    public void setObject(ObjectDTO object) {
        this.object = object;
    }
}
