package com.example.classroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText age;
    EditText email;
    Button addBtn;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding
        name = findViewById(R.id.EnterName);
        age = findViewById(R.id.EnterAge);
        email = findViewById(R.id.EnterEmail);
        addBtn = findViewById(R.id.AddButton);
        listView = findViewById(R.id.ListOfStudents);
        //Db handler
        DbHandler db = new DbHandler(this);
        //show all students
        ArrayList<Student> arrayList = db.getAllStudents();
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student std = new Student(name.getText().toString(), email.getText().toString(), Integer.parseInt(age.getText().toString()));
                db.AddStudent(std);
            }
        });
    }
}