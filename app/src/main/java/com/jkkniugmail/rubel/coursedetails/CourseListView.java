package com.jkkniugmail.rubel.coursedetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.jkkniugmail.rubel.coursedetails.FinalValue.REQUEST_ADD_COURSE;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.REQUEST_COURSE_DETAIL;

public class CourseListView extends BaseActivity {
    private ListView listView;
    private DatabaseManager manager;
    private CourseAdapter courseAdapter;
    private ArrayList<Course> courseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_view);

        listView = (ListView) findViewById(R.id.course_list);
        manager = new DatabaseManager(this);
        courseArrayList = new ArrayList<>();

        showCourseList();

    }

    public void showCourseList(){

        courseArrayList = manager.getAllCourse();
        courseAdapter = new CourseAdapter(this, courseArrayList);
        listView.setAdapter(courseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = courseArrayList.get(position);
                Intent intent = new Intent(view.getContext(), CourseDetailActivity.class);
                intent.putExtra("course_id", course.getCourse_id());
                startActivityForResult(intent,REQUEST_COURSE_DETAIL);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showCourseList();
    }

    public void addNewCourse(View view) {
        Intent intent = new Intent(this, CourseAddLayoutActivity.class);
        startActivityForResult(intent, REQUEST_ADD_COURSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_ADD_COURSE &&  resultCode == RESULT_OK){
            showCourseList();
        }
    }
}
