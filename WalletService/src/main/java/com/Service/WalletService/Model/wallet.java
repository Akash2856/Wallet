package com.Service.WalletService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String phoneNo;
    Integer userid;
    Double balance;
    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updatedOn;

}
