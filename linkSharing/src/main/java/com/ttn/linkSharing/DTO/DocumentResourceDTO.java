package com.ttn.linkSharing.DTO;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;

public class DocumentResourceDTO {
    private String path;


    private String description;

    public String getPath() {
        return path;
    }

    public DocumentResourceDTO setPath(String path) {
        this.path = path;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DocumentResourceDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Topic getTopic() {
        return topic;
    }

    public DocumentResourceDTO setTopic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public User getUser() {
        return user;
    }

    public DocumentResourceDTO setUser(User user) {
        this.user = user;
        return this;
    }

    private Topic topic;

    private User user;
}
