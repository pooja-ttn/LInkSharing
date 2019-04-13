package com.ttn.linkSharing.CO;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.enums.Seriousness;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionCO {

User user;
Topic topic;

    @Enumerated(EnumType.STRING)
    Seriousness seriousness;

    public User getUser() {
        return user;
    }

    public SubscriptionCO setUser(User user) {
        this.user = user;
        return this;
    }

    public Topic getTopic() {
        return topic;
    }

    public SubscriptionCO setTopic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public Seriousness getSeriousness() {
        return seriousness;
    }

    public SubscriptionCO setSeriousness(Seriousness seriousness) {
        this.seriousness = seriousness;
        return this;
    }


}
