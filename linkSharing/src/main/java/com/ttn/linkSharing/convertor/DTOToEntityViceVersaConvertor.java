package com.ttn.linkSharing.convertor;

import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;

public class DTOToEntityViceVersaConvertor {


    Logger logger = LoggerFactory.getLogger(DTOToEntityViceVersaConvertor.class);

    //DTO TO Entity

    public static User DTOToEntity(UserDTO userDTO) {
        User user = new User();
        if (Objects.nonNull(userDTO)) {
            BeanUtils.copyProperties(userDTO,user);
//            modelMapper.map(userDTO, user);
//            logger.debug("DTO TO ENTITY successful");
        }
        return user;

    }


        //Entity To DTO
    public static UserDTO EntityToDTO(User user){
        UserDTO userDTO=new UserDTO();
        if(Objects.nonNull(user)){
            BeanUtils.copyProperties(user,userDTO);
//            modelMapper.map(user,userDTO);
        }
        return userDTO;
    }


}
