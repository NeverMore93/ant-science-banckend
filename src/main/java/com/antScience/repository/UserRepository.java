package com.antScience.repository;

import com.antScience.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User findByUserName(@Param("userName") String userName);

    User findByPhone(@Param("phone") String phone);

    void create(User user);

    User findByIdentity(@Param("identity") String identity);

    User findByCredential(@Param("identity") String identity, @Param("password") String password);
}
