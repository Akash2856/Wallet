package com.Service.TransactionService.Controller;

import com.Service.TransactionService.Dto.initiateTransactionrequest;
import com.Service.TransactionService.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/transaction")
    public String initiateTransaction(@RequestBody @Valid initiateTransactionrequest request){
    return transactionService.initiateTransaction(request);
    }
}
