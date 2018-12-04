package model;
//
import javax.persistence.*;
//
//@Entity
public class User extends org.restlet.security.User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", unique = true, nullable = false)
//    private Integer id;
//
//    @Column(name = "NAME", nullable = false)
//    private String name;
//
//    @Column(name = "PASSWORD", nullable = false)
//    private String password;
//
//    public User() {}
//
//    public User(String name, String password) {
//        this.name = name;
//        this.password = password;
//    }


    private Integer id;
    private String name;
    private Integer garageId;
    private Integer CustomerId;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGarageId() {
        return garageId;
    }

    public void setGarageId(Integer garageId) {
        this.garageId = garageId;
    }

    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        CustomerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
