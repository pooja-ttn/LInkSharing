package com.ttn.linkSharing.DTO;

import com.ttn.linkSharing.entities.Rating;
import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.enums.Role;
import com.ttn.linkSharing.enums.Visibility;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",orphanRemoval = true)
    private List<Subscription> subscriptions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public UserDTO setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public UserDTO setRatings(List<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

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


    public String getFullName() {
        return firstName + " " + lastName;
    }
}
