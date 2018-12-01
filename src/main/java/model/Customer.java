package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "customer", orphanRemoval = true)
    @Column(name = "CAR_LIST")
    private Set<Car> carList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "customer", orphanRemoval = true)
    @Column(name = "SERVICE_LIST")
    private Set<Service> serviceList;

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
        carList = new LinkedHashSet<>();
        serviceList = new LinkedHashSet<>();
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

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Car> getCarList() {
        return carList;
    }

    public Set<Service> getServiceList() {
        return serviceList;
    }

}
