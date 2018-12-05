package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mindrot.jbcrypt.BCrypt;
import org.restlet.security.User;

import javax.persistence.*;

@Entity()
public class CDUser extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Integer id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToOne()
    @JoinColumn(name = "CUST_ID")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "GARAGE_ID")
    private Garage garage;

    @Transient
    @JsonIgnore
    private String email;

    @Transient
    @JsonIgnore
    private volatile String firstName;

    @Transient
    @JsonIgnore
    private volatile String identifier;

    @Transient
    @JsonIgnore
    private volatile String lastName;

    @Transient
    @JsonIgnore
    private volatile char[] secret;

    public CDUser() {
    }

    public CDUser(String username) {
        super(username);
    }

    public CDUser(String username, String password, Customer customer, Garage garage) {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.customer = customer;
        this.garage = garage;
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Customer getCustomer() {
        return customer;
    }

    @JsonIgnore
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Garage getGarage() {
        return garage;
    }

    @JsonIgnore
    public void setGarage(Garage garage) {
        this.garage = garage;
    }

}
