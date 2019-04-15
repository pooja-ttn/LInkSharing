package com.ttn.linkSharing.services;

import com.ttn.linkSharing.CO.LinkResourceCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.LinkResource;
import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.repositories.LinkResourceRepository;
import com.ttn.linkSharing.repositories.SubscriptionRepository;
import com.ttn.linkSharing.repositories.TopicRepository;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkResourceService {
    @Autowired
    LinkResourceRepository linkResourceRepository;
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
            DocumentResourceService documentResourceService;

    Logger logger = LoggerFactory.getLogger(LinkResourceService.class);

    public void saveLink(UserDTO userDTO, LinkResourceCO linkResourceCO,String topic)
    {
        User user= DTOToEntityViceVersaConvertor.DTOToEntity(userDTO);
        LinkResource linkResource= COToEntityViceVersaConvertor.COconvertorToEntity(linkResourceCO);
        linkResource.setUser(user);
        Subscription subscription=subscriptionRepository.findByUser_UserNameAndTopic_Name(user.getUserName(),topic);
        Topic topic1=subscription.getTopic();

        linkResource.setTopic(topic1);
        linkResourceRepository.save(linkResource);
        topic1.setPostCount(linkResourceRepository.findByTopic(topic1).size()+documentResourceService.documentResourceRepository.findByTopic(topic1).size());
        topicRepository.save(topic1);

    }

    public void deletewhereTopicId(Integer integer){
        linkResourceRepository.deleteAllByTopic_Id(integer);
    }

    public void saveLinkResource(LinkResource linkResource){
        linkResourceRepository.save(linkResource);
    }

    public List<LinkResource> findLinkResourceForTopic(Topic topic)
    {
        return linkResourceRepository.findByTopic(topic);

    }

/*
    public List<LinkResource> findLinkResource()
*/





}
