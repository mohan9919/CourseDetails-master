package com.jkkniugmail.rubel.coursedetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import static com.jkkniugmail.rubel.coursedetails.FinalValue.SIGNED_OUT;
import static com.jkkniugmail.rubel.coursedetails.FinalValue.USER_SIGN_FLAG;

/**
 * Created by islan on 12/10/2016.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent menuIntn;
        switch (item.getItemId()) {
            case R.id.menu_home :{
                menuIntn = new Intent(this, MainActivity.class);
                startActivity(menuIntn);
                finish();
                return true;
            }
            case R.id.menu_courses :{
                menuIntn = new Intent(this, CourseListView.class);
                startActivity(menuIntn);
                finish();
                return true;
            }

            case R.id.menu_log_out :{
                SharedPreferences sharedPreferences;
                SharedPreferences.Editor editor;
                sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putInt(USER_SIGN_FLAG, SIGNED_OUT);
                editor.commit();

                menuIntn = new Intent(this, SignInActivity.class);
                startActivity(menuIntn);
                finishAffinity();
                finish();
                return true;
            }
        }
        return true;

    }


}
