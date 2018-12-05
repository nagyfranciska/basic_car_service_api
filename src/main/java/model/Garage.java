package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

//    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

//    @Column(name = "ADDRESS", nullable = false)
    private String address;

//    @Column(name = "CAPACITY", nullable = false)
    private Integer capacity;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "garage", orphanRemoval = true)
//    @Column(name = "SERVICE_LIST")
    private Set<Service> serviceList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "garage", orphanRemoval = true)
//    @Column(name = "USER_LIST")
    private Set<User> userList;

    public Garage() {
    }

    public Garage(String name, String address, Integer capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        serviceList = new LinkedHashSet<>();
        userList = new LinkedHashSet<>();
    }

    public Set<Service> getServiceList() {
        return serviceList;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }
}