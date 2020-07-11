package com.example.uploadapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

public class New_Category extends AppCompatActivity  implements ViewHolderForCategory.OnItemClickListener{

    RecyclerView mRecyclerview;

    // RecyclerView mRecyclerview;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
     FirebaseStorage mStorage;
    ArrayList<Upload_Category> mUploads = new ArrayList<Upload_Category>();

    EditText add;
    ArrayList<String> myList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        add = findViewById(R.id.btn_add);

        Button btn_Add = (Button)findViewById(R.id.btn_cat);
        mRecyclerview = findViewById(R.id.category_recyclerview);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Categories");
        System.out.println("Reference" + reference.getRef());


        FireBaseRecycler();
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();

            }
        });


    }
    private void uploadData(){

        Upload_Category upload_category = new Upload_Category(add.getText().toString().trim());
        try {

                reference.push().setValue(upload_category);
                Toast.makeText(New_Category.this, "Upload successful", Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            Toast.makeText(New_Category.this, "Upload Failed", Toast.LENGTH_LONG).show();

        }
        }



    public void FireBaseRecycler(){
        FirebaseRecyclerAdapter<Upload_Category,ViewHolderForCategory> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Upload_Category, ViewHolderForCategory>(Upload_Category.class,R.layout.show_categories,ViewHolderForCategory.class,reference) {
            @Override
            protected void populateViewHolder(ViewHolderForCategory viewHolder, Upload_Category member, int i) {
               member.setKey(getRef(i).getKey());
                //this.mU
                myList.add(member.getCategory());
                mUploads.add(member);
                viewHolder.setdetails(getApplicationContext(),member.getCategory());
                viewHolder.setOnItemClickListener(New_Category.this);
            }
        };

        mRecyclerview.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.notifyDataSetChanged();

    }


    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this, "Normal Click"+position, Toast.LENGTH_SHORT).show();
        Upload_Category selectedCategory = mUploads.get(position);
        Intent intent = new Intent(New_Category.this,TemporaryActivity.class);
        intent.putExtra("category",selectedCategory.getCategory());
        intent.putStringArrayListExtra("myArrayList",myList);
        System.out.println(selectedCategory.getCategory());
        //wait(2000);

        startActivity(intent);
    }


    @Override
    public void OnDeleteClick(int position) {
        Upload_Category selectedCategory = mUploads.get(position);
        final String key = selectedCategory.getKey();
        String cat = selectedCategory.getCategory();
        DatabaseReference fdb = FirebaseDatabase.getInstance().getReference("Category");
        reference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(New_Category.this, "Deletion Successfull", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(New_Category.this, "Not Deleted", Toast.LENGTH_SHORT).show();

            }
        });

       /* imgRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                reference.child(selectedKey).removeValue();
                Toast.makeText(New_Category.this, "Deletion Successfull", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(New_Category.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        */
    }
}



