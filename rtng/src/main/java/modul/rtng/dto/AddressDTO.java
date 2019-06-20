package modul.rtng.dto;



import modul.rtng.model.Adress;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AddressDTO {

    private Long id;
    private String state;
    private String city;
    private String street;
    private BigInteger number;
    private Integer zip;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String state, String city, String street, BigInteger number, Integer zip, BigDecimal longitude, BigDecimal latitude){
        this.setId(id);
        this.setState(state);
        this.setCity(city);
        this.setStreet(street);
        this.setNumber(number);
        this.setZip(zip);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
    }

    public AddressDTO(Adress adress){
        this(adress.getId(),adress.getState(),adress.getCity(),adress.getStreet(),adress.getNumber(),adress.getZIP(),adress.getLongitude(),adress.getLatitude());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
