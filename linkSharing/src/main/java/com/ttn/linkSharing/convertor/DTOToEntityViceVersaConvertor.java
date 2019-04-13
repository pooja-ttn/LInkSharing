package com.ttn.linkSharing.convertor;

import com.ttn.linkSharing.CO.TopicCO;
import com.ttn.linkSharing.DTO.DocumentResourceDTO;
import com.ttn.linkSharing.DTO.TopicDTO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.entities.DocumentResource;
import com.ttn.linkSharing.entities.Topic;
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

    public static Topic DTOToEntity(TopicDTO topicDTO) {
Topic topic=new Topic();
        if (Objects.nonNull(topicDTO)) {
            BeanUtils.copyProperties(topicDTO,topic);
//            modelMapper.map(userDTO, user);
//            logger.debug("DTO TO ENTITY successful");
        }
        return topic;

    }


    //Entity To DTO
    public static TopicDTO EntityToDTO(Topic topic){
TopicDTO topicDTO=new TopicDTO();
        if(Objects.nonNull(topic)){
            BeanUtils.copyProperties(topic,topicDTO);
//            modelMapper.map(user,userDTO);
        }
        return topicDTO;
    }



    public static DocumentResource DTOToEntity(DocumentResourceDTO documentResourceDTO) {
        DocumentResource documentResource=new DocumentResource();
        if (Objects.nonNull(documentResourceDTO)) {
            BeanUtils.copyProperties(documentResourceDTO,documentResource);

        }
        return documentResource;

    }


    //Entity To DTO
    public static DocumentResourceDTO EntityToDTO(DocumentResource documentResource){
        DocumentResourceDTO documentResourceDTO=new DocumentResourceDTO();
        if(Objects.nonNull(documentResource)){
            BeanUtils.copyProperties(documentResource,documentResourceDTO);
        }
        return documentResourceDTO;
    }

}
