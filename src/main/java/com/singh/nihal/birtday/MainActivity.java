package com.singh.nihal.birtday;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button choose_image;
    EditText enter_name;
    ImageView imageView;
    ByteArrayOutputStream stream;
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choose_image=(Button)findViewById(R.id.button);
        enter_name=(EditText)findViewById(R.id.edit_text);
        imageView=(ImageView)findViewById(R.id.image);

        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
              startActivityForResult(intent ,  0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Uri uri=data.getData();

        try
        {
            Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
            BitmapDrawable bd=new BitmapDrawable(getResources(),bitmap);
            stream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            byteArray=stream.toByteArray();
            imageView.setImageBitmap(bitmap);

        }catch(FileNotFoundException e)
        {
            Toast.makeText(this, "image not found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Launch(View view) {

        Intent intent= new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("picture", byteArray);
        String name;
        name=enter_name.getText().toString();
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
