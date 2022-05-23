package com.WebApp.MovieNights.models;

import com.WebApp.MovieNights.models.BookmarksKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@IdClass(BookmarksKey.class)
@Table(name="bookmarks", schema = "public")
public class Bookmarks implements Serializable {

    @Id private String email;
    @Id private String title;

    /*
        constructors
     */
    public Bookmarks() {}

    public Bookmarks(String email, String title) {
        this.email = email;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}//end of class Bookmarks
