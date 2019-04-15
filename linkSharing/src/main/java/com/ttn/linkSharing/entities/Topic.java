package com.ttn.linkSharing.entities;
import com.ttn.linkSharing.enums.Visibility;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "topic",uniqueConstraints = @UniqueConstraint(columnNames = {"name","created_by"}))
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name="created_by")
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_on")
    private Date createdOn;

    @UpdateTimestamp
    @Column(name="updated_on")
    private Date updatedOn;


    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @Column(name = "post_count")
    private Integer postCount;



    @Column(name = "subscription_count")
    private Integer subscriptionCount;

    @Column(name = "user_photo")
    private String photo;

    public Integer getPostCount() {
        return postCount;
    }

    public Topic setPostCount(Integer postCount) {
        this.postCount = postCount;
        return this;
    }


    public String getPhoto() {
        return photo;
    }

    public Topic setPhoto(String photo) {
        this.photo = photo;
        return this;
    }
    public Integer getSubscriptionCount() {
        return subscriptionCount;
    }

    public Topic setSubscriptionCount(Integer subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
        return this;
    }

    @OneToMany( mappedBy = "topic")
    private List<LinkResource> linkresourcesList = new ArrayList<>();

    @OneToMany( mappedBy = "topic")
    private List<DocumentResource> documentresourcesList = new ArrayList<>();


 /*   @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private List<Subscription> subscriptions = new ArrayList<>();*/

    public Integer getId() {
        return id;
    }


    public Topic setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Topic setName(String name) {
        this.name = name;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Topic setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Topic setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Topic setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public Topic setVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }
/*
    public User getUser() {
        return user;
    }

    public Topic setUser(User user) {
        this.user = user;
        return this;
    }*/

  /*  public List<Resource> getResourcesList() {
        return resourcesList;
    }

    public Topic setResourcesList(List<Resource> resourcesList) {
        this.resourcesList = resourcesList;
        return this;
    }*/

/*    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public Topic setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }*/

    public List<LinkResource> getLinkresourcesList() {
        return linkresourcesList;
    }

    public Topic setLinkresourcesList(List<LinkResource> linkresourcesList) {
        this.linkresourcesList = linkresourcesList;
        return this;
    }

    public List<DocumentResource> getDocumentresourcesList() {
        return documentresourcesList;
    }

    public Topic setDocumentresourcesList(List<DocumentResource> documentresourcesList) {
        this.documentresourcesList = documentresourcesList;
        return this;
    }
}
