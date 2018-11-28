package model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String start;
    private String end;
    private Integer price;
    @JsonBackReference
    @ManyToOne
    private Garage garage;
    @JsonBackReference
    @ManyToOne
    private Car car;
    @JsonBackReference
    @ManyToOne
    private Customer customer;

    public Service() {
    }

    public Service(String start, String end, Integer price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
