package com.ttn.linkSharing.services;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@Service
public class UserSignUpService {
    @Autowired
    UserRepository userRepository;
    private static final String UPLOAD_FILE = "/home/ttn/Downloads/linkSharing/src/main/resources/static/images/";


    public void saveAdmin(User user) {
        user.setPhoto("images/" + "User.png");
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        userRepository.save(user);

    }


public Boolean findByUserNameOrEmail(String userName,String email) {

    if (Objects.nonNull(userRepository.findByUserNameOrEmail(userName,email))) {
        return true;
    }
    return false;

}



    public void saveUser(@Valid @ModelAttribute User user, MultipartFile file) throws IOException {
        if (file.equals(null) || file.isEmpty()) {
            user.setPhoto(user.getUserName() + "_" + "User.png");

        }
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        user.setActive(true);
        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FILE + user.getUserName() + "_" + file.getOriginalFilename());
            Files.write(path, bytes);
            user.setPhoto(user.getUserName() + "_" + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRepository.save(user);

    }




}

