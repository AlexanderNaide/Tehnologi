package com.satal.tehnologi.module;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "mail", unique = true)
    private String eMail;
    @Column(name = "number")
    private String phoneNumber;


}
