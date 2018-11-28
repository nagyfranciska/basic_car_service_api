package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String invoiceAddress;
    private String type;
    @JsonManagedReference
    @OneToMany
    private List<Car> carList;
    @JsonManagedReference
    @OneToMany
    private List<Service> serviceList;

    public Customer() {
    }

    public Customer(String name, String address, String invoiceAddress, String type) {
        this.name = name;
        this.address = address;
        this.invoiceAddress = invoiceAddress;
        this.type = type;
        carList = new CopyOnWriteArrayList<>();
        serviceList = new CopyOnWriteArrayList<>();
    }

    public Integer getId() {
        return id;
    }
}
