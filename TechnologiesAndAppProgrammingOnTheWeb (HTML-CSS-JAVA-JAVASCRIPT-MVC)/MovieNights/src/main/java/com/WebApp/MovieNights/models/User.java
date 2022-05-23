package com.WebApp.MovieNights.models;

import javax.persistence.*;

@Entity
@Table(name="users", schema = "public")
public class User {

    @Id private String email;
    private String password;

    /*
        constructors
     */
    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /*
        Getters - Setters
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}//end of class User
