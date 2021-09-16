package com.example.crud_with_datajpa_thymeleaf.Controller;

import com.example.crud_with_datajpa_thymeleaf.Service.ProductService;
import com.example.crud_with_datajpa_thymeleaf.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Admin
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        String keyword = null;
        return listByPages(model, 1, "name", "asc", keyword);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPages(Model model,
                              @PathVariable(name = "pageNumber") int currentPage,
                              @Param("sortField") String sortField,
                              @Param("sortDirection") String sortDirection,
                              @Param("keyword") String keyword){
        //tra ve tat cac page
        Page<Product> page = service.listAll(currentPage, sortField, sortDirection, keyword);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Product> listProduct = page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection",sortDirection);
        model.addAttribute("keyword", keyword);

        String reversedSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reversedSortDirection", reversedSortDirection );
        return "index";
    }

    @RequestMapping("/new")
    public String showNewProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product){
        service.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("edit_product");
        Product product = service.getProduct(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id){
        service.deleteProduct(id);
        return "redirect:/";
    }


}
