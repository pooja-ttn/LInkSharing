package com.ttn.linkSharing.services;
import com.ttn.linkSharing.CO.UserCO;
import com.ttn.linkSharing.Utils.PassWordUtils;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.enums.Role;
import com.ttn.linkSharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.validation.Valid;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    private static final String UPLOAD_FILE = "/home/ttn/Downloads/linkSharing/out/production/resources/static/images/";


    public void saveAdmin(User user) {
        user.setPhoto("User.png");
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        userRepository.save(user);

    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }


    public Optional<User> findById(User user){
        return userRepository.findById(user.getId());
    }


    public Boolean findByEmail( String email) {

        if (Objects.nonNull(userRepository.findByEmail(email))) {
            return true;
        }
        return false;

    }




    public void saveUser(@Valid @ModelAttribute User user, MultipartFile file) throws IOException {
        user.setRole(Role.USER);
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        user.setActive(true);
        user.setVerified(true);
        if (file.isEmpty()) {
            user.setPhoto("User.png");

        }
        else {

            try {

                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_FILE + user.getUserName() + "_" + file.getOriginalFilename());
                Files.write(path, bytes);
                user.setPhoto(user.getUserName() + "_" + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.save(user);

    }

   public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);

   }

   public void save(User user)
   {
       userRepository.save(user);
   }

   public Optional<User>findUserByResetToken(String resetToken){
        return userRepository.findByResetToken(resetToken);
   }

public List<User> findAll(){
        return userRepository.findAll();
}

public void ActiveByUserId(Integer userId) {
    User user = userRepository.findById(userId).orElse(null);
    if (user != null) {
        if(!user.getActive()) {
            user.setActive(true);
        }
        else
        {
            user.setActive(false);

        }
        userRepository.save(user);
    }
}
}

