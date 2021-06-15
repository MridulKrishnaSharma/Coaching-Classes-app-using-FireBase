package com.knowledge.coachingclasses.modelClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class user implements Serializable {

    // Basic details of user
    private String userId;
    private String displayName;
    private String imageUrl;
    private String phoneNumber;
    private String classes;

    // Cources code details
    private ArrayList<String> classCodes = new ArrayList<>();
    private int points;

    // different Constructors
    //empty
    public user(){this.classCodes = new ArrayList<>();}

    // all fields
    public user(String userId, String displayName, String imageUrl, String phoneNumber, String classes, ArrayList<String> classCodes, int points) {
        this.classCodes = new ArrayList<>();
        this.userId = userId;
        this.displayName = displayName;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.classes = classes;
        this.classCodes = classCodes;
        this.points = points;

    }

    //basic detrails

    public user(String userId, String displayName, String imageUrl, String phoneNumber, String classes) {
        this.classCodes = new ArrayList<>();
        this.userId = userId;
        this.displayName = displayName;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.classes = classes;
    }


    // getters and setters

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public ArrayList<String> getClassCodes() {
        return classCodes;
    }

    public void setClassCodes(ArrayList<String> classCodes) {
        this.classCodes = classCodes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
