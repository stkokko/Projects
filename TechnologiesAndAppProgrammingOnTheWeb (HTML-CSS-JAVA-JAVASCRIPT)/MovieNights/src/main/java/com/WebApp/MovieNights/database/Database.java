package com.WebApp.MovieNights.database;

import com.WebApp.MovieNights.repositories.DatabaseBookmarksRepository;
import com.WebApp.MovieNights.repositories.DatabaseUserRepository;
import com.WebApp.MovieNights.models.Bookmarks;
import com.WebApp.MovieNights.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Database {

    @Autowired private DatabaseUserRepository userRepository;
    @Autowired private DatabaseBookmarksRepository bookmarksRepository;

    public List<Bookmarks> bookmarksList() {
        return bookmarksRepository.findAll();
    }

    public void insertBookmark(Bookmarks bookmark) {
        bookmarksRepository.save(bookmark);
    }

    public List<User> userList() {
        return userRepository.findAll();
    }

    public void insertUser(User user) {
        userRepository.save(user);
    }

    public User selectUser(String email) {
        return userRepository.findById(email).orElse(null);
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }
}
