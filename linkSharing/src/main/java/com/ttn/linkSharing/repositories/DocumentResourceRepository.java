package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.DocumentResource;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentResourceRepository extends CrudRepository<DocumentResource, Integer> {

    List<DocumentResource> findByUserAndTopic(User user, Topic topic);
     List<DocumentResource>findByTopic(Topic topic);
     void deleteAllByTopic_Id(Integer integer);


}
