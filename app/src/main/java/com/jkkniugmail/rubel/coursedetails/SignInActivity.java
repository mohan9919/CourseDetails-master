package com.jkkniugmail.rubel.coursedetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.jkkniugmail.rubel.coursedetails.FinalValue.NOT_REGISTERED;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.SIGNED_IN;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.SIGNED_OUT;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.USER_EMAIL;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.USER_NAME;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.USER_PASSWORD;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.USER_SIGN_FLAG;


public class SignInActivity extends AppCompatActivity {
    private TextView sign_head_tv;
    private EditText user_name_et;
    private EditText user_email_et;
    private EditText user_password_et;
    private EditText user_repassword_et;
    private Button sign_in_btn;

    private String user_email;
    private String user_password;

    private int sign_flag;

    private Intent intent;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        retrieveUser();

        sign_head_tv = (TextView) findViewById(R.id.sign_head);
        user_name_et = (EditText) findViewById(R.id.user_name);
        user_email_et = (EditText) findViewById(R.id.user_email);
        user_password_et = (EditText) findViewById(R.id.user_password);
        user_repassword_et = (EditText) findViewById(R.id.user_re_password);
        sign_in_btn = (Button) findViewById(R.id.user_sign_btn);


        if (sign_flag==NOT_REGISTERED){
            sign_in_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveUser();
                }
            });

        }
        else if (sign_flag==SIGNED_OUT){
            sign_head_tv.setText("Sign In");
            sign_in_btn.setText("Sign In");
            user_name_et.setVisibility(View.GONE);
            user_repassword_et.setVisibility(View.GONE);
            sign_in_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    matchUser();
                }
            });
        }
        else if (sign_flag==SIGNED_IN){
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void retrieveUser(){
        this.user_email = sharedPreferences.getString(USER_EMAIL, "not fount");
        this.user_password = sharedPreferences.getString(USER_PASSWORD, "not found");
        this.sign_flag = sharedPreferences.getInt(USER_SIGN_FLAG, 0);
        sharedPreferences.getAll();
    }

    public void saveUser() {
        String user_name;
        String user_email;
        String user_password;
        String user_repassword;
        user_name = user_name_et.getText().toString().trim();
        user_email = user_email_et.getText().toString().trim();
        user_password = user_password_et.getText().toString().trim();
        user_repassword = user_repassword_et.getText().toString().trim();
        if(user_name.isEmpty()||user_email.isEmpty()||user_password.isEmpty()||user_repassword.isEmpty()){
            if(user_name.isEmpty()){
                user_name_et.setError("please enter a name");
            }
            if (user_email.isEmpty()){
                user_email_et.setError("please enter email");
            }
            if (user_password.isEmpty()){
                user_repassword_et.setError("please enter a password");
            }
            if (user_repassword.isEmpty()){
                user_repassword_et.setError("please re enter password");
            }
        }
        else{
            if (!user_password.equals(user_repassword)){
                user_repassword_et.setError("password does not match");
            }
            else{
                editor.putString(USER_NAME, user_name);
                editor.putString(USER_EMAIL, user_email);
                editor.putString(USER_PASSWORD, user_password);
                editor.putInt(USER_SIGN_FLAG, SIGNED_IN);
                editor.commit();
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }

    void matchUser(){
        String user_email;
        String user_password;
        user_email = user_email_et.getText().toString().trim();
        user_password = user_password_et.getText().toString().trim();
        if(user_email.isEmpty()||user_password.isEmpty()){
            if (user_email.isEmpty()){
                user_email_et.setError("please enter email");
            }
            if (user_password.isEmpty()){
                user_repassword_et.setError("please enter a password");
            }
        }
        else{
            if(this.user_email.equals(user_email) && this.user_password.equals(user_password)){
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                editor.putInt(USER_SIGN_FLAG, SIGNED_IN);
                editor.commit();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                user_email_et.setText("");
                user_password_et.setText("");
                user_email_et.setError("wrong email or password");
                user_password_et.setError("wrong email or password");
            }
        }
    }

    public void cencelSigning(View view) {
        finish();
    }
}
