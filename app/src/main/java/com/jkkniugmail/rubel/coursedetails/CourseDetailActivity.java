package com.jkkniugmail.rubel.coursedetails;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.jkkniugmail.rubel.coursedetails.FinalValue.COURSE_ID;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.REQUEST_ADD_STUDENT;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.REQUEST_STUDENT_DETAIL;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.REQUEST_UPDATE_COURSE;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.STUDENT_ID;

public class CourseDetailActivity extends BaseActivity {
    int course_id;
    TextView tv_course_title_code;
    TextView tv_course_length;
    TextView tv_course_fee;
    DatabaseManager manager;
    ListView listView;
    Course course;
    StudentAdepter studentAdepter;
    ArrayList<Student> studentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Intent intent = getIntent();
        course_id = intent.getIntExtra(COURSE_ID, 0);

        studentArrayList = new ArrayList<>();
        tv_course_title_code = (TextView) findViewById(R.id.tv_course_title_code);
        tv_course_length = (TextView) findViewById(R.id.tv_course_length);
        tv_course_fee = (TextView) findViewById(R.id.tv_course_fee);
        listView = (ListView) findViewById(R.id.list_students);


        manager = new DatabaseManager(this);
        course = manager.getCourse(course_id);

        tv_course_title_code.setText(course.getCourse_title() + " (" + course.getCourse_code() + ")");
        tv_course_length.setText(String.valueOf(course.getCourse_length() + " month"));
        tv_course_fee.setText(String.valueOf(course.getCourse_fee() + " tk"));

        showStudentList();

    }

    public void addNewStudent(View view) {
        Intent intent = new Intent(this, StudentEditLayoutActivity.class);
        intent.putExtra("course_id", course_id);
        startActivityForResult(intent, REQUEST_ADD_STUDENT);
    }

    public void showStudentList() {
        studentArrayList = manager.getAllStudentHavingCourse(course_id);
        studentAdepter = new StudentAdepter(this, studentArrayList);
        listView.setAdapter(studentAdepter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = studentArrayList.get(position);
                Intent intent = new Intent(view.getContext(), StudentDetailActivity.class);
                intent.putExtra(STUDENT_ID, student.getId());
                startActivityForResult(intent,REQUEST_STUDENT_DETAIL);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_STUDENT||requestCode == REQUEST_STUDENT_DETAIL) {
            if (resultCode == RESULT_OK)
                showStudentList();

        }
        else if(requestCode==REQUEST_UPDATE_COURSE){
            if(resultCode == RESULT_OK){
                course = manager.getCourse(course_id);
                tv_course_title_code.setText(course.getCourse_title() + " (" + course.getCourse_code() + ")");
                tv_course_length.setText(String.valueOf(course.getCourse_length() + " month"));
                tv_course_fee.setText(String.valueOf(course.getCourse_fee() + " tk"));
            }
        }
    }

    public void deleteCourse(final View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Alert!!");
        alert.setMessage("Are you sure to delete record");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(manager.deleteCourse(course_id)) {
                    setResult(RESULT_OK);
                    finish();
                }

                dialog.dismiss();

            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        alert.show();


    }

    public void updateCourse(View view) {
        Intent intent = new Intent(this, CourseAddLayoutActivity.class);
        intent.putExtra(COURSE_ID, course.getCourse_id());
        startActivityForResult(intent, REQUEST_UPDATE_COURSE);
    }
}