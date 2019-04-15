package com.ttn.linkSharing.services;

import com.ttn.linkSharing.CO.TopicCO;
import com.ttn.linkSharing.DTO.SubscriptionDTO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.enums.Seriousness;
import com.ttn.linkSharing.enums.Visibility;
import com.ttn.linkSharing.repositories.SubscriptionRepository;
import com.ttn.linkSharing.repositories.TopicRepository;
import org.omg.CORBA.INTERNAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
            TopicService topicService;
    Logger logger = LoggerFactory.getLogger(SubscriptionService.class);


    public List<Subscription> showAllSubscribedTopic(String userName) {
        return subscriptionRepository.findByUser_UserNameAndTopic_VisibilityOrderByTopic_NameAsc(userName, Visibility.PUBLIC);
    }


    public void saveSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);

    }

    public void saveSubscriptionForTopicAndUser(Integer topicId,UserDTO userDTO)
    { Topic topic=topicService.findById(topicId).orElse(null);
        User user=DTOToEntityViceVersaConvertor.DTOToEntity(userDTO);
        Subscription subscription=new Subscription().setSeriousness(Seriousness.CASUAL).setTopic(topic).setUser(user);
        subscriptionRepository.save(subscription);
    }

    public List<Subscription> findByTopic(Topic topic) {
        return subscriptionRepository.findByTopic(topic);
    }

    public Subscription findByUser_UserNameAndTopic_Name(String userName, String topicName) {
        return subscriptionRepository.findByUser_UserNameAndTopic_Name(userName, topicName);
    }

/*
    public void updateSubscription(Integer topicId,String userName)
    {
        Subscription subscription=subscriptionRepository.findByTopic_IdAndAndUser_UserName(topicId,userName);
        if(subscription!=null)
        {
            subscription.setRead(false);

        }
    }*/

    public Integer countByTopic_IdAndAndUser_UserName(Integer integer,String userName){
        return subscriptionRepository.countByTopic_IdAndAndUser_UserName(integer,userName);
    }



    @Transactional
public void deleteSubscriptionIdWhereTopicId(Integer integer){
        subscriptionRepository.deleteByTopic_Id(integer);
}



@Transactional
    public void deleteSubscriptionId(Integer id, UserDTO userDTO) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isPresent()) {
            if (userDTO.getUserName().equals(subscription.get().getTopic().getCreatedBy())) {
                Topic topic = subscription.get().getTopic();
                subscriptionRepository.deleteById(id);
                topicRepository.deleteById(topic.getId());
            } else {
                subscriptionRepository.deleteById(id);

            }

        }
    }



    @Transactional
    public void deleteSubscriptionWhereTopicIdAndUserId(Integer integer,UserDTO userDTO)
    {
        User user= DTOToEntityViceVersaConvertor.DTOToEntity(userDTO);
        subscriptionRepository.deleteByTopic_IdAndUser_Id(integer,user.getId());

    }
}
