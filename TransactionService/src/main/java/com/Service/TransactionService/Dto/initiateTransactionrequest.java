package com.Service.TransactionService.Dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class initiateTransactionrequest {
    @NotBlank
    String receiverPhoneNo;
    @Positive
    Double amount;

    String message;

}
