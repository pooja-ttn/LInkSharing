package com.ttn.linkSharing.entities;
import com.ttn.linkSharing.enums.Visibility;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "topic",uniqueConstraints = @UniqueConstraint(columnNames = {"name","created_by"}))
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name="created_by")
    private String createdBy;
    @CreatedDate
    @Column(name = "created_on")
    private Date createdOn;
    @UpdateTimestamp
    @Column(name="updated_on")
    private Date updatedOn;


    @Enumerated(EnumType.STRING)
    private Visibility visibility;


    }
