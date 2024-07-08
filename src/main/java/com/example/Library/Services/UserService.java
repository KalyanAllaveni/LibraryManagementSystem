package com.example.Library.Services;

import com.example.Library.Entities.User;
import com.example.Library.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements UserDetailsService{


        @Autowired
        UserRepository userRepository;


        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return this.userRepository.findByUserName(username).orElseThrow(
                    ()-> new ResponseStatusException(
                            HttpStatusCode.valueOf(404), "User not found"
                    )
            );
        }

        public User saveUser(User user){
            return userRepository.save(user);
        }
    }

