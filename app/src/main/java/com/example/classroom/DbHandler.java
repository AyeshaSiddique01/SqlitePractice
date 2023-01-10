package com.example.classroom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {
    public static final String name = "name";
    public static final String age = "age";
    public static final String email = "email";
    public static final String id = "id";
    public static final String Student_Table = "StudentTable";

    public DbHandler(@Nullable Context context) {
        super(context, "studentDB.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + Student_Table + "(" + id + " Integer PRIMARY KEY AUTOINCREMENT, " + name + " Text, " + email + " Text, " + age + " Int) ";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Student_Table);
        onCreate(db);
    }

    public ArrayList<Student> getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + Student_Table, null);

        ArrayList<Student> studentArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {

                studentArrayList.add(new Student(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getInt(3)));
            } while (cursorCourses.moveToNext());

        }

        cursorCourses.close();
        return studentArrayList;
    }

    public void AddStudent(Student std) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "Insert into " + Student_Table + "(" + name + ", " + email + ", " + age +") Values(" + std.getName() + ", " + std.getEmail() +", " + std.age + ") ";
        db.execSQL(insertQuery);
    }
}
