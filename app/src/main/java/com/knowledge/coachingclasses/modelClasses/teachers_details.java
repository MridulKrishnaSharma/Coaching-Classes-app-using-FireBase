package com.knowledge.coachingclasses.modelClasses;

public class teachers_details {
    private int drawable_value;
    private String teacherName;
    private String bio;


    public teachers_details(int drawable_value, String teacherName, String bio) {
        this.drawable_value = drawable_value;
        this.teacherName = teacherName;
        this.bio = bio;
    }
//g & s


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public teachers_details() {
    }

    public int getDrawable_value() {
        return drawable_value;
    }

    public void setDrawable_value(int drawable_value) {
        this.drawable_value = drawable_value;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
