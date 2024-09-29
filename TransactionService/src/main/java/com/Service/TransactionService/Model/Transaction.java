package com.Service.TransactionService.Model;

import com.Service.TransactionService.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String senderPhoneNo;
    String receiverPhoneNo;
    Double amount;
    String purpose;

    @Enumerated(value=EnumType.STRING)
    TransactionStatus status;
    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updatedOn;

    String transactionId;

}
