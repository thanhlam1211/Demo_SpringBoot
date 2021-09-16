package com.example.crud_with_datajpa_thymeleaf.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Admin
 */
@Data
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String brand;
    @Column
    private String madein;
    @Column
    private float price;
}
