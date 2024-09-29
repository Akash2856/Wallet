package com.Service.TransactionService.Controller;

import com.Service.TransactionService.Dto.initiateTransactionrequest;
import com.Service.TransactionService.Service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/transaction")
    public String initiateTransaction(@RequestBody @Valid initiateTransactionrequest request){
        log.info("in transaction");
        UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String senderPhoneNo=userDetails.getUsername();
        return transactionService.initiateTransaction(senderPhoneNo, request);
    }
}
