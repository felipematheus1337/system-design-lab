package system_design_user.v1.infra.persistence.entities;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AddressEntity {

    private String streetAddress;
    private String city;
    private String state;
    private Integer postalCode;

    public AddressEntity() {
    }

    public AddressEntity(String streetAddress, String city, String state, Integer postalCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
