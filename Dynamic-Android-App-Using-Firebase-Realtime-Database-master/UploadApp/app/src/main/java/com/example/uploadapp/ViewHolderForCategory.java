package com.example.uploadapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderForCategory extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener,
        MenuItem.OnMenuItemClickListener {
    View view;
    private ViewHolderForCategory.OnItemClickListener mListener;

    public ViewHolderForCategory(@NonNull View itemView) {
        super(itemView);
        view=itemView;
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

    }

    public void setdetails(final Context context, final String name){
        TextView mtitletv =(TextView)view.findViewById(R.id.textview_category);
        if(name !=null){
        mtitletv.setText(name);
        }
    }



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if(mListener != null){
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION){
                /*case 1:
                        mListener.onWhatEverClick(position);
                        return true;

                     */
                if (menuItem.getItemId() == 1) {
                    mListener.OnDeleteClick(position);
                    return true;
                }
            }
        }
        return false;
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
        //MenuItem doWhatever = contextMenu.add(Menu.NONE,1,1,"Image Details");
        MenuItem delete = contextMenu.add(Menu.NONE,1,1,"Delete");

        //doWhatever.setOnMenuItemClickListener(this);
        delete.setOnMenuItemClickListener(this);

    }
    public interface OnItemClickListener{
        void onItemClick(int position);

        //void onWhatEverClick(int position);

        void OnDeleteClick(int position);
    }


    public void setOnItemClickListener(ViewHolderForCategory.OnItemClickListener listener){
        mListener = listener;
    }

}

