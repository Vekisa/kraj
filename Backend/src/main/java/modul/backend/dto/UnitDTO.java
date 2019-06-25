package modul.backend.dto;

import modul.backend.model.Object;
import modul.backend.model.Unit;

import java.math.BigInteger;

public class UnitDTO {

    private Long id;
    private BigInteger adults;
    private BigInteger beds;
    private ObjectDTO object;

    public UnitDTO(Long id, BigInteger adults,BigInteger beds,  Object object){
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

    public BigInteger getAdults() {
        return adults;
    }

    public void setAdults(BigInteger adults) {
        this.adults = adults;
    }

    public BigInteger getBeds() {
        return beds;
    }

    public void setBeds(BigInteger beds) {
        this.beds = beds;
    }


    public ObjectDTO getObject() {
        return object;
    }

    public void setObject(ObjectDTO object) {
        this.object = object;
    }
}
