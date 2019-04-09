package com.ttn.linkSharing.DTO;

import com.ttn.linkSharing.enums.Role;
import com.ttn.linkSharing.enums.Visibility;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.util.Date;

public class UserDTO {
    private Integer id;

    private String firstName;

    private String lastName;
    @Email
    private String email;

    private String userName;

    private String password;

    private String photo;

    private Boolean verified;

    private Role role;
    private Date createdDate;

    private Date updatedDate;
    private  Integer topicId;
    private String createdBy;
    @CreatedDate
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;


    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    private String topicName;

    public Integer getId() {
        return id;
    }

    public UserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public UserDTO setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Boolean getVerified() {
        return verified;
    }

    public UserDTO setVerified(Boolean verified) {
        this.verified = verified;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserDTO setRole(Role role) {
        this.role = role;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public UserDTO setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public UserDTO setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public UserDTO setTopicId(Integer topicId) {
        this.topicId = topicId;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UserDTO setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public UserDTO setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public UserDTO setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public UserDTO setVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public String getTopicName() {
        return topicName;
    }

    public UserDTO setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
