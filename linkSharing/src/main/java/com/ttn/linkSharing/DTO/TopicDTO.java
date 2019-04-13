package com.ttn.linkSharing.DTO;

import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.enums.Visibility;
import com.ttn.linkSharing.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class TopicDTO {

    @Autowired
    TopicService topicService;

    private String name;

    private String createdBy;

    private Date createdOn;

    private Date updatedOn;


    private Visibility visibility;




    public TopicService getTopicService() {
        return topicService;
    }

    public TopicDTO setTopicService(TopicService topicService) {
        this.topicService = topicService;
        return this;
    }

    public String getName() {
        return name;
    }

    public TopicDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TopicDTO setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public TopicDTO setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public TopicDTO setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public TopicDTO setVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }
/*
    public List<Topic> getTopicList() {
        return topicList;
    }

    public TopicDTO setTopicList() {
        this.topicList = topicService.topicCreatedBy(this.createdBy);
        return this;
    }*/




}
