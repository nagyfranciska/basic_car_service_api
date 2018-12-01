package model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "CAR_TYPE", nullable = false)
    private CarType carType;

    @Column(name = "PLATE", nullable = false)
    private String plate;

    @Column(name = "REG_DATE", nullable = false)
    private String registrationDate;

    @Column(name = "SIZE", nullable = false)
    private Integer size;

    @Column(name = "DOOR_COUNT", nullable = false)
    private Integer doorCount;

    @Column(name = "COLOR", nullable = false)
    private String color;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car", orphanRemoval = true)
    @Column(name = "SERVICE_LIST")
    private Set<Service> serviceList;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "CUST_ID")
    private Customer customer;

    public Car() {
    }

    public Car(CarType carType, String plate, String regDate, Integer size, Integer door, String color) {
        this.carType = carType;
        this.plate = plate;
        this.registrationDate = regDate;
        this.size = size;
        this.doorCount = door;
        this.color = color;
        serviceList = new LinkedHashSet<>();
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(Integer doorCount) {
        this.doorCount = doorCount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public Set<Service> getServiceList() {
        return serviceList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
