package com.example.tasklist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tasklist.data.Task

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, "tasks.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE TASKS (name TEXT, description TEXT, data TEXT, status TEXT)")
        db.execSQL("INSERT INTO TASKS(name, description, data, status) VALUES (\"Wash the dishes\", \"Chores\", \"too many dishes\", \"DONE\"), (\"Do the homework\", \"C#\", \"5 minute homework\", \"DO IT ON SATURDAY\"), (\"Do the homework\", \"Java\", \"3 hours homework\", \"DO IT ON SUNDAY\"), (\"Buy fruits\", \"Grocery\", \"Buy for 100zl\", \"DO IT ON FRIDAY\"), (\"Learn\", \"Android\", \"Learn fragments\", \"DONE\"), (\"Homework\", \"Add database\", \"read documentation\", \"DO IT IN 3 HOURS FROM NOW\"), (\"Clean\", \"Tidy your room\", \"tidy it good\", \"DO IT DURING THE WEEKEND\")")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //TODO
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //TODO
    }
    public fun refactorToList(): ArrayList<Task>{
        val read = readableDatabase;
        val cursor = read.query("TASKS", null, null, null, null, null, null)
        val readTheDatabase = ArrayList<Task>();
        if(cursor.moveToFirst()) {
            do {
                val name : String = cursor.getString(0);
                val desc : String= cursor.getString(1);
                val date : String = cursor.getString(2);
                val status : String = cursor.getString(3);
                readTheDatabase.add(Task(name, desc, date, status));
            }while(cursor.moveToNext());
        }
        return readTheDatabase;
    }
}