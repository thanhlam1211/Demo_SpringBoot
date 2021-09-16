package com.example.crud_with_datajpa_thymeleaf.Controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Admin
 */
@Controller
public class AppController {
    @GetMapping("/403")
    public String error403(){
        return "403";
    }

    @GetMapping("/login")
    public String loginPage(){
        //prevent user return back to login page if they already login to the system
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/";
    }

//    @PostMapping("/login_success_handler")
//    public String loginSuccessHandle(){
//        return "index";
//    }
}
