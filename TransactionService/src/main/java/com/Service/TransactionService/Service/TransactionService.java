package com.Service.TransactionService.Service;

import com.Service.TransactionService.Dto.initiateTransactionrequest;
import com.Service.TransactionService.Model.Transaction;
import com.Service.TransactionService.Repository.TransactionRepository;
import com.Service.TransactionService.client.UserServiceClient;
import com.Service.TransactionService.enums.TransactionStatus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TransactionService implements UserDetailsService {

    @Autowired
    UserServiceClient userServiceClient;
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        String auth = "transaction-service:transaction-service";
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());

        String authValue = "Basic "+new String(encodedAuth);
        ObjectNode node = userServiceClient.getUser(phoneNo, authValue);

        log.info("user fetched: {}",node);
        if(node==null)
            throw new UsernameNotFoundException("username not found");
        ArrayNode authorities = (ArrayNode) node.get("authorities");


        final List<GrantedAuthority> authorityList = new ArrayList<>();


        authorities.iterator().forEachRemaining(jsonNode -> {
            authorityList.add(new SimpleGrantedAuthority(jsonNode.get("authority").textValue()));
        });


        User user = new User(node.get("phoneNo").textValue(), node.get("password").textValue(), authorityList);
        return user;
    }

    public String initiateTransaction(String senderPhoneNo,initiateTransactionrequest request) {
        Transaction transaction=Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .senderPhoneNo(senderPhoneNo)
                .receiverPhoneNo(request.getReceiverPhoneNo())
                .amount(request.getAmount())
                .purpose(request.getMessage())
                .status(TransactionStatus.INITIATED).build();
        transactionRepository.save(transaction);
        return transaction.getTransactionId();
    }
}
