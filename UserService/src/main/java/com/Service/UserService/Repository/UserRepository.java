package com.Service.UserService.Repository;

import com.Service.UserService.model.User;
import com.Service.UserService.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByPhoneNo(String phoneNo);
}
