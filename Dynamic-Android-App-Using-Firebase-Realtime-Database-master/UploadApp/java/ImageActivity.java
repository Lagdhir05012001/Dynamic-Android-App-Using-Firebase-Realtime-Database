package com.example.uploadapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ImageActivity  extends AppCompatActivity implements ViewHolder.OnItemClickListener {

    RecyclerView mRecyclerview;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private FirebaseStorage mStorage;
    ArrayList<Upload> mUploads = new ArrayList<Upload>();
    Button btnProductDetails;
    String REF = "";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


       String Category =  getIntent().getStringExtra("category");
        textView = findViewById(R.id.textView);
        mRecyclerview=findViewById(R.id.recyclerview);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mStorage = FirebaseStorage.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child(Category);
        REF = Category;
        System.out.println("Reference"+reference.getRef());
        textView.setText(Category);
    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Upload,ViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Upload, ViewHolder>(Upload.class,R.layout.image_item,ViewHolder.class,reference) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Upload member, int i) {
                member.setKey(getRef(i).getKey());
                //this.mU
                mUploads.add(member);
                //urlList.add(i,member.getImageUrl());
                viewHolder.setdetails(getApplicationContext(),member.getName(),member.getImageUrl(),member.getPrice(),member.getDescription());
                viewHolder.setOnItemClickListener(ImageActivity.this);
            }
        };

        mRecyclerview.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Normal Click"+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWhatEverClick(final int position, View view) {

                Upload selectedItem = mUploads.get(position);

                Intent intent = new Intent(ImageActivity.this, ProductDetails.class);

                intent.putExtra("key",selectedItem.getKey());
                intent.putExtra("Url",  selectedItem.getImageUrl());
                intent.putExtra("REFF",REF);
                intent.putExtra("name",selectedItem.getName());
                intent.putExtra("price",selectedItem.getPrice());
                intent.putExtra("desc",selectedItem.getDescription());

                startActivity(intent);

    }

    @Override
    public void OnDeleteClick(int position) {
        Upload selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();
        System.out.println("Url = "+selectedItem.getImageUrl());
        StorageReference imgRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        imgRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                reference.child(selectedKey).removeValue();
                Toast.makeText(ImageActivity.this, "Deletion Successfull", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ImageActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}