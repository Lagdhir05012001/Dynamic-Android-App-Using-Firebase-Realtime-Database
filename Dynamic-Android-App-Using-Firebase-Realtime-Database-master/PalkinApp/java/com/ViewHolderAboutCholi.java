package com.example.retrive_image_tablayout;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrive_image_tablayout.R;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ViewHolderAboutCholi extends  RecyclerView.ViewHolder {
     static View view;

    public ViewHolderAboutCholi(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
    }




    public static void setdetails(final Context context, final String name, final String imageUrl, final String price, final String description){
        ImageView pdImg= view.findViewById(R.id.pd_imageView);
        TextView pdName = view.findViewById(R.id.pd_textname);
        TextView pdPrice = view.findViewById(R.id.pd_price);
        TextView imgdesc = view.findViewById(R.id.imgdesc);



        pdName.setText(name);
        pdPrice.setText(price);
        if(description !=null)
            imgdesc.setText(description);
        if(imageUrl.isEmpty()){

        }
        Picasso.get().load(imageUrl).into(pdImg);
        Animation animation= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        view.startAnimation(animation);

    }



}
