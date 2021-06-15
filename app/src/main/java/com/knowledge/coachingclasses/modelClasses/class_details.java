package com.knowledge.coachingclasses.modelClasses;

public class class_details {

    private String class_subject;
    private String class_name;
    private String class_teacher;
    private String class_code;
    private int backGround_value;

    //empty constructor
    public class_details() {
    }


    //constructor
    public class_details(String class_subject, String class_name, String class_teacher, String class_code, int backGround_value) {
        this.class_subject = class_subject;
        this.class_name = class_name;
        this.class_teacher = class_teacher;
        this.class_code = class_code;
        this.backGround_value = backGround_value;
    }

    //getters and setters


    public int getBackGround_value() {
        return backGround_value;
    }

    public void setBackGround_value(int backGround_value) {
        this.backGround_value = backGround_value;
    }

    public String getClass_subject() {
        return class_subject;
    }

    public void setClass_subject(String class_subject) {
        this.class_subject = class_subject;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_teacher() {
        return class_teacher;
    }

    public void setClass_teacher(String class_teacher) {
        this.class_teacher = class_teacher;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }
}
