package com.ttn.linkSharing.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "resource")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Resource implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne
    Topic topic;
    @ManyToOne
    User user;

    public User getUser() {
        return user;
    }

    public Resource setUser(User user) {
        this.user = user;
        return this;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resource",fetch = FetchType.LAZY)
    private List<Rating> rating = new ArrayList<>();


    @CreationTimestamp
    private Date resourceCreatedOn;

    @UpdateTimestamp
    private Date resourceUpdatedOn;

    public Integer getId() {
        return id;
    }

    public Resource setId(Integer id) {
        this.id = id;
        return this;
    }

    public Topic getTopic() {
        return topic;
    }

    public Resource setTopic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Resource setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Rating> getRating() {
        return rating;
    }

    public Resource setRating(List<Rating> rating) {
        this.rating = rating;
        return this;
    }

    public Date getResourceCreatedOn() {
        return resourceCreatedOn;
    }

    public Resource setResourceCreatedOn(Date resourceCreatedOn) {
        this.resourceCreatedOn = resourceCreatedOn;
        return this;
    }

    public Date getResourceUpdatedOn() {
        return resourceUpdatedOn;
    }

    public Resource setResourceUpdatedOn(Date resourceUpdatedOn) {
        this.resourceUpdatedOn = resourceUpdatedOn;
        return this;
    }
}
