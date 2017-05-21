package com.jkkniugmail.rubel.coursedetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.*;

/**
 * Created by islan on 12/5/2016.
 */

public class DatabaseManager {
    private DatabaseHelper helper;
    private SQLiteDatabase database;
    public DatabaseManager(Context context){
        helper = new DatabaseHelper(context);
    }

    private void openDatabase() {
        database = helper.getWritableDatabase();

    }

    private void closeDatabase() {
        database.close();
        helper.close();

    }

    public boolean addNewStudent(Student student){
        this.openDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_STUDENT_FIRST_NAME, student.getFirst_name());
        cv.put(COL_STUDENT_LAST_NAME, student.getLast_name());
        cv.put(COL_STUDENT_PHONENO, student.getPhone_no());
        cv.put(COL_STUDENT_EMAIL, student.getEmail());
        cv.put(COL_STUDENT_COURSE_ID, student.getCourse_id());

        long inserted = database.insert(STUDENT_TABLE_NAME, null, cv);
        this.closeDatabase();
        return (inserted>0);
    }

    public boolean updateStudent(Student student){
        this.openDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_STUDENT_FIRST_NAME, student.getFirst_name());
        cv.put(COL_STUDENT_LAST_NAME, student.getLast_name());
        cv.put(COL_STUDENT_PHONENO, student.getPhone_no());
        cv.put(COL_STUDENT_EMAIL, student.getEmail());
        cv.put(COL_STUDENT_COURSE_ID, student.getCourse_id());

        int updated = database.update(STUDENT_TABLE_NAME, cv, COL_STUDENT_ID + " = " + student.getId(), null);
        this.closeDatabase();
        return (updated>0);
    }

    public boolean addNewCourser(Course course){
        this.openDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COURSE_TITLE, course.getCourse_title());
        cv.put(COL_COURSE_CODE, course.getCourse_code());
        cv.put(COL_COURSE_LENGTH, course.getCourse_length());
        cv.put(COL_COURSE_FEE, course.getCourse_fee());

        long inserted = database.insert(COURSE_TABLE_NAME, null, cv);
        this.closeDatabase();
        return (inserted>0);
    }

    public boolean updateCoures(Course course){
        this.openDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_COURSE_TITLE, course.getCourse_title());
        cv.put(COL_COURSE_CODE, course.getCourse_code());
        cv.put(COL_COURSE_LENGTH, course.getCourse_length());
        cv.put(COL_COURSE_FEE, course.getCourse_fee());

        int updated = database.update(COURSE_TABLE_NAME, cv, COL_COURSE_ID+" = "+course.getCourse_id(), null);
        this.closeDatabase();
        return (updated>0);
    }

    public ArrayList<Course> getAllCourse(){
        ArrayList<Course> courseArrayList = new ArrayList<Course>();
        Cursor cursor;
        this.openDatabase();
        cursor = database.query(COURSE_TABLE_NAME, new String[]{
              COL_COURSE_ID, COL_COURSE_TITLE, COL_COURSE_CODE,
                COL_COURSE_LENGTH, COL_COURSE_FEE}, null, null, null,null, null);
        cursor.moveToFirst();
        if (cursor!=null) {
            for (int i = 0; i < cursor.getCount(); i++) {
                Course course;
                int id = cursor.getInt(cursor.getColumnIndex(COL_COURSE_ID));
                String title = cursor.getString(cursor.getColumnIndex(COL_COURSE_TITLE));
                String code = cursor.getString(cursor.getColumnIndex(COL_COURSE_CODE));
                int lenght = cursor.getInt(cursor.getColumnIndex(COL_COURSE_LENGTH));
                long fee = cursor.getLong(cursor.getColumnIndex(COL_COURSE_FEE));
                course = new Course(id, title, code, fee, lenght);
                courseArrayList.add(course);
                cursor.moveToNext();
            }
        }
        this.closeDatabase();
        return courseArrayList;
    }

    public ArrayList<Student> getAllStudentHavingCourse(int course_id){
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        Cursor cursor;
        this.openDatabase();
        cursor = database.query(STUDENT_TABLE_NAME, new String[]{COL_STUDENT_ID, COL_STUDENT_FIRST_NAME,
            COL_STUDENT_LAST_NAME, COL_STUDENT_PHONENO, COL_STUDENT_EMAIL, COL_STUDENT_COURSE_ID}, COL_STUDENT_COURSE_ID+"="+course_id, null, null, null, null);
        cursor.moveToFirst();
        if (cursor!=null){
            for (int i =0; i<cursor.getCount(); i++){
                Student student;
                int id = cursor.getInt(cursor.getColumnIndex(COL_STUDENT_ID));
                String first_name = cursor.getString(cursor.getColumnIndex(COL_STUDENT_FIRST_NAME));
                String last_name = cursor.getString(cursor.getColumnIndex(COL_STUDENT_LAST_NAME));
                String phone_no = cursor.getString(cursor.getColumnIndex(COL_STUDENT_PHONENO));
                String email = cursor.getString(cursor.getColumnIndex(COL_STUDENT_EMAIL));
                student = new Student(id,first_name, last_name, phone_no, email, course_id);
                studentArrayList.add(student);
                cursor.moveToNext();
            }
        }this.closeDatabase();
        return studentArrayList;
    }

    public boolean deleteStudent(int id){
        this.openDatabase();
        int deleted = database.delete(STUDENT_TABLE_NAME, COL_STUDENT_ID+"="+id,null);
        this.closeDatabase();
        return (deleted>0);
    }

    public boolean deleteCourse(int id){
        this.openDatabase();
        int deleted = database.delete(COURSE_TABLE_NAME, COL_COURSE_ID+"="+id,null);
        this.closeDatabase();
        return (deleted>0);
    }

    public Course getCourse(int course_id){
        Cursor cursor;
        Course course;
        this.openDatabase();
        cursor = database.query(COURSE_TABLE_NAME, new String[]{
                COL_COURSE_ID, COL_COURSE_TITLE, COL_COURSE_CODE,
                COL_COURSE_LENGTH, COL_COURSE_FEE}, COL_COURSE_ID+"="+course_id, null, null,null, null);
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndex(COL_COURSE_ID));
        String title = cursor.getString(cursor.getColumnIndex(COL_COURSE_TITLE));
        String code = cursor.getString(cursor.getColumnIndex(COL_COURSE_CODE));
        int lenght = cursor.getInt(cursor.getColumnIndex(COL_COURSE_LENGTH));
        long fee = cursor.getLong(cursor.getColumnIndex(COL_COURSE_FEE));
        course = new Course(id, title, code, fee, lenght);
        return course;
    }

    public Student getStudent(int student_id){
        Cursor cursor;
        this.openDatabase();
        Student student;
        cursor = database.query(STUDENT_TABLE_NAME, new String[]{COL_STUDENT_ID, COL_STUDENT_FIRST_NAME,
                COL_STUDENT_LAST_NAME, COL_STUDENT_PHONENO, COL_STUDENT_EMAIL, COL_STUDENT_COURSE_ID}, COL_STUDENT_ID+"="+student_id, null, null, null, null);
        cursor.moveToFirst();
            int course_id = cursor.getInt(cursor.getColumnIndex(COL_STUDENT_COURSE_ID));
            String first_name = cursor.getString(cursor.getColumnIndex(COL_STUDENT_FIRST_NAME));
            String last_name = cursor.getString(cursor.getColumnIndex(COL_STUDENT_LAST_NAME));
            String phone_no = cursor.getString(cursor.getColumnIndex(COL_STUDENT_PHONENO));
            String email = cursor.getString(cursor.getColumnIndex(COL_STUDENT_EMAIL));
            student = new Student(student_id,first_name, last_name, phone_no, email, course_id);

        this.closeDatabase();
        return student;
    }
}
