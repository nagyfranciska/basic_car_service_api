package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private CarType carType;
    private String plate;
    private String registrationDate;
    private Integer size;
    private Integer doorCount;
    private String color;
    @JsonManagedReference
    @OneToMany
    private List<Service> serviceList;
    @JsonBackReference
    @ManyToOne()
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
        serviceList = new ArrayList<>();
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

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
