package com.Service.UserService.service;

import com.Service.UserService.Enum.UserType;
import com.Service.UserService.Repository.UserRepository;
import com.Service.UserService.dto.CreateUserRequest;
import com.Service.UserService.mapper.UserMapper;
import com.Service.UserService.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        User user= userRepository.findByPhoneNo(phoneNo);
        if(user==null)
            throw new UsernameNotFoundException("user not found");
        return user;
    }

    public User createUser(CreateUserRequest userRequest) {
        User user= UserMapper.maptoUser(userRequest);
        user.setUserType(UserType.USER);
        user.setAuthorities("USER");
        log.info("User Created {} ", user);
        userRepository.save(user);
        log.info("User save {} ", user);
        return user;
    }
}
