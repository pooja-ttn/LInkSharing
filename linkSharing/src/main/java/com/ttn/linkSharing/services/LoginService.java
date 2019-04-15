package com.ttn.linkSharing.services;

import com.ttn.linkSharing.CO.LoginCO;
import com.ttn.linkSharing.Utils.PassWordUtils;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.repositories.UserRepository;
import net.bytebuddy.matcher.CollectionOneToOneMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoginService {


    @Autowired
    UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(LoginService.class);

    public Optional<User> checkLoginPassword(LoginCO loginCO) {

        User user= COToEntityViceVersaConvertor.COconvertorToEntity(loginCO);
        user.setActive(true);
         return userRepository.findByUserNameOrEmailAndPassword(user.getUserName(), user.getEmail(), PassWordUtils.encrypt(user.getPassword()),user.getActive());
    }
}

