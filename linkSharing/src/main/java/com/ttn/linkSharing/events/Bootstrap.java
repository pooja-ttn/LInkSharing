package com.ttn.linkSharing.events;

import com.ttn.linkSharing.entities.*;
import com.ttn.linkSharing.enums.Role;
import com.ttn.linkSharing.enums.Seriousness;
import com.ttn.linkSharing.enums.Visibility;
import com.ttn.linkSharing.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class Bootstrap {


    @Autowired
    TopicService topicService;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    LinkResourceService linkResourceService;
    @Autowired
    DocumentResourceService documentResourceService;
    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @EventListener(ApplicationStartedEvent.class)
    public void init() throws IOException {
        User user = userService.findByUserName("admin");
        if (user == null) {
            User user3 = new User().setFirstName("Admin").setLastName("Admin").setUserName("admin").
                    setPassword("admin").setActive(true).setVerified(true).setRole(Role.ADMIN).setEmail("123linkShare123@gmail.com");

            userService.saveAdmin(user3);

            Topic topic = topicService.findByNameAndCreatedBy("PhP", "admin");
            if (topic == null) {
                Topic topic1 = new Topic().setVisibility(Visibility.PUBLIC).setName("PhP").setCreatedBy("admin").
                        setCreatedOn(new Date()).setUpdatedOn(new Date()).setPostCount(0).setSubscriptionCount(1);
                topicService.saveTopic(topic1);

                Subscription subscription = subscriptionService.findByUser_UserNameAndTopic_Name("admin", "Php");
                if (subscription == null) {
                    Subscription subscription1 = new Subscription().setUser(user3.setUserName("admin")).setTopic(topic1.setName("Php")).setSeriousness(Seriousness.VERY_SERIOUS);
                    subscriptionService.saveSubscription(subscription1);

                    List<LinkResource> linkResource = linkResourceService.findLinkResourceForTopic(topic1);
                    if (linkResource == null) {
                        LinkResource linkResource1 = new LinkResource();
                        linkResource1.setLinkURL("https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=3&cad=rja&uact=8&ved=2ahUKEwiQ6pSN6szhAhVs73MBHSxmDgsQFjACegQIDxAK&url=https%3A%2F%2Fwww.php.net%2Fmanual%2Fen%2Fintro-whatis.php&usg=AOvVaw2IJQ7Lnxf7GvJB63-57oy0");
                        linkResource1.setTopic(topic1);
                        linkResource1.setUser(user3);

                        linkResource1.setResourceCreatedOn(new Date());
                        linkResource1.setResourceUpdatedOn(new Date());

                        linkResourceService.saveLinkResource(linkResource1);

                    }

                    System.out.println("Application is inserting values ..");

                }
            }
        }

     /*   Optional<Topic> optionalTopic=topicRepository.findById(1);
        if(optionalTopic.isPresent()){
            Topic topic=new Topic().setName("Java").setVisibility(Visibility.PUBLIC).setUser((User)session.getAttribute("user"));

        }*/


    }

}
