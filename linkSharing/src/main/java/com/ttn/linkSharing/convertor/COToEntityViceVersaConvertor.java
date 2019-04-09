package com.ttn.linkSharing.convertor;

import com.ttn.linkSharing.CO.LoginCO;
import com.ttn.linkSharing.CO.UserCO;
import com.ttn.linkSharing.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class COToEntityViceVersaConvertor {


    //CO To Entity
    public static User COconvertorToEntity(UserCO userCO)
    {
        User user=new User();
        if(Objects.nonNull(userCO))
        {
            BeanUtils.copyProperties(userCO,user);
        }
        return user;
    }

    public static User COconvertorToEntity(LoginCO loginCO)
    {
        User user=new User();
        if(Objects.nonNull(loginCO))
        {
            BeanUtils.copyProperties(loginCO,user);
        }
        return user;
    }



    //Entity to CO

    public static UserCO EntityconvertorToUserCO(User user)
    {
        UserCO userCO=null;
        if(Objects.nonNull(user))
        {
            BeanUtils.copyProperties(user,userCO);
        }
        return userCO;
    }



    public static LoginCO EntityconvertorToLoginCO(User user)
    {
        LoginCO loginCO=null;
        if(Objects.nonNull(user))
        {
            BeanUtils.copyProperties(user,loginCO);
        }
        return loginCO;
    }
}
