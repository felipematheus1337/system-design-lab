package system_design_user.v1.domain;

import system_design_user.v1.domain.vo.Address;

import java.util.List;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private List<String> phoneNumbers;
}
