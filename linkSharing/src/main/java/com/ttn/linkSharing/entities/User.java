package com.ttn.linkSharing.entities;

import com.ttn.linkSharing.enums.Role;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;
@Column(name="last_name")
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;


    @Column(unique = true,name = "user_name")
   private String userName;

     private String password;

    private String photo;

   private Boolean active;
    private Boolean verified;


   @Enumerated(EnumType.STRING)
   private Role role;

   @Temporal(TemporalType.DATE)
   @CreatedDate
   @Column(name = "created_date")
   private Date createdDate;

    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",orphanRemoval = true)
    private List<Subscription> subscriptions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user",orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public User setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public User setRatings(List<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

    public Boolean getVerified() {
        return verified;
    }

    public User setVerified(Boolean verified) {
        this.verified = verified;
        return this;
    }
    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public User setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public User setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public User setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public User setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

}
