package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.enums.Visibility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public List<Subscription> findByUser_UserNameAndTopic_VisibilityOrderByTopic_NameAsc(String userName, Visibility visibility);

    public Subscription findByUser_UserNameAndTopic_Name(String username, String topic_name);

    public List<Subscription> findByTopic(Topic topic);

    public void deleteByTopic_IdAndUser_Id(Integer topicId,Integer userId);
    @Transactional
    public void deleteByTopic_Id(Integer topicId);

    public Integer countByTopic_IdAndAndUser_UserName(Integer topicId,String userName);

    public Subscription findByTopic_IdAndAndUser_UserName(Integer topicId,String userName);


}
