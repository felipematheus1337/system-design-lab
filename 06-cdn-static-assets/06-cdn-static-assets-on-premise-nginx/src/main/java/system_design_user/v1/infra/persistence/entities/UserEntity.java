package system_design_user.v1.infra.persistence.entities;

import jakarta.persistence.*;
import system_design_user.v1.domain.vo.Address;

import java.util.List;

@Table
@Entity(name = "tb_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)

    private AddressEntity address;

    private List<String> phoneNumbers;


}
