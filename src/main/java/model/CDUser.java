package model;

import org.restlet.security.User;

import javax.persistence.*;

@Entity
public class CDUser extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

//    @Column(name = "NAME", nullable = false)
    private String name;

//    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToOne()
    @JoinColumn
    private Garage garage;

    @ManyToOne()
    @JoinColumn
    private Customer customer;


    public CDUser() {
    }

    public CDUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
