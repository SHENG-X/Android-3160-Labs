package com.shengxiao.recycleviewtesting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.studentViewHoder> {
    List<Student> studentList;
    TextView studentNo, studentName;

    public StudentAdapter(List<Student> studentList) {
        this.studentList=studentList;
    }

    @Override
    public studentViewHoder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View vh=layoutInflater.inflate(R.layout.activity_student_adapter,parent,false);
        studentViewHoder studentViewHoder=new studentViewHoder(vh);
        return studentViewHoder;
    }

    @Override
    public void onBindViewHolder(studentViewHoder holder, int position) {
        studentName.setText(studentList.get(position).getStudentName().toString());
        studentNo.setText(studentList.get(position).getStudentNo().toString());
    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class studentViewHoder extends RecyclerView.ViewHolder{
        public studentViewHoder(View itemView) {
            super(itemView);
            studentName=(TextView)itemView.findViewById(R.id.studentName);
            studentNo=(TextView)itemView.findViewById(R.id.studentNo);
        }
        public void addStudent(int position, Student student){
            studentList.add(position,student);
        }

    }
}
