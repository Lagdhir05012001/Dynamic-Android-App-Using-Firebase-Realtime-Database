package Fragments;

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

import com.example.retrive_image_tablayout.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener,
MenuItem.OnMenuItemClickListener{
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
        MenuItem MoreInfo = contextMenu.add(Menu.NONE,1,1,"More Information");
        //MenuItem delete = contextMenu.add(Menu.NONE,2,2,"Delete");

        MoreInfo.setOnMenuItemClickListener(this);
        //delete.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if(mListener != null){
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION){
                if (menuItem.getItemId() == 1) {
                    mListener.MoreInfo(position, view);
                    return true;
//                    case 2:
//                        mListener.OnDeleteClick(position);
//                        return true;
                }
            }
        }
        return false;
    }

    public void setdetails(Context context, String name, String imageUrl, String price, String description){
        TextView mtitletv =view.findViewById(R.id.rTitleView);
        ImageView mimagetv = view.findViewById(R.id.rImageView);
        TextView mprice = view.findViewById(R.id.price_text_view);
        //TextView mdec = view.findViewById(R.id)
        mtitletv.setText(name);
        mprice.setText(price);
        System.out.println("image url "+imageUrl);

        //Glide.with(context).load(imageUrl).into(mimagetv);

        Picasso.get().load(imageUrl).into(mimagetv);

        Animation animation= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        itemView.startAnimation(animation);
    }

    public interface OnItemClickListener{
        void onItemClick(int position);

        void MoreInfo(int position, View view);

        //void OnDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

}
