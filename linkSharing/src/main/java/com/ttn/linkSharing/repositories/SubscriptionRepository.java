package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.entities.Topic;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public List<Subscription> findByUser_UserNameOrderByTopic_NameAsc(String userName);

    public Subscription findByUser_UserNameAndTopic_Name(String username, String topic_name);

    public List<Subscription> findByTopic(Topic topic);
}
