package com.jkkniugmail.rubel.coursedetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by islan on 12/5/2016.
 */

public class CourseAdapter extends ArrayAdapter  {
    ArrayList<Course> courseArrayList;
    Course course;
    Context context;
    DatabaseManager manager;

    public CourseAdapter(Context context, ArrayList<Course> courseArrayList) {
        super(context, R.layout.course_adapter_view, courseArrayList);
        this.context = context;
        manager = new DatabaseManager(context);
        this.courseArrayList = courseArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderCourse viewHolderCourse;
        View view;
        course = courseArrayList.get(position);
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.course_adapter_view, parent, false);
            viewHolderCourse = new ViewHolderCourse();

            viewHolderCourse.tv_course_title = (TextView) view.findViewById(R.id.tv_course_title_adapter);
            viewHolderCourse.tv_course_code = (TextView) view.findViewById(R.id.tv_course_code_adapter);


            view.setTag(viewHolderCourse);
        }
        else {
            view = convertView;
            viewHolderCourse = (ViewHolderCourse) convertView.getTag();
        }



        viewHolderCourse.tv_course_title.setText(course.getCourse_title().toString());
        viewHolderCourse.tv_course_code.setText(course.getCourse_code().toString());


        return view;
    }

    class ViewHolderCourse{
        TextView tv_course_title;
        TextView tv_course_code;
    }
}