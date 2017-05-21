package com.jkkniugmail.rubel.coursedetails;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.jkkniugmail.rubel.coursedetails.FinalValue.*;

/**
 * Created by islan on 12/5/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_course_table = "CREATE TABLE " + COURSE_TABLE_NAME + " ( "
                +COL_COURSE_ID+" INTEGER PRIMARY KEY, "
                +COL_COURSE_TITLE+" TEXT UNIQUE, "
                +COL_COURSE_CODE+" TEXT UNIQUE, "
                +COL_COURSE_LENGTH+" INTEGER, "
                +COL_COURSE_FEE+" NUMBER )";


        String sql_student_table = "CREATE TABLE " +STUDENT_TABLE_NAME+" ( "
                +COL_STUDENT_ID+" INTEGER PRIMARY KEY, "
                +COL_STUDENT_FIRST_NAME+" TEXT, "
                +COL_STUDENT_LAST_NAME+" TEXT, "
                +COL_STUDENT_PHONENO+" TEXT UNIQUE, "
                +COL_STUDENT_EMAIL+" TEXT UNIQUE, "
                +COL_STUDENT_COURSE_ID+" INTEGER,"
                +"FOREIGN KEY("+COL_STUDENT_COURSE_ID+" ) REFERENCES "
                +COURSE_TABLE_NAME+" ( "+COL_COURSE_ID+" ) ON DELETE CASCADE ON UPDATE CASCADE ) ";

        db.execSQL(sql_course_table);
        db.execSQL(sql_student_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
