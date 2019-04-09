/*
package com.ttn.linkSharing.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="subscription")
public class Subscription implements Serializable {
 @EmbeddedId
    SubscriptionKey id;
 @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "student_id")
    User user;

    @ManyToOne
    @MapsId("topic_id")
    @JoinColumn(name = "topic_id")
    Topic topic;
    int rating;


}

*/
