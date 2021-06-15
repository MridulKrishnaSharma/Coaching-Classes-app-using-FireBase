package com.knowledge.coachingclasses.modelClasses;

import com.google.firebase.Timestamp;

public class notification {

    private String header;
    private String notice;
    private Timestamp createdAt;
    private String url;


    public notification() { // empty constructor
    }

    public notification(String header, String notice, Timestamp createdAt, String url) {
        this.header = header;
        this.notice = notice;
        this.createdAt = createdAt;
        this.url = url;
    }



    //getter setter


    public Timestamp getCreatedAt() {
        return createdAt;
    }

//    public void setCreatedAt(Timestamp createdAt) {
//        this.createdAt = createdAt;
//    }

    public String getHeader() {
        return header;
    }

//    public void setHeader(String header) {
//        this.header = header;
//    }

    public String getNotice() {
        return notice;
    }

//    public void setNotice(String notice) {
//        this.notice = notice;
//    }

    public String getUrl() {
        return url;
    }

//    public void setUrl(String url) {
//        this.url = url;
//    }

}
