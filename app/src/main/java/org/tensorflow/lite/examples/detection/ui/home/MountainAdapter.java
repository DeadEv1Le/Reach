package org.tensorflow.lite.examples.detection.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.tensorflow.lite.examples.detection.R;

import java.util.List;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.ViewHolder> {
    private List<MountModel> mountainList;

    private Context context;
    public MountainAdapter(List<MountModel> mountainList, Context context) {
        this.mountainList = mountainList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MountModel mountain = mountainList.get(position);
        // Bind the mountain data to the views in the ViewHolder
        holder.mountainNameTextView.setText(mountain.getMountainName());
        holder.elevationTextView.setText(String.valueOf(mountain.getElevation()));

        Glide.with(context).load(mountainList.get(position).getItemImage()).into(holder.itemImage);


        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MountainInfoActivity.class);
                intent.putExtra("mountain",  mountainList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });



    }



    public void setMountainList(List<MountModel> mountainList) {
        this.mountainList = mountainList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mountainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mountainNameTextView;
        TextView elevationTextView;

        ImageView itemImage;
        LinearLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mountainNameTextView = itemView.findViewById(R.id.mountName1);
            elevationTextView = itemView.findViewById(R.id.mountainHeight);
            itemImage = itemView.findViewById(R.id.mountainItemImage);
            container = itemView.findViewById(R.id.imagesContainer);
        }

    }
}