package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "GARAGE", unique = true, nullable = false)
    private String name;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "CAPACITY", nullable = false)
    private Integer capacity;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "garage", orphanRemoval = true)
    @Column(name = "SERVICE_LIST")
    private List<Service> serviceList;

    public Garage() {
    }

    public Garage(String name, String address, Integer capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        serviceList = new CopyOnWriteArrayList<>();
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public Integer getId() {
        return id;
    }
}
