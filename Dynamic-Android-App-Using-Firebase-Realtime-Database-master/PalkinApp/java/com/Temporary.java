package com.example.retrive_image_tablayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Temporary extends AppCompatActivity {
    Button btnProductDetails;
    String REF = "";
    String URL = "";
    String DESC = "";
    String key = "";
    String PRICE = "";
    String NAME = "";
    Boolean CHK = false;
    TextView name,price,desc;
    ImageView imgview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_product);


        final Intent intent = getIntent();
        URL = intent.getStringExtra("Url");
        REF = intent.getStringExtra("REFF");
        key = intent.getStringExtra("key");
        NAME = intent.getStringExtra("name");
        DESC = intent.getStringExtra("desc");
        PRICE = intent.getStringExtra("price");

        name = findViewById(R.id.pd_textname);
        price = findViewById(R.id.pd_price);
        desc = findViewById(R.id.imgdesc);
        imgview = findViewById(R.id.pd_imageView);

        name.setText(NAME);
        price.setText(PRICE);
        desc.setText(DESC);
        //imgview.setImageDrawable(imgview);
        Picasso.get().load(URL).into(imgview);
        //Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.slide_in_left);
        //startAnimation(animation);
    }


}
