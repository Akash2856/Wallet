package com.Service.UserService.service;

import com.Service.UserService.Enum.UserType;
import com.Service.UserService.Repository.UserRepository;
import com.Service.UserService.dto.CreateUserRequest;
import com.Service.UserService.mapper.UserMapper;
import com.Service.UserService.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Service.UserService.Constant.UserCreationTopicConstants;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.constraints.Email;
import org.springframework.kafka.core.KafkaTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Service.UserService.Constant.UserCreationTopicConstants;

import static com.Service.UserService.Constant.KafkaConstant.USER_CREATION_TOPIC;
import static com.Service.UserService.Constant.UserCreationTopicConstants.*;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PasswordEncoder passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        log.info("User Created {} ", user);
        userRepository.save(user);
        log.info("User save {} ", user);
        ObjectNode objectNode= objectMapper.createObjectNode();
        objectNode.put(EMAIL,user.getEmail());
        objectNode.put(PHONENO,user.getPhoneNo());
        objectNode.put(NAME,user.getName());
        objectNode.put(USERID,user.getId());

        String kafkaMessage=objectNode.toString();
        kafkaTemplate.send(USER_CREATION_TOPIC,objectNode.toString());
        log.info("Message published to kafka:  {}",user);

        return user;
    }

    public User getUserByPhoneNo(String phoneNo) {
        return  userRepository.findByPhoneNo(phoneNo);
    }
}
