package modul.search.dto;


import modul.search.model.Adress;

public class AddressDTO {

    private Long id;
    private String state;
    private String city;
    private String street;
    private Integer number;
    private String zip;
    private double longitude;
    private double latitude;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String state, String city, String street, Integer number, String zip, double longitude, double latitude){
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
