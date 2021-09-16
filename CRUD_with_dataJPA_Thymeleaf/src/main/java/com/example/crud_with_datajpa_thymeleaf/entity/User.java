package com.example.crud_with_datajpa_thymeleaf.entity;

import com.sun.istack.NotNull;
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
@Table(name = "users")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthenticationProvider authenticationProvider;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
                joinColumns =@JoinColumn(name = "user_id"),
                inverseJoinColumns =@JoinColumn(name = "role_id"))
    private Set<Role> roleList = new HashSet<>();

    public void addRole(Role role) {
        this.roleList.add(role);
    }

}
