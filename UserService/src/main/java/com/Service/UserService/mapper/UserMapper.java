package com.Service.UserService.mapper;

import com.Service.UserService.Enum.UserStatus;
import com.Service.UserService.dto.CreateUserRequest;
import com.Service.UserService.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User maptoUser(CreateUserRequest createUserRequest){
        return User.builder().name(createUserRequest.getName()).
                email(createUserRequest.getEmail())
                .phoneNo(createUserRequest.getPhoneNo())
                .userIdentificationType(createUserRequest.getUserIdentificationType())
                .UserIdentificationValue(createUserRequest.getUserIdentificationValue())
                .userStatus(UserStatus.ACTIVE).build();
    }
}
