package com.jkkniugmail.rubel.coursedetails;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentEditLayoutActivity extends BaseActivity {
    private EditText et_first_name;
    private EditText et_last_name;
    private EditText et_phone_no;
    private EditText et_email;
    private int course_id;
    private int student_id;
    DatabaseManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit_layout);
        manager = new DatabaseManager(this);
        et_first_name = (EditText) findViewById(R.id.et_first_name);
        et_last_name = (EditText) findViewById(R.id.et_last_name);
        et_phone_no = (EditText) findViewById(R.id.et_phone_no);
        et_email = (EditText) findViewById(R.id.et_email);
        Intent intent = getIntent();
        course_id = intent.getIntExtra("course_id", -1);
        student_id = intent.getIntExtra("student_id", -1);

        if(student_id!=-1){
            Student st = manager.getStudent(student_id);
            et_first_name.setText(st.getFirst_name());
            et_last_name.setText(st.getLast_name());
            et_phone_no.setText(st.getPhone_no());
            et_email.setText(st.getEmail());

        }
    }

    public void goStudentBtnAction(View view) {
        if(view.getId()==R.id.person_add){
            String first_name = et_first_name.getText().toString();
            String last_name = (et_last_name.getText().toString());
            String phone_no = et_phone_no.getText().toString();
            String email = et_email.getText().toString();

            if(TextUtils.isEmpty(first_name)&&TextUtils.isEmpty(last_name)&&TextUtils.isEmpty(phone_no)&&TextUtils.isEmpty(email)){
                et_first_name.setError("required");
                et_last_name.setError("required");
                et_phone_no.setError("required");
                et_email.setError("required");
            }
            else if(TextUtils.isEmpty(first_name)&&TextUtils.isEmpty(last_name)&&TextUtils.isEmpty(phone_no)){
                et_first_name.setError("required");
                et_last_name.setError("required");
                et_phone_no.setError("required");
            }
            else if(TextUtils.isEmpty(first_name)&&TextUtils.isEmpty(last_name)){
                et_first_name.setError("required");
                et_last_name.setError("required");
            }
            else if(TextUtils.isEmpty(first_name)&&TextUtils.isEmpty(last_name)){
                et_first_name.setError("required");
            }
            else if(TextUtils.isEmpty(last_name)){
                et_last_name.setError("required");
            }
            else if(TextUtils.isEmpty(phone_no)){
                et_phone_no.setError("required");
            }
            else if(TextUtils.isEmpty(email)){
                et_email.setError("required");
            }
            else {
                if (student_id == -1) {
                    Student student = new Student(first_name, last_name, phone_no, email, course_id);
                    if (manager.addNewStudent(student)) {
                        showToast("inserted");
                        setResult(RESULT_OK);
                        finish();

                    }
                    else {
                        showToast("canceled");
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                }
                else{
                    Student student = new Student(student_id, first_name, last_name, phone_no, email, course_id);
                    DatabaseManager manager = new DatabaseManager(this);
                    if (manager.updateStudent(student)){
                        showToast("updated");
                        setResult(RESULT_OK);
                        finish();
                    }
                    else {
                        showToast("canceled");
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                }

            }
        }
        else if(view.getId()==R.id.person_cancel){
            showToast("Canceled");
            setResult(RESULT_CANCELED);
            finish();
        }


    }

    public void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
