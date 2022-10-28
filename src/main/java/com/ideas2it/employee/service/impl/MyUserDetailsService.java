package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.dao.UserRepository;
import com.ideas2it.employee.model.User;
import com.ideas2it.employee.models.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()) {
            User userDetails = user.get();
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            userDetails.setPassword(bCrypt.encode(userDetails.getPassword()));
            return new MyUserDetails(userDetails);
        }
        throw new UsernameNotFoundException("not found");
    }
}

