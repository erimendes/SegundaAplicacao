package com.example.frabelo.segundaaplicacao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;

import com.example.frabelo.segundaaplicacao.Student;
import com.example.frabelo.segundaaplicacao.StudentRepo;


public class MainActivity extends ActionBarActivity implements   AdapterView.OnItemSelectedListener  {

    Spinner mSpinStudent, mSpinStudent_Simple;
    TextView mTvName, mTvAge, mTvEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpinStudent = (Spinner) findViewById(R.id.spinStudent);
        mSpinStudent_Simple= (Spinner) findViewById(R.id.spinStudent_Simple);

        mTvAge= (TextView) findViewById(R.id.tvAge);
        mTvEmail= (TextView) findViewById(R.id.tvEmail);
        mTvName= (TextView) findViewById(R.id.tvName);

        mSpinStudent.setOnItemSelectedListener(this);
        mSpinStudent_Simple.setOnItemSelectedListener(this);

        insertDummyData();
        loadStudent();
        loadStudent_Simple();
    }

    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        if (parentView== findViewById(R.id.spinStudent)){
            Student selected = (Student) parentView.getItemAtPosition(position);
            mTvName.setText("Name: " + selected.getName());
            mTvAge.setText( "Age: " + selected.getAge().toString());
            mTvEmail.setText("Email: " + selected.getEmail());
        }
        else if (parentView== findViewById(R.id.spinStudent_Simple)){
            //This is the limitation of this method
            // as no other information could use to display
            //student record except the text.
            mTvName.setText(((TextView) selectedItemView).getText());
            mTvAge.setText("I DON'T KNOW, THIS IS NOT POSSIBLE!!!");
            mTvEmail.setText("I DON'T KNOW YOU NEED TO QUERY DB!!!");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        mTvAge.setText( "");
        mTvEmail.setText( "");
        mTvName.setText("");
    }

    //Insert some dummy data for displaying on spinner
    private void insertDummyData(){

        StudentRepo repo = new StudentRepo(this);
        repo.Delete();

        for (Integer i=0;i<5;i++){
            Student student = new Student();
            student.setAge(10);
            student.setEmail("tutor@instinctcoder.com");
            student.setName("Tan Woon How " + i.toString());

            repo.insert(student);


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //This is the arrayadapter  binding method as you can see
    private void loadStudent_Simple(){
        ArrayAdapter<String> spinnerAdapter;
        StudentRepo db = new StudentRepo(getApplicationContext());
        List<String> students = db.getAll_Simple();
        spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, students);
        mSpinStudent_Simple.setAdapter(spinnerAdapter);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    //This is the customize adapter binding method as you can see
    //there is only a slight different if you compare with functino loadStudent_Simple.
    private void loadStudent(){
        StudentSpinnerAdapter  studentAdapter;
        StudentRepo db = new StudentRepo(getApplicationContext());
        List<Student> students = db.getAll();
        studentAdapter = new StudentSpinnerAdapter(MainActivity.this,
                android.R.layout.simple_spinner_item , students );
        mSpinStudent.setAdapter(studentAdapter);

        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}