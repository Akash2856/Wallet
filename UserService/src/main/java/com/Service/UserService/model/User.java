package com.Service.UserService.model;

import com.Service.UserService.Enum.UserIdentificationType;
import com.Service.UserService.Enum.UserStatus;
import com.Service.UserService.Enum.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 30)
    String name;
    @Column(unique = true,length = 50)
    String email;
    @Column(unique = true,length = 55)
    String  phoneNo;
    String password;
    String authorities;

    @Enumerated(value=EnumType.STRING)
    UserIdentificationType userIdentificationType;
    String UserIdentificationValue;

    @Enumerated(value=EnumType.STRING)
    UserStatus userStatus;

    @Enumerated(value=EnumType.STRING)
    UserType userType;

    @CreationTimestamp
    Date createdOn;

    @UpdateTimestamp
    Date updatedOn;


    @Override
    public String getUsername() {
        return phoneNo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Arrays.stream(authorities.split(","))
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }
}
