package com.shengxiao.recycleviewtesting;

/**
 * Created by ShengXiao on 2018-01-13.
 */

public class Student {
    private String studentNo, studentName;

    public String getStudentNo() {
        return studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public Student(String studentNo, String studentName) {
        this.studentNo = studentNo;

        this.studentName = studentName;
    }
}
