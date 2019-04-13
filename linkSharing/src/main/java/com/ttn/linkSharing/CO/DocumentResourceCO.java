package com.ttn.linkSharing.CO;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;

public class DocumentResourceCO {

    private String path;


    private String description;


     private Topic topic;

    private User user;

    public String getDescription() {
        return description;
    }

    public DocumentResourceCO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Topic getTopic() {
        return topic;
    }

    public DocumentResourceCO setTopic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public User getUser() {
        return user;
    }

    public DocumentResourceCO setUser(User user) {
        this.user = user;
        return this;
    }

    public String getPath() {
        return path;
    }


    public DocumentResourceCO setPath(String path) {
        this.path = path;
        return this;
    }
}
