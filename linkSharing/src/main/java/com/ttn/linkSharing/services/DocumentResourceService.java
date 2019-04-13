package com.ttn.linkSharing.services;

import com.ttn.linkSharing.CO.DocumentResourceCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.DocumentResource;
import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.repositories.DocumentResourceRepository;
import com.ttn.linkSharing.repositories.SubscriptionRepository;
import com.ttn.linkSharing.repositories.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class DocumentResourceService {

    @Autowired
    DocumentResourceRepository documentResourceRepository;
    @Autowired
            TopicService topicService;
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    LinkResourceService linkResourceService;
    Logger logger = LoggerFactory.getLogger(DocumentResourceService.class);
    private static final String UPLOAD_FILE = "/home/ttn/Downloads/linkSharing/src/main/resources/static/documents/";


        public void saveDocument(DocumentResourceCO documentResourceCO, UserDTO userDTO, MultipartFile multipartFile,String topic){
        DocumentResource documentResource= COToEntityViceVersaConvertor.COconvertorToEntity(documentResourceCO);
        User user= DTOToEntityViceVersaConvertor.DTOToEntity(userDTO);
        Subscription subscription=subscriptionRepository.findByUser_UserNameAndTopic_Name(user.getUserName(),topic);
            Topic topic1=subscription.getTopic();
            try {
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get(UPLOAD_FILE + subscription.getTopic()+ "_" + multipartFile.getOriginalFilename());
                Files.write(path, bytes);
                documentResource.setPath(documentResource.getId() + "_" + multipartFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
            documentResource.setTopic(topic1);
        documentResource.setUser(user);
        documentResource.setResourceCreatedOn(new Date());
        documentResource.setResourceUpdatedOn(new Date());
        documentResourceRepository.save(documentResource);
        topic1.setPostCount(documentResourceRepository.findByTopic(topic1).size()+linkResourceService.linkResourceRepository.findByTopic(topic1).size());
topicRepository.save(topic1);
        }




    public List<DocumentResource>getAll(User user,Topic topic) {

       return documentResourceRepository.findByUserAndTopic(user, topic);

    }

}
