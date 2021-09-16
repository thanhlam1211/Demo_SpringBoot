package com.example.crud_with_datajpa_thymeleaf.Repository;

import com.example.crud_with_datajpa_thymeleaf.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Admin
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

        public Page<Product> findByNameContainingOrBrandContainingOrMadeinContaining(String name, String brand, String madein, Pageable pageable);


}
