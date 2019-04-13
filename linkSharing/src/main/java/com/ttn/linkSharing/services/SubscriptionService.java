package com.ttn.linkSharing.services;

import com.ttn.linkSharing.DTO.SubscriptionDTO;
import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.repositories.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    Logger logger = LoggerFactory.getLogger(SubscriptionService.class);
/*
    public List<Subscription> subscriptionById(Integer id){
        return subscriptionRepository.findByUser_IdOrderByTopic_NameAsc(id);
    }*/

    public List<Subscription> showAllSubscribedTopic(String userName){
        return subscriptionRepository.findByUser_UserNameOrderByTopic_NameAsc(userName);
    }


    public void saveSubscription(Subscription subscription){
        subscriptionRepository.save(subscription);

    }

    public List<Subscription> findByTopic(Topic topic){
        return subscriptionRepository.findByTopic(topic);
    }

public Subscription findByUser_UserNameAndTopic_Name(String userName,String topicName){
        return subscriptionRepository.findByUser_UserNameAndTopic_Name(userName,topicName);
}
}
