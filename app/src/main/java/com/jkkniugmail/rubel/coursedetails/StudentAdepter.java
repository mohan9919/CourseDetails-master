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

public class StudentAdepter extends ArrayAdapter{
    Student student;
    Context context;
    ArrayList<Student> studentArrayList = new ArrayList<>();
    DatabaseManager manager;

    public StudentAdepter(Context context, ArrayList<Student> studentArrayList) {
        super(context,R.layout.student_adapter_view, studentArrayList);
        this.context = context;
        this.studentArrayList = studentArrayList;
        manager = new DatabaseManager(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderStudent viewHolderStudent;
        student = studentArrayList.get(position);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.student_adapter_view, parent, false);
            viewHolderStudent = new ViewHolderStudent();
            viewHolderStudent.tv_full_name = (TextView) convertView.findViewById(R.id.full_name);
            convertView.setTag(viewHolderStudent);
        }
        else {

            viewHolderStudent = (ViewHolderStudent) convertView.getTag();
        }

        viewHolderStudent.tv_full_name.setText(student.getFirst_name().toString()+" "+ student.getLast_name().toString());

        return convertView;
    }

    class ViewHolderStudent {
        private TextView tv_full_name;

    }
}
