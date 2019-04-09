package com.ttn.linkSharing.events;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.enums.Role;
import com.ttn.linkSharing.repositories.UserRepository;
import com.ttn.linkSharing.services.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class Bootstrap {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSignUpService userService;
    @EventListener(ApplicationStartedEvent.class)
    public void init()throws IOException {
        User user=userRepository.findByUserName("admin");
        if(user==null){
            User user3=new User().setFirstName("Admin").setLastName("Admin").setUserName("admin").setPassword("admin").setActive(true).setVerified(true).setRole(Role.ADMIN);
            userService.saveAdmin(user3);
            System.out.println("Application is inserting values ..");
        }
        }

    }
