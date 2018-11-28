package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @JsonManagedReference
    @JsonIgnoreProperties
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer", orphanRemoval = true)
    @Column(name = "CAR_LIST")
    private List<Car> carList;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer", orphanRemoval = true)
    @Column(name = "SERVICE_LIST")
    private List<Service> serviceList;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "INVOICE_ADD", nullable = false)
    private String invoiceAddress;

    @Column(name = "TYPE", nullable = false)
    private String type;

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
