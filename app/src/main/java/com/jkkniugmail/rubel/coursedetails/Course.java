package com.jkkniugmail.rubel.coursedetails;

/**
 * Created by islan on 12/4/2016.
 */

public class Course {
    private int course_id;
    private String course_title;
    private String course_code;
    private float course_fee;
    private int course_length;

    public Course() {
    }

    public Course(String course_title, String course_code, float course_fee, int course_length) {
        this.course_title = course_title;
        this.course_code = course_code;
        this.course_fee = course_fee;
        this.course_length = course_length;
    }

    public Course(int course_id, String course_title, String course_code, float course_fee, int course_length) {
        this.course_id = course_id;
        this.course_length = course_length;
        this.course_fee = course_fee;
        this.course_code = course_code;
        this.course_title = course_title;
    }

    public int getCourse_id() {
        return course_id;
    }

    public int getCourse_length() {
        return course_length;
    }

    public void setCourse_length(int course_length) {
        this.course_length = course_length;
    }

    public float getCourse_fee() {
        return course_fee;
    }

    public void setCourse_fee(float course_fee) {
        this.course_fee = course_fee;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }
}
