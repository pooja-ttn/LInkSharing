package com.ttn.linkSharing.convertor;

import com.ttn.linkSharing.CO.*;
import com.ttn.linkSharing.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class COToEntityViceVersaConvertor {


    //CO To Entity
    public static User COconvertorToEntity(UserCO userCO)
    {
        User user=new User();
        if(Objects.nonNull(userCO))
        {
            BeanUtils.copyProperties(userCO,user);
        }
        return user;
    }

    public static User COconvertorToEntity(LoginCO loginCO)
    {
        User user=new User();
        if(Objects.nonNull(loginCO))
        {
            BeanUtils.copyProperties(loginCO,user);
        }
        return user;
    }



    //Entity to CO

    public static UserCO EntityconvertorToUserCO(User user)
    {
        UserCO userCO=null;
        if(Objects.nonNull(user))
        {
            BeanUtils.copyProperties(user,userCO);
        }
        return userCO;
    }



    public static LoginCO EntityconvertorToLoginCO(User user)
    {
        LoginCO loginCO=null;
        if(Objects.nonNull(user))
        {
            BeanUtils.copyProperties(user,loginCO);
        }
        return loginCO;
    }



    //CO To Entity
    public static Topic COconvertorToEntity(TopicCO topicCO)
    {
        Topic topic=new Topic();
        if(Objects.nonNull(topicCO))
        {
            BeanUtils.copyProperties(topicCO,topic);
        }
        return topic;
    }


    public static TopicCO EntityconvertorToLoginCO(Topic topic)
    {
        TopicCO topicCO=null;
        if(Objects.nonNull(topic))
        {
            BeanUtils.copyProperties(topic,topicCO);
        }
        return topicCO;
    }
    public static Subscription COconvertorToEntity(SubscriptionCO subscriptionCO)
    {
        Subscription subscription=null;
        if(Objects.nonNull(subscriptionCO))
        {
            BeanUtils.copyProperties(subscriptionCO,subscription);
        }
        return subscription;
    }


    public static SubscriptionCO EntityconvertorToLoginCO(Subscription subscription)
    {
        SubscriptionCO subscriptionCO=null;
        if(Objects.nonNull(subscription))
        {
            BeanUtils.copyProperties(subscription,subscriptionCO);
        }
        return subscriptionCO;
    }



    public static DocumentResource COconvertorToEntity(DocumentResourceCO documentResourceCO)
    {
        DocumentResource documentResource=new DocumentResource();
        if(Objects.nonNull(documentResourceCO))
        {
            BeanUtils.copyProperties(documentResourceCO,documentResource);
        }
        return documentResource;
    }


    public static DocumentResourceCO EntityconvertorToLoginCO(DocumentResource documentResource)
    {
        DocumentResourceCO documentResourceCO=new DocumentResourceCO();
        if(Objects.nonNull(documentResource))
        {
            BeanUtils.copyProperties(documentResource,documentResourceCO);
        }
        return documentResourceCO;
    }





    public static LinkResource COconvertorToEntity(LinkResourceCO linkResourceCO)
    {
        LinkResource linkResource=new LinkResource();
        if(Objects.nonNull(linkResourceCO))
        {
            BeanUtils.copyProperties(linkResourceCO,linkResource);
        }
        return linkResource;
    }


    public static LinkResourceCO EntityconvertorToLoginCO(LinkResource linkResource)
    {
        LinkResourceCO linkResourceCO=new LinkResourceCO();
        if(Objects.nonNull(linkResource))
        {
            BeanUtils.copyProperties(linkResource,linkResourceCO);
        }
        return linkResourceCO;
    }

}
