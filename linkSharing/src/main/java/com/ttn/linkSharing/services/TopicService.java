package com.ttn.linkSharing.services;

import com.ttn.linkSharing.CO.TopicCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.LinkResource;
import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.enums.Seriousness;
import com.ttn.linkSharing.enums.Visibility;
import com.ttn.linkSharing.repositories.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    DocumentResourceService documentResourceService;
    @Autowired
    LinkResourceService linkResourceService;
    Logger logger = LoggerFactory.getLogger(TopicService.class);

    public void saveTopic(TopicCO topicCO, UserDTO userDTO) {
        User user = DTOToEntityViceVersaConvertor.DTOToEntity(userDTO);
        Topic topic = COToEntityViceVersaConvertor.COconvertorToEntity(topicCO);
        Subscription subscription = new Subscription();
        logger.debug("topicDTO CONVERTED");
        topic.setName(topicCO.getTopicName());
        topic.setCreatedBy(user.getUserName());
        topic.setCreatedOn(new Date());
        topic.setPhoto(user.getPhoto());
        subscription.setUser(user);
        subscription.setTopic(topic);
        subscription.setSeriousness(Seriousness.VERY_SERIOUS);
        if (topicCO.getVisibility() == Visibility.PRIVATE) {

            topicRepository.save(topic);
            subscriptionService.saveSubscription(subscription);
            topic.setSubscriptionCount(subscriptionService.findByTopic(topic).size());
            topic.setPostCount(documentResourceService.documentResourceRepository.findByTopic(topic).size()+linkResourceService.linkResourceRepository.findByTopic(topic).size());

            topicRepository.save(topic);



        } else {

            if (!topicRepository.findByCreatedByAndNameAndVisibility(topic.getCreatedBy(), topic.getName()
                    , topic.getVisibility()).isPresent()) {
                topicRepository.save(topic);
                subscriptionService.saveSubscription(subscription);
                topic.setSubscriptionCount(subscriptionService.findByTopic(topic).size());
                topic.setPostCount(documentResourceService.documentResourceRepository.findByTopic(topic).size()+linkResourceService.linkResourceRepository.findByTopic(topic).size());
                topicRepository.save(topic);

            }

        }
    }


    public List<Topic> topicCreatedBy(String createdBy) {
        return topicRepository.findByCreatedBy(createdBy);
    }


    public Topic findByNameAndCreatedBy(String topicName,String createdBy)
    {
        return topicRepository.findByNameAndCreatedBy(topicName,createdBy);
    }


    public void saveTopic(Topic topic)
    {
        topicRepository.save(topic);
    }


    public List<Topic> findAllTopic(){
        return topicRepository.findAllByVisibilityOrderByPostCountDesc(Visibility.PUBLIC);
    }

    @Transactional
    public void deleteTopic(Integer topicId){

        subscriptionService.deleteSubscriptionIdWhereTopicId(topicId);
        linkResourceService.deletewhereTopicId(topicId);
        documentResourceService.deletewhereTopicId(topicId);
        topicRepository.deleteById(topicId);
    }


    public Optional<Topic> findById(Integer integer)
    {
        return topicRepository.findById(integer);
    }

    public String editTopic(String newTopic, Integer id, UserDTO user) {

        Topic topic = topicRepository.findById(id).orElse(null);

        if(user == null) {
            return "redirect:/";
        }
        else if(topic == null) {
            return "redirect:/dashboard";
        }
        else {
            topic.setName(newTopic);
            topicRepository.save(topic);
            return "redirect:/dashboard";
        }
    }
}
