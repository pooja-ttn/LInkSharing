package com.ttn.linkSharing.CO;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;

public class LinkResourceCO {

    private  String linkURL;
    private String description;
    private Topic topic;
    private User user;


    public String getDescription() {
        return description;
    }

    public LinkResourceCO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Topic getTopic() {
        return topic;
    }

    public LinkResourceCO setTopic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public User getUser() {
        return user;
    }

    public LinkResourceCO setUser(User user) {
        this.user = user;
        return this;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public LinkResourceCO setLinkURL(String linkURL) {
        this.linkURL = linkURL;
        return this;
    }
}
