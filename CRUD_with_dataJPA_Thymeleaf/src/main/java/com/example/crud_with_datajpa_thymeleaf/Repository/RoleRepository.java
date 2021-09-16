package com.example.crud_with_datajpa_thymeleaf.Repository;

import com.example.crud_with_datajpa_thymeleaf.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Admin
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String name);
}
