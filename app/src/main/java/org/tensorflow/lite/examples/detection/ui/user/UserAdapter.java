package org.tensorflow.lite.examples.detection.ui.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.tensorflow.lite.examples.detection.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<TouristPost> postsList;

    public UserAdapter(List<TouristPost> postsList) {
        this.postsList = postsList;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recTitle);
            categoryTextView = itemView.findViewById(R.id.postCategory);
            userNameTextView = itemView.findViewById(R.id.recUserName);
            placeNameTextView = itemView.findViewById(R.id.recDesc);
            postImageView = itemView.findViewById(R.id.recImage);
        }
    }
}

