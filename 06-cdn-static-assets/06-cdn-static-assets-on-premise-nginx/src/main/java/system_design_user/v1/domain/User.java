package system_design_user.v1.domain;

import system_design_user.v1.domain.vo.Address;

import java.util.List;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private List<String> phoneNumbers;

    public User() {
    }

    public User(Long id, String firstName, String lastName, Address address, List<String> phoneNumbers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String createReport() {
        return String.format(
                "USER: %s " +
                        "with Address: %s " +
                "and with the principal phoneNumber: %s " +
                        "CREATED sucessfully ", this.firstName,
                this.address.toString(),
                this.phoneNumbers.getFirst()
        );
    }


}
