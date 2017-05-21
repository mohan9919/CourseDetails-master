package com.jkkniugmail.rubel.coursedetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button allCourseBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button reference
        allCourseBtn = (Button) findViewById(R.id.our_courses);
    }

    public void goOnClick(View view) {

                intent = new Intent(this, CourseListView.class);
                startActivity(intent);
                finish();

    }
}
