package com.ttn.linkSharing.entities;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.naming.directory.SearchResult;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rating")
public class Rating implements Serializable {

@Column(name = "id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
        @Id
Integer id;

    @ManyToOne
    User user;

    @ManyToOne
    Resource resource;

    private Double rating;
    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "resource_created_date")
    @CreationTimestamp
    private Date resourceCreatedDate;

    @UpdateTimestamp
    @Column(name = "resource_updated_date")
    private Date resourceUpdatedDate;

    public User getUser() {
        return user;
    }

    public Rating setUser(User user) {
        this.user = user;
        return this;
    }

    public Resource getResource() {
        return resource;
    }

    public Rating setResource(Resource resource) {
        this.resource = resource;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public Rating setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public Boolean getRead() {
        return isRead;
    }

    public Rating setRead(Boolean read) {
        isRead = read;
        return this;
    }

    public Date getResourceCreatedDate() {
        return resourceCreatedDate;
    }

    public Rating setResourceCreatedDate(Date resourceCreatedDate) {
        this.resourceCreatedDate = resourceCreatedDate;
        return this;
    }

    public Date getResourceUpdatedDate() {
        return resourceUpdatedDate;
    }

    public Rating setResourceUpdatedDate(Date resourceUpdatedDate) {
        this.resourceUpdatedDate = resourceUpdatedDate;
        return this;
    }
}
