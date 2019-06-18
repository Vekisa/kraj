package modul.search.dto;



import modul.search.model.Object;
import modul.search.model.Unit;

import java.math.BigDecimal;
import java.math.BigInteger;

public class UnitDTO {

    private Long id;
    private BigInteger adults;
    private Integer children;
    private BigInteger beds;
    private BigDecimal size;
    private Boolean smoking;
    private ObjectDTO object;

    public UnitDTO(Long id, BigInteger adults, Integer children, BigInteger beds, BigDecimal size, Boolean smoking, Object object){
        this.setId(id);
        this.setAdults(adults);
        this.setChildren(children);
        this.setBeds(beds);
        this.setSize(size);
        this.setSmoking(smoking);
        this.setObject(new ObjectDTO(object));
    }

    public UnitDTO(Unit unit){
        this(unit.getId(),unit.getAdults(),unit.getChildren(),unit.getBeds(),unit.getSize(),unit.isSmoking(),unit.getObject());
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

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public BigInteger getBeds() {
        return beds;
    }

    public void setBeds(BigInteger beds) {
        this.beds = beds;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public Boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public ObjectDTO getObject() {
        return object;
    }

    public void setObject(ObjectDTO object) {
        this.object = object;
    }
}
