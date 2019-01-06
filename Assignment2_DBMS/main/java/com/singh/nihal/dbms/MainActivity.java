package com.singh.nihal.dbms;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelperClass mdb;
    EditText name,surname,marks,id;
    Button add, view,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        surname=findViewById(R.id.surname);
        marks=findViewById(R.id.marks);
        add=findViewById(R.id.add);
        view =findViewById(R.id.view);
        update=findViewById(R.id.update);
        id=findViewById(R.id.id);
        delete=findViewById(R.id.delete);

        mdb=new DatabaseHelperClass(this);
        addData();
        viewAlldata();
        update();
        deleteData();
    }

    public void addData()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean inserted= mdb.insertData(name.getText().toString(),surname.getText().toString(),marks.getText().toString());

               if(inserted)
                   Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(MainActivity.this, "Data insertion fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void viewAlldata()
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor res= mdb.getAllData();

               if(res.getCount()==0) {
                 message("Error","No Data Found");
                   return;
               }
               else
               {
                   StringBuffer buffer=new StringBuffer();
                   while(res.moveToNext())
                   {
                       buffer.append("ID :"+res.getString(0)+"\n");
                       buffer.append("NAME :"+res.getString(1)+"\n");
                       buffer.append("SURNAME :"+res.getString(2)+"\n");
                       buffer.append("MARKS :"+res.getString(3)+"\n\n");
                   }
                   //show all data
                   message("Data",buffer.toString());
               }
            }
        });
    }

    public void message(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void update(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isupdate= mdb.updateData(id.getText().toString(),name.getText().toString(),surname.getText().toString(),marks.getText().toString());
               if(isupdate==true)
                   Toast.makeText(MainActivity.this, "Data is updated", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(MainActivity.this, "Data is not updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteData()
    {
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int row_number=mdb.deleteData(id.getText().toString());
               if(row_number!=0)
                   Toast.makeText(MainActivity.this, row_number+" deleted", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(MainActivity.this, row_number+" deleted", Toast.LENGTH_SHORT).show();
           }
       });
    }
}
