package com.example.uploadapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ProductDetails extends AppCompatActivity {
    String REF = "";
    String URL = "";
    String DESC = "";
    String key = "";
    String PRICE = "";
    String NAME = "";
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
        Picasso.get().load(URL).into(imgview);
    }


}
