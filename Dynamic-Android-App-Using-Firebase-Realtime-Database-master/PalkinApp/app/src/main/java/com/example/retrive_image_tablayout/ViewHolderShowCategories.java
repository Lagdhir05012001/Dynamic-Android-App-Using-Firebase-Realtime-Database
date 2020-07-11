package com.example.retrive_image_tablayout;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderShowCategories extends RecyclerView.ViewHolder{
    View view;

    public ViewHolderShowCategories(@NonNull View itemView) {
        super(itemView);
        view  = itemView;
    }


    public void setdetails(final Context context, final String name){
        TextView mtitletv =(TextView)view.findViewById(R.id.textview_category);
        if(name !=null){
            mtitletv.setText(name);
        }
    }
}
