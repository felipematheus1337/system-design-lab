package delivery_api.v1.domain;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_person")
public class Person extends InfoAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer age;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @CPF
    private String document;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public Cart getCart() {
        return cart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}