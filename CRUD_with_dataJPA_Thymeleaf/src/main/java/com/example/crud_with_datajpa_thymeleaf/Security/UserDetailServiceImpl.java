package com.example.crud_with_datajpa_thymeleaf.Security;

import com.example.crud_with_datajpa_thymeleaf.Repository.UserRepository;
import com.example.crud_with_datajpa_thymeleaf.Security.MyUserDetail;
import com.example.crud_with_datajpa_thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Admin
 */
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetail(user);
    }
}
