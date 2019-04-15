package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUserName(String name);
    @Query("select u from User u where (u.userName=:userName or u.email=:email) AND u.password=:password AND u.active=:active")
    Optional<User> findByUserNameOrEmailAndPassword(@Param("userName") String userName, @Param("email") String email,@Param("password") String password,@Param("active") Boolean Active);
    User findByUserNameOrEmail(String name,String email);
public Optional<User> findByEmail(String email);

public Optional<User>findByResetToken(String resetToken);
public List<User> findAll();
}
