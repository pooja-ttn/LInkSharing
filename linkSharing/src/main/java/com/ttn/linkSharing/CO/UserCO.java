package com.ttn.linkSharing.CO;

import com.ttn.linkSharing.enums.Role;
import org.hibernate.validator.constraints.Email;

import java.util.Date;

public class UserCO {


        private Integer id;

        private String firstName;

        private String lastName;
        @Email(message = "Enter  valid email-id")
        private String email;

        private String userName;

        private String password;

        private String photo;

        private Boolean verified;

        private Role role;
     private Date createdDate;

    private Date updatedDate;

    public Integer getId() {
        return id;
    }

    public UserCO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserCO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserCO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public UserCO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserCO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserCO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public UserCO setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Boolean getVerified() {
        return verified;
    }

    public UserCO setVerified(Boolean verified) {
        this.verified = verified;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserCO setRole(Role role) {
        this.role = role;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public UserCO setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public UserCO setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public UserCO setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserCO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    private boolean active;

        private String confirmPassword;
}

