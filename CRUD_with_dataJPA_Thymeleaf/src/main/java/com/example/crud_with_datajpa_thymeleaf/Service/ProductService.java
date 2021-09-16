package com.example.crud_with_datajpa_thymeleaf.Service;

import com.example.crud_with_datajpa_thymeleaf.Repository.ProductRepository;
import com.example.crud_with_datajpa_thymeleaf.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Admin
 */
@Service
public class ProductService{
    @Autowired
    private ProductRepository repository;

    public Page<Product> listAll(int pageNumber, String sortField, String sortDirection, String keyword){

        //name trong product entity
        Sort sort = Sort.by(sortField);
        sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();

        //phan trang
        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        if(keyword != null) {
//            keyword = "%"+keyword+"%";
//            System.out.println(keyword);
            return repository.findByNameContainingOrBrandContainingOrMadeinContaining(keyword, keyword, keyword, pageable);
        }
        return repository.findAll(pageable);
    }

    public void saveProduct(Product product) {
        repository.save(product);
    }
    public Product getProduct(Long id) {
        return repository.findById(id).get();
    }
    public void deleteProduct(Long id){
        repository.deleteById(id);
    }

}
