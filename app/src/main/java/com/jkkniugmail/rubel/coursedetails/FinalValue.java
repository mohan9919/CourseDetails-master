package com.jkkniugmail.rubel.coursedetails;

/**
 * Created by islan on 12/6/2016.
 */

public abstract class FinalValue {
    //request code
    public static final int REQUEST_ADD_STUDENT = 1;
    public static final int REQUEST_ADD_COURSE = 2;
    public static final int REQUEST_UPDATE_STUDENT = 3;
    public static final int REQUEST_UPDATE_COURSE = 4;
    public static final int REQUEST_DELETE_STUDENT = 5;
    public static final int REQUEST_USER_SIGN_FLAGDELETE_COURSE = 4;
    public static final int REQUEST_STUDENT_DETAIL = 5;
    public static final int REQUEST_COURSE_DETAIL = 6;

    //user information

    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_SIGN_FLAG = "user_sign_flag";

    public static final int NOT_REGISTERED = 0;
    public static final int SIGNED_IN = 1;
    public static final int SIGNED_OUT = 2;


    //
    public static final String COURSE_ID = "course_id";
    public static final String STUDENT_ID = "student_id";


    //
    //database helper
    //
    //database
    public static final String DATABASE_NAME = "bdjobs";
    public static final int DATABASE_VERSION = 1;

    //table
    public static final String STUDENT_TABLE_NAME = "students";
    public static final String COURSE_TABLE_NAME = "courses";

    //course coloum
    public static final String COL_COURSE_ID = "id";
    public static final String COL_COURSE_TITLE = "title";
    public static final String COL_COURSE_FEE = "fee";
    public static final String COL_COURSE_LENGTH = "length";
    public static final String COL_COURSE_CODE = "code";

    //student coloum
    public static final String COL_STUDENT_ID = "id";
    public static final String COL_STUDENT_FIRST_NAME = "first_name";
    public static final String COL_STUDENT_LAST_NAME = "last_name";
    public static final String COL_STUDENT_PHONENO = "phone_no";
    public static final String COL_STUDENT_EMAIL = "email";
    public static final String COL_STUDENT_COURSE_ID = "course_id";

}
