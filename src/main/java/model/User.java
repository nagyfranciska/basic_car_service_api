package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@Entity
public class User {

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

    public User() {
    }

    //TODO : password and username required, garage and customer >> delete from constructor
//    getters and setters for garage and customer (if both)
    public User(String name) {
        this.name = name;
    }

    public User(String name, String rawPassword, Garage garage, Customer customer) {
        this.name = name;
        this.password = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        this.garage = garage;
        this.customer = customer;
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
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
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
