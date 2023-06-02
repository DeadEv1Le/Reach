package org.tensorflow.lite.examples.detection.ui.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.tensorflow.lite.examples.detection.R;
import org.tensorflow.lite.examples.detection.ui.post.DetailActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<TouristPost> postsList;
    private Context context;

    public UserAdapter(List<TouristPost> postsList, Context context) {

        this.postsList = postsList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TouristPost post = postsList.get(position);

        // Set the data to the views in the ViewHolder
        holder.titleTextView.setText(post.getDataTitle());
        holder.categoryTextView.setText(post.getCategory());
        holder.userNameTextView.setText(post.getUserName());
        holder.placeNameTextView.setText(post.getPlaceName());
        // Set other views accordingly

        // You can also load images using Glide or any other image loading library
        Glide.with(holder.itemView.getContext())
                .load(post.getDataImage())
                .into(holder.postImageView);


        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostUserDetail.class);
                intent.putExtra("Image", postsList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description", postsList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("PlaceName", postsList.get(holder.getAdapterPosition()).getPlaceName());
                intent.putExtra("Title", postsList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Category", postsList.get(holder.getAdapterPosition()).getCategory());
                intent.putExtra("Username", postsList.get(holder.getAdapterPosition()).getUserName());
                intent.putExtra("PostAddedUserImage", postsList.get(holder.getAdapterPosition()).getUserImage());
                intent.putExtra("Contacts", postsList.get(holder.getAdapterPosition()).getContacts());
                intent.putExtra("Key",postsList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView categoryTextView;
        public TextView userNameTextView;
        public TextView placeNameTextView;
        public ImageView postImageView;

        public CardView recCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recTitle);
            categoryTextView = itemView.findViewById(R.id.postCategory);
            userNameTextView = itemView.findViewById(R.id.recUserName);
            placeNameTextView = itemView.findViewById(R.id.recDesc);
            postImageView = itemView.findViewById(R.id.recImage);
            recCard = itemView.findViewById(R.id.recCard);
        }
    }
}

