package com.ttn.linkSharing.CO;

import com.ttn.linkSharing.enums.Visibility;


public class TopicCO {


    private String topicName;

    private Visibility visibility;
    private Integer postCount;

    private Integer subscriptionCount;

    public Integer getPostCount() {
        return postCount;
    }

    public TopicCO setPostCount(Integer postCount) {
        this.postCount = postCount;
        return this;
    }

    public Integer getSubscriptionCount() {
        return subscriptionCount;
    }

    public TopicCO setSubscriptionCount(Integer subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
        return this;
    }

    public String getTopicName() {
        return topicName;
    }

    public TopicCO setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }


    public Visibility getVisibility() {
        return visibility;
    }

    public TopicCO setVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }
}
