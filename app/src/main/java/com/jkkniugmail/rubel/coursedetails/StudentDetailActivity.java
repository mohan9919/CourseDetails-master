package com.jkkniugmail.rubel.coursedetails;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.jkkniugmail.rubel.coursedetails.FinalValue.COURSE_ID;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.REQUEST_UPDATE_STUDENT;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.STUDENT_ID;

public class StudentDetailActivity extends AppCompatActivity {

    private TextView tv_first_name;
    private TextView tv_last_name;
    private TextView tv_phone_no;
    private TextView tv_email;
    private Button btn_delete;
    private Button btn_update;
    private Button btn_call;
    private Button btn_email;
    private Student student;
    private DatabaseManager manager;
    private int std_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        final Intent intent = getIntent();
        std_id = intent.getIntExtra(STUDENT_ID,0);

        manager = new DatabaseManager(this);
        student = manager.getStudent(std_id);

        tv_first_name = (TextView ) findViewById(R.id.tv_first_name);
        tv_last_name = (TextView ) findViewById(R.id.tv_last_name);
        tv_phone_no = (TextView ) findViewById(R.id.phone_no);
        tv_email = (TextView ) findViewById(R.id.email);
        btn_call = (Button) findViewById(R.id.callBtn);
        btn_delete = (Button) findViewById(R.id.personDeleteBtn);
        btn_update = (Button) findViewById(R.id.personEditBtn);

        tv_first_name.setText(student.getFirst_name().toString());
        tv_last_name.setText(student.getLast_name().toString());
        tv_phone_no.setText("Phone no : "+ student.getPhone_no().toString());
        tv_email.setText(   "Email    : "+ student.getEmail().toString());

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(manager.deleteStudent(student.getId())) {
                            Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                            //
                            setResult(RESULT_OK);
                            finish();
                        }
                        else {
                            Toast.makeText(v.getContext(), "Failed To delete", Toast.LENGTH_SHORT).show();
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
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(), StudentEditLayoutActivity.class);
                intent.putExtra(STUDENT_ID, student.getId());
                intent.putExtra(COURSE_ID, student.getCourse_id());
                startActivityForResult(intent, REQUEST_UPDATE_STUDENT);
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ student.getPhone_no()));
                if (ActivityCompat.checkSelfPermission(StudentDetailActivity.this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(StudentDetailActivity.this, android.Manifest.permission.CALL_PHONE)) {
                    }
                    else {
                        ActivityCompat.requestPermissions(StudentDetailActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                    }
                }

                v.getContext().startActivity(callIntent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_UPDATE_STUDENT&&resultCode==RESULT_OK){

            student = manager.getStudent(std_id);
            tv_first_name.setText(student.getFirst_name().toString());
            tv_last_name.setText(student.getLast_name().toString());
            tv_phone_no.setText("Phone no : "+ student.getPhone_no().toString());
            tv_email.setText(   "Email    : "+ student.getEmail().toString());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
