package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.enums.Seriousness;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subscription")
public class Subscription implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

  @NotNull
   @ManyToOne
    private  User user;

    @NotNull
    @ManyToOne
    private  Topic topic;

    @Enumerated(EnumType.STRING)
    Seriousness seriousness;


    public Integer getId() {
        return id;
    }

    public Subscription setId(Integer id) {
        this.id = id;
        return this;
    }
    public User getUser() {
        return user;
    }

    public Subscription setUser(User user) {
        this.user = user;
        return this;
    }

    public Topic getTopic() {
        return topic;
    }

    public Subscription setTopic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public Seriousness getSeriousness() {
        return seriousness;
    }

    public Subscription setSeriousness(Seriousness seriousness) {
        this.seriousness = seriousness;
        return this;
    }
}
