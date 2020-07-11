package com.example.retrive_image_tablayout;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.jar.Attributes;


public class TempFragment extends AppCompatActivity {


    //int key_index = intent.getIntExtra("index",1);
    RecyclerView mRecyclerview;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private FirebaseStorage mStorage;
    //Arrays url = new Arrays(String);
    ArrayList<Member> mUploads = new ArrayList<Member>();
    Button btnProductDetails;
    String REF = "";
    String URL = "";
    String DESC = "";
    String key = "";
    String PRICE = "";
    String NAME = "";
    Boolean CHK = false;
    Button btn_return;
    /*
    ViewHolderAboutCholi mView;
    Member m ;
    int key_index;

    public TempFragment(ViewHolderAboutCholi viewHolder, Member member, int i) {
        this.mView = viewHolder;
        this.m = member;
        this.key_index = i;
    }

 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_recycler);

        //btnProductDetails = findViewById(R.id.btn_productDetails);

        final Intent intent = getIntent();
        URL = intent.getStringExtra("Url");
        REF = intent.getStringExtra("REFF");
        key = intent.getStringExtra("key");
        NAME = intent.getStringExtra("name");
        DESC = intent.getStringExtra("desc");
        PRICE = intent.getStringExtra("price");

        //DESC = intent.getStringExtra("DESC");
        // pass.add(intent.getStringArrayListExtra("pass"));

        mRecyclerview = findViewById(R.id.temp_recycler);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
/*
        btn_return = (Button)findViewById(R.id.btn_back);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TempFragment.this, MainActivity.class);
                startActivity(intent1);
            }
        });

 */


        mStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //reference = firebaseDatabase.getReferenceFromUrl(URL);
        reference = firebaseDatabase.getReference().child(REF);
        System.out.println("Reference" + reference.getRef());
        /*if(reference.getRef().equals(REF)){
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();

        }

         */
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("Started");
        //final String t = intent.getStringExtra("url");
        //URL = pass.get(1);
        final FirebaseRecyclerAdapter<Member, ViewHolderAboutCholi> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Member, ViewHolderAboutCholi>(Member.class, R.layout.about_product, ViewHolderAboutCholi.class, reference) {
            @Override
            protected void populateViewHolder(ViewHolderAboutCholi viewHolder, Member member, int i) {
                //member.setKey(getRef(i).getKey());
                //if(btnProductDetails.callOnClick())
                //assert t != null;
                if (!CHK) {
                    if (key.equals(getRef(i).getKey())) {
//                if (URL.equals(member.getImageUrl())) {
                        mUploads.add(member);
                        ViewHolderAboutCholi.setdetails(getApplicationContext(), NAME, URL, PRICE, DESC);
                        CHK = true;
                        //viewHolder.setOnItemClickListener(HomeFragment.this);
                    } else {
                        startActivity(new Intent(TempFragment.this,MainActivity.class));
                    }
                }
            }
        };

        mRecyclerview.setAdapter(firebaseRecyclerAdapter);

    }
}
/*
    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this, "Normal Click"+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void MoreInfo(final int position, View view) {
        Member selectedItem = mUploads.get(position);
        //ViewHolderAboutCholi frag = (ViewHolderAboutCholi) getChildFragmentManager().getFragment().get()
        FirebaseRecyclerAdapter<Member,ViewHolderAboutCholi>firebaseRecyclerAdapter1=new FirebaseRecyclerAdapter<Member, ViewHolderAboutCholi>( Member.class,R.layout.about_product,ViewHolderAboutCholi.class,reference) {
            @Override
            protected void populateViewHolder(ViewHolderAboutCholi viewHolder, Member member, int i) {
                if(i==position) {
                    member.setKey(getRef(i).getKey());
                    //this.mU
                    //mUploads.add(member);
                    //urlList.add(i,member.getImageUrl());

                    ViewHolderAboutCholi.setdetails(Objects.requireNonNull(getActivity()).getApplicationContext(), member.getName(), member.getImageUrl(), member.getPrice(), member.getDescription());
                }
                else{
                    //Toast.makeText(HomeFragment.this, "Nothing Happens", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(HomeFragment.this, "Nothing Happen", Toast.LENGTH_SHORT).show();
                    System.out.println("Nothing Happen");
                }
            }
        };
        mRecyclerview.setAdapter(firebaseRecyclerAdapter1);
//        firebaseRecyclerAdapter.
        firebaseRecyclerAdapter1.notifyDataSetChanged();
    }



 */

