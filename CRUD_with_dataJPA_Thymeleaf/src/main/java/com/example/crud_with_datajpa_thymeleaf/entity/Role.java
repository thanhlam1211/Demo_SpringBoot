package com.example.crud_with_datajpa_thymeleaf.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Admin
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private Integer role_id;
    @Column
    private String name;

    @ManyToMany(mappedBy = "roleList")
    private Set<User> userList = new HashSet<>();
}
