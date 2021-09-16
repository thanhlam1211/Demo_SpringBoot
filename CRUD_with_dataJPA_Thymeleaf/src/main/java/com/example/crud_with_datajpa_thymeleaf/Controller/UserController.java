package com.example.crud_with_datajpa_thymeleaf.Controller;

import com.example.crud_with_datajpa_thymeleaf.Repository.RoleRepository;
import com.example.crud_with_datajpa_thymeleaf.Security.MyUserDetail;
import com.example.crud_with_datajpa_thymeleaf.Service.UserService;
import com.example.crud_with_datajpa_thymeleaf.entity.Role;
import com.example.crud_with_datajpa_thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Admin
 */
@Controller
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RoleRepository repository;

    @GetMapping("/process_register")
    public String createNewUser(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "sign_up";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute(name = "user") User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role roleUser = repository.findByName("ROLE_USER");
        user.addRole(roleUser);
        service.saveUser(user);
        return "login";
    }

    @GetMapping("/user_home")
    public String viewUserHome(@AuthenticationPrincipal MyUserDetail userDetail, Model model){
        model.addAttribute("user",userDetail);
        return "/user_home";
    }

}
