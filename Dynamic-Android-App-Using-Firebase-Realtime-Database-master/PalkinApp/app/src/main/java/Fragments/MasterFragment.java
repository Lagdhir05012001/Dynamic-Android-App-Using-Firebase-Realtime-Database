package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrive_image_tablayout.Member;
import com.example.retrive_image_tablayout.R;
import com.example.retrive_image_tablayout.Temporary;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Objects;

public class MasterFragment extends Fragment implements ViewHolder.OnItemClickListener{


    RecyclerView mRecyclerview;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private FirebaseStorage mStorage;
    //Arrays url = new Arrays(String);
    ArrayList<Member> mUploads = new ArrayList<Member>();
    TextView text;
    String REF = "";
    String URL = "";
    ArrayList<String> pass = new ArrayList<>();
    String[] childrens = new String[4];
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_home, container, false);
        mRecyclerview=(RecyclerView)view.findViewById(R.id.recyclerview);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        //btnProductDetails = (Button)view.findViewById(R.id.btn_back);

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Choli");
        System.out.println("Reference"+reference.getRef());
        //pass.add(0,"Choli/");
        REF = "Choli/";
        // REF = "Choli/";
//        FirebaseRecyclerAdapter<Member, ViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Member, ViewHolder>(Member.class,R.layout.images,ViewHolder.class,reference) {
//            @Override
//            protected void populateViewHolder(ViewHolder viewHolder, Member member, int i) {
//                viewHolder.setdetails(Objects.requireNonNull(getActivity()).getApplicationContext(),member.getName(),member.getImageurl());
//            }
//        };
//
//        mRecyclerview.setAdapter(firebaseRecyclerAdapter);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //btnProductDetails = (Button)view.findViewById(R.id.btn_back);
        System.out.println("View Created");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("Started");
        final FirebaseRecyclerAdapter<Member, ViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Member, ViewHolder>(Member.class,R.layout.choli_images, ViewHolder.class,reference) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Member member, int i) {
                member.setKey(getRef(i).getKey());
                //REF = getRef(i).toString();
                //Toast.makeText(getActivity().getApplicationContext(),getRef(i).toString(),Toast.LENGTH_SHORT).show();
                //if(btnProductDetails.callOnClick())

                mUploads.add(member);
                viewHolder.setdetails(Objects.requireNonNull(getActivity()).getApplicationContext(),member.getName(),member.getImageUrl(),member.getPrice(),member.getDescription());
                viewHolder.setOnItemClickListener(MasterFragment.this);

            }
        };

        mRecyclerview.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public void onItemClick(int position) {
        //Toast.makeText(this, "Normal Click"+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void MoreInfo(final int position, View view) {
        final Member selectedItem = mUploads.get(position);

        //URL = selectedItem.getImageUrl();
        Intent intent = new Intent(getActivity(), Temporary.class);
        String temp = "com.example.retrive_image_tablayout";

        //pass.add(1,selectedItem.getImageUrl());
        //pass.add(2,selectedItem.getDescription());
        //intent.setClassName(temp, String.valueOf(TempFragment.class));
        intent.putExtra("key",selectedItem.getKey());
        intent.putExtra("Url",  selectedItem.getImageUrl());
        intent.putExtra("REFF",REF);
        intent.putExtra("name",selectedItem.getName());
        intent.putExtra("price",selectedItem.getPrice());
        intent.putExtra("desc",selectedItem.getDescription());

        startActivity(intent);

    }

}
