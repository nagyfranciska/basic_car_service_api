package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "CAR_TYPE", nullable = false)
    private String start;

    @Column(name = "CAR_TYPE", nullable = false)
    private String end;

    @Column(name = "CAR_TYPE", nullable = false)
    private Integer price;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "GARAGE_ID")
    private Garage garage;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "CUST_ID")
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
