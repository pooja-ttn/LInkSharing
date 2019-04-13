package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.DocumentResource;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentResourceRepository extends CrudRepository<DocumentResource, Integer> {

    public List<DocumentResource> findByUserAndTopic(User user, Topic topic);
    public List<DocumentResource>findByTopic(Topic topic);

}
