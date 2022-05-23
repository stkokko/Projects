package com.WebApp.MovieNights.models;

import java.io.Serializable;

public class BookmarksKey implements Serializable {
    protected String email;
    protected String title;

    /*
        constructors
     */
    public BookmarksKey() {}

    public BookmarksKey(String email, String title) {
        this.email = email;
        this.title = title;
    }
}
