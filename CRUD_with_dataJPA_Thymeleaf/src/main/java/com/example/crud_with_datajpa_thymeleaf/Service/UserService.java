package com.example.crud_with_datajpa_thymeleaf.Service;

import com.example.crud_with_datajpa_thymeleaf.Exception.UserNotFoundException;
import com.example.crud_with_datajpa_thymeleaf.Repository.UserRepository;
import com.example.crud_with_datajpa_thymeleaf.entity.AuthenticationProvider;
import com.example.crud_with_datajpa_thymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Admin
 */
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User getCustomerByEmail(String email){
        return repository.getUserByEmail(email);
    }
    public User getUserByName(String username){
        return repository.getUserByUsername(username);
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = repository.findByEmail(email);
        if(user != null) {
            user.setResetPasswordToken(token);
            repository.save(user);
        }else{
            throw new UserNotFoundException("Could not find any user with " + email);
        }
    }

    //check user belong to the email or not
    public User get(String resetPasswordToken) {
        return repository.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(User user, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);

        repository.save(user);
    }

    public void createNewUserAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider authenticationProvider) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(name);
        user.setEnabled(true);
        user.setAuthenticationProvider(authenticationProvider);

        repository.save(user);
    }

    public void updateUserAfterOAuthLoginSuccess(User user, String name, AuthenticationProvider provider) {
        user.setUsername(name);
        user.setAuthenticationProvider(provider);

        repository.save(user);
    }
}
