package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.CO.DocumentResourceCO;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "document_resource")

public class DocumentResource extends Resource implements Serializable {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
