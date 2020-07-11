package com.example.retrive_image_tablayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class ShowCategories extends AppCompatActivity {
    FirebaseStorage mStorage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RecyclerView mRecyclerView;
    Button home_btn;
    ArrayList<Upload_Categories> mUpload = new ArrayList<Upload_Categories>();
    ArrayList<String> myList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_categories);
        mRecyclerView =(RecyclerView)findViewById(R.id.ShowCategoryRecyclerView);
        home_btn = (Button)findViewById(R.id.btn_Home);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });



        mStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Categories");
        FireBaseRecycler();



    }

    private void openMainActivity() {
        Intent intent = new Intent(ShowCategories.this,MainActivity.class);
        intent.putStringArrayListExtra("myArrayList",myList);
        startActivity(intent);

    }

    public void FireBaseRecycler(){
        FirebaseRecyclerAdapter<Upload_Categories,ViewHolderShowCategories> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Upload_Categories, ViewHolderShowCategories>(Upload_Categories.class,R.layout.showcategory_items,ViewHolderShowCategories.class,reference) {
            @Override
            protected void populateViewHolder(ViewHolderShowCategories viewHolder, Upload_Categories member, int i) {
                //member.setKey(getRef(i).getKey());
                //this.mU
                myList.add(member.getCategory());
                mUpload.add(member);
                viewHolder.setdetails(getApplicationContext(),member.getCategory());
                //viewHolder.setOnItemClickListener(New_Category.this);
            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.notifyDataSetChanged();
    }

}
