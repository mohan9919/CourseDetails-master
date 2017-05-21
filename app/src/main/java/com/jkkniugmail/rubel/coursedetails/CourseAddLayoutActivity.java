package com.jkkniugmail.rubel.coursedetails;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.jkkniugmail.rubel.coursedetails.FinalValue.COURSE_ID;

public class CourseAddLayoutActivity extends BaseActivity {

    private EditText et_course_title;
    private EditText et_course_code;
    private EditText et_course_fee;
    private EditText et_course_length;
    int course_id;
    DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add_layout);
        manager = new DatabaseManager(this);
        Intent intent= getIntent();
        course_id = intent.getIntExtra(COURSE_ID, 0);

        //view reference
        et_course_title = (EditText) findViewById(R.id.et_course_title);
        et_course_code = (EditText) findViewById(R.id.et_course_code);
        et_course_fee = (EditText) findViewById(R.id.et_course_fee);
        et_course_length = (EditText) findViewById(R.id.et_course_length);

        if(course_id!=0){
            Course course = manager.getCourse(course_id);
            et_course_title.setText(course.getCourse_title());
            et_course_code.setText(course.getCourse_code());
            et_course_length.setText(String.valueOf(course.getCourse_length()));
            et_course_fee.setText(String.valueOf(course.getCourse_fee()));
        }

    }

    public void goCourseBtnAction(View view) {
        if(view.getId()==R.id.course_add){
            String course_title=et_course_title.getText().toString();
            String course_code=et_course_code.getText().toString();

            String fee = et_course_fee.getText().toString();
            String lenght = et_course_length.getText().toString();
            if(TextUtils.isEmpty(fee)){
                fee="00";
            }
            if(TextUtils.isEmpty(lenght)){
                lenght ="00";
            }

            float course_fee=Float.parseFloat(fee);
            int course_length=Integer.parseInt(lenght);

            if(TextUtils.isEmpty(course_title)&&TextUtils.isEmpty(course_code)){
                et_course_title.setError("course title required");
                et_course_code.setError("course code required");
            }
            else if(TextUtils.isEmpty(course_title)){
                et_course_title.setError("course title required");
            }
            else if(TextUtils.isEmpty(course_code)){
                et_course_code.setError("course code required");
            }

            else{

                if(this.course_id==0){
                    Course course = new Course(course_title, course_code, course_fee, course_length);

                    if(manager.addNewCourser(course)) {
                        setResult(RESULT_OK);
                        finish();
                    }
                    else{
                        showToast("Canceled");
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                }
                else if(this.course_id!=0){
                    Course course = new Course(this.course_id,course_title, course_code, course_fee, course_length);
                    if(manager.updateCoures(course)) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }

                else{
                    showToast("Canceled");
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }


        }

        else if (view.getId()==R.id.course_cancel){
            showToast("Canceled");
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
