package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.enums.Visibility;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic,Integer> {

    Topic findByNameAndCreatedBy(String topicName,String createdBy);

   List<Topic>findByCreatedBy(String createdBy);

   Optional<Topic> findByCreatedByAndNameAndVisibility(String createdBy, String name, Visibility visibility);

}
