package com.example.frabelo.segundaaplicacao;

/**
 * Created by FRabelo on 19/06/2015.
 */
public class Student {
    // Labels table name
    public static final String TABLE = "Student";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_email = "email";
    public static final String KEY_age = "age";

    private int student_ID;
    private String name;
    private String email;
    private int age;

    public void setAge(Integer age){
        this.age=age;
    }

    public Integer getAge(){
        return this.age;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }


    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return this.email;
    }


    public void setStudent_ID(Integer student_ID){
        this.student_ID=student_ID;
    }

    public Integer getStudent_ID(){
        return this.student_ID;
    }

}