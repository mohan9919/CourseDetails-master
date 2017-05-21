package com.jkkniugmail.rubel.coursedetails;

/**
 * Created by islan on 12/4/2016.
 */

public class Student extends Person{
    private int id;
    private int course_id;

    public Student() {
    }

    public Student(String first_name, String last_name, String phone_no, String email, int course_id) {

        setFirst_name(first_name);
        setLast_name(last_name);
        setPhone_no(phone_no);
        setEmail(email);
        setCourse_id(course_id);
    }

    public Student(int id, String first_name, String last_name, String phone_no, String email, int course_id) {

        this.id = id;
        setFirst_name(first_name);
        setLast_name(last_name);
        setPhone_no(phone_no);
        setEmail(email);
        setCourse_id(course_id);
    }

    public int getId() {
        return id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
