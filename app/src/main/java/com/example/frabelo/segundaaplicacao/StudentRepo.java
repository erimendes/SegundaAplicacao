package com.example.frabelo.segundaaplicacao;

/**
 * Created by FRabelo on 19/06/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tan on 2/5/2015.
 */
public class StudentRepo {
    private DBHelper dbHelper;

    public StudentRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    //Delete all data in the table
    public void Delete() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Student.TABLE, null, null);
        db.close(); // Closing database connection
    }

    //Insert student records
    public int insert(Student student) {
        // TODO Auto-generated method stub
        //Integer noStudent = getStudentCount();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student.KEY_age, student.getAge());
        values.put(Student.KEY_email, student.getEmail());
        values.put(Student.KEY_name, student.getName());

        // Inserting Row
        long student_Id = db.insert(Student.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) student_Id;
    }
    //Retrieve all records and populate into List<Student>
    //This method allow you to retrieve more fields/information into List.
    public List<Student> getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Student.KEY_ID + "," +
                Student.KEY_name + "," +
                Student.KEY_email + "," +
                Student.KEY_age +
                " FROM " + Student.TABLE;

        List<Student> studentList = new ArrayList<Student>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student  = new Student();
                student.setAge(cursor.getInt(cursor.getColumnIndex(Student.KEY_age)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(Student.KEY_email)));
                student.setName(cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
                student.setStudent_ID(cursor.getInt(cursor.getColumnIndex(Student.KEY_ID)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

    //Retrieve all records and populate into List<String>
    public List<String> getAll_Simple() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Student.KEY_age + "," +
                Student.KEY_name +
                " FROM " + Student.TABLE;

        List<String> studentList = new ArrayList<String>() ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                studentList.add(i,cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
                i+=1;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }

}