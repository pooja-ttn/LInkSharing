package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.CO.LinkResourceCO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "link_resource")
public class LinkResource extends Resource implements Serializable {

    @NotBlank(message = "URL can't be blank")
    @Column(name = "link_URL")
    String linkURL;


    public String getLinkURL() {
        return linkURL;
    }

    public LinkResource setLinkURL(String linkURL) {
        this.linkURL = linkURL;
        return this;
    }




}
