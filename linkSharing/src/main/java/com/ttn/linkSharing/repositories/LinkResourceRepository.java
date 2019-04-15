package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.LinkResource;
import com.ttn.linkSharing.entities.Resource;
import com.ttn.linkSharing.entities.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkResourceRepository extends CrudRepository<LinkResource,Integer> {

    public List<LinkResource>findByTopic(Topic topic);
    public void deleteAllByTopic_Id(Integer integer);
}
