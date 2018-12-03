//package model;
//
//import javax.persistence.*;
//
//@Entity
//public class User {
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
//}
