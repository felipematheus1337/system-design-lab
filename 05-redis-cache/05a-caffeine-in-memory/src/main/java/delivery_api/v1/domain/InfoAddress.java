package delivery_api.v1.domain;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class InfoAddress {

    private String zipCode;

    private String street;

    private Integer number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
}