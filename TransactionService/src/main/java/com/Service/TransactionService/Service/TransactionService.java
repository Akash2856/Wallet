package com.Service.TransactionService.Service;

import com.Service.TransactionService.Dto.initiateTransactionrequest;
import com.Service.TransactionService.client.UserServiceClient;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Slf4j
public class TransactionService implements UserDetailsService {

    @Autowired
    UserServiceClient userServiceClient;
    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        String auth = "txn-service:txn-service";
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authValue = "Basic "+new String(encodedAuth);
        JsonNode node = userServiceClient.getUser(phoneNo, authValue);
        log.info("user fetched: {}",node);
        return null;
    }

    public String initiateTransaction(initiateTransactionrequest request) {
        return null;
    }
}
