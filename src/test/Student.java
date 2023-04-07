package test;

import codec.Message;
import codec.MessageCodec;

import java.util.Objects;

public class Student implements MessageCodec<Student> {
    private String mName;
    private String mDept;
    private int mRollNo;

    public String getName() {
        return mName;
    }

    public String getDept() {
        return mDept;
    }

    public int getRollNo() {
        return mRollNo;
    }

    Student(String name, String dept, int rollNo ) {
        mName = name;
        mDept = dept;
        mRollNo = rollNo;

    }

    Student() {

         //To-do
    }

    @Override
    public boolean equals(Object o) {
        Student student = (Student) o;
        return mRollNo == student.mRollNo && Objects.equals(mName, student.mName) && Objects.equals(mDept, student.mDept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName, mDept, mRollNo);
    }

    @Override
    public Student decode(Message read) {
        mName = read.readString();
        mDept = read.readString();
        mRollNo = read.readInt();
        return this;
    }

    @Override
    public void encode(Message payload) {
        payload.writeString(mName);
        payload.writeString(mDept);
        payload.writeInt(mRollNo);

    }
    public static final Creator<Student> CREATOR = Student::new;



}
