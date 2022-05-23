package com.WebApp.MovieNights.controllers;

import com.WebApp.MovieNights.models.Bookmarks;
import com.WebApp.MovieNights.database.Database;
import com.WebApp.MovieNights.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AppController {

    @Autowired
    private Database db;

    @RequestMapping("/")
    public String homePage() {
        return "index";
    }//end of method homePage

    @RequestMapping("/my_bookmarks")
    public String myBookmarksPage() {
        return "my_bookmarks";
    }//end of method myBookmarksPage

    @RequestMapping("/loggedin")
    public String loggedinPage() {
        return "loggedin";
    }//end of method loggedinPage

    @RequestMapping("/register_form")
    public String registerPage(){
        return "register";
    }//end of method registerPage

    @RequestMapping("/login_form")
    public String loginPage(HttpSession session) {
        session.invalidate();
        return "login";
    }//end of method loginPage

    @RequestMapping("/register")
    public String register(@ModelAttribute User user, HttpSession session) {

        System.out.println("---------register-----------");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());

        session.setAttribute("userEmail", user.getEmail());

        if (db.selectUser(user.getEmail()) == null) {
            System.out.println("Valid email");
            db.insertUser(user);
            System.out.println("Inserted");
            return "loggedin";
        } else {
            System.out.println("Invalid email");
            return "register";
        }//end if/else
    }//end of method register

    @RequestMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session) {

        System.out.println("---------login-------------");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());

        session.setAttribute("userEmail", user.getEmail());

        boolean validUser = false;
        ArrayList<User> users = (ArrayList<User>) db.userList();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().trim().equals(user.getEmail()) && users.get(i).getPassword().trim().equals(user.getPassword())) {
                System.out.println("Valid user");
                validUser = true;
                break;
            }//end of if
        }//end of for

        if (validUser) {
            System.out.println("Logged in");
            return "loggedin";
        }
        System.out.println("email or password is wrong!");
        return "login";
    }//end of method login

    @RequestMapping(value = "/save_movie", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public void saveMovie(@RequestBody String title, HttpSession session) {

        System.out.println("---------saveMovie-----------");
        System.out.println("Title: " + title);

        User user = db.selectUser((String)session.getAttribute("userEmail"));

        Bookmarks bookmark = new Bookmarks(user.getEmail(),title);
        db.insertBookmark(bookmark);

        System.out.println("Bookmark = email: " + user.getEmail() + " title: " + title);

    }//end of method saveMovie

    @RequestMapping(value = "/my_bookmarks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<String> getBookmarks(HttpSession session) {

        System.out.println("-------GetBookmarks--------");

        User user = db.selectUser((String)session.getAttribute("userEmail"));

        ArrayList<Bookmarks> bookmarksList = (ArrayList<Bookmarks>) db.bookmarksList();
        ArrayList<String> tempBookmarks = new ArrayList<>();

        for (int i = 0; i < bookmarksList.size(); i++) {
            if (bookmarksList.get(i).getEmail().trim().equals(user.getEmail())) {
                System.out.println("Email: " + bookmarksList.get(i).getEmail() + " & Title: " + bookmarksList.get(i).getTitle());
                tempBookmarks.add(bookmarksList.get(i).getTitle());
            }
        }

        return tempBookmarks;
    }//end of method getBookmarks

}//end of class AppController
