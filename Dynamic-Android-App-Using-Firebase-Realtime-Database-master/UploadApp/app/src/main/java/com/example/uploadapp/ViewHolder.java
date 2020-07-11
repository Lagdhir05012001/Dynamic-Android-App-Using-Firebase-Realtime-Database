package com.example.uploadapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener,
        MenuItem.OnMenuItemClickListener {
    View view;
    private OnItemClickListener mListener;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        view=itemView;

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onClick(View view) {
        if(mListener != null){
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION){
                mListener.onItemClick(position);
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select Item");
        MenuItem doWhatever = contextMenu.add(Menu.NONE,1,1,"Image Details");
        MenuItem delete = contextMenu.add(Menu.NONE,2,2,"Delete");

        doWhatever.setOnMenuItemClickListener(this);
        delete.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if(mListener != null){
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION){
                    switch (menuItem.getItemId()){
                        case 1:
                            mListener.onWhatEverClick(position,view);
                            return true;
                        case 2:
                            mListener.OnDeleteClick(position);
                            return true;
                    }
            }
        }
        return false;
    }

    public void setdetails(final Context context, final String name, final String imageUrl, final String price, final String description ){
        TextView mtitletv =(TextView)view.findViewById(R.id.rTitleView);
        final ImageView mimagetv = (ImageView)view.findViewById(R.id.rImageView);
        TextView mprice = (TextView)view.findViewById(R.id.price_text_view);
        TextView mImgDesc =(TextView)view.findViewById(R.id.description);
        /*
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ImageView pdImg= view.findViewById(R.id.pd_imageView);
               TextView pdName = view.findViewById(R.id.pd_textname);
               TextView pdPrice = view.findViewById(R.id.pd_price);
               TextView pdDesc = view.findViewById(R.id.description);

               pdName.setText(name);
               pdPrice.setText(price);
               pdDesc.setText(description);
               Picasso.get().load(imageUrl).into(pdImg);
               Animation animation= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
               about_product.startAnimation(animation);

            }


        });


         */
        mtitletv.setText(name);
        mprice.setText(price);
        //mImgDesc.setText(description);
        System.out.println("image url "+imageUrl);

        //Glide.with(context).load(imageUrl).into(mimagetv);

        Picasso.get().load(imageUrl).into(mimagetv);

        Animation animation= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        itemView.startAnimation(animation);
    }

    public interface OnItemClickListener{
        void onItemClick(int position);

        void onWhatEverClick(int position, View view);

        void OnDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
