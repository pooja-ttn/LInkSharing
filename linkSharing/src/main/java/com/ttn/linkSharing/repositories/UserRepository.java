package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUserName(String name);
    @Query("select u from User u where (u.userName=:userName or u.email=:email) AND u.password=:password")
    Optional<User> findByUserNameOrEmailAndPassword(@Param("userName") String userName, @Param("email") String email,@Param("password") String password);
    User findByUserNameOrEmail(String name,String email);

}
