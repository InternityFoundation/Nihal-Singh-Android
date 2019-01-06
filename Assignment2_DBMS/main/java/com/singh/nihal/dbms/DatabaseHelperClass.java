package com.singh.nihal.dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String ID="ID";
    public static final String NAME="NAME";
    public static final String SURNAME="SURNAME";
    public static final String MARKS="MARKS";

    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name,String surname,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(SURNAME,surname);
        contentValues.put(MARKS,marks);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String surname,String marks)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(SURNAME,surname);
        contentValues.put(MARKS,marks);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});
        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return(sqLiteDatabase.delete(TABLE_NAME, "ID = ?",new String[] {id}));
    }
}
