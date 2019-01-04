package com.singh.nihal.birtday;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    ImageView imageView2;
    TextView textname;
    Animation frombottom,frombottom2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textname = (TextView) findViewById(R.id.textname);
        imageView2 = (ImageView) findViewById(R.id.image2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");


        Bundle bundle = getIntent().getExtras();
        byte[] image = bundle.getByteArray("picture");

           Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
           imageView2.setImageBitmap(bmp);
           textname.setText(name);

           frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
           frombottom2=AnimationUtils.loadAnimation(this,R.anim.frombottom2);
           imageView2.startAnimation(frombottom);
           textname.startAnimation(frombottom2);
    }

}
