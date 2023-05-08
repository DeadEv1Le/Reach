package org.tensorflow.lite.examples.detection.ui.post;

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

import java.util.ArrayList;
import java.util.List;
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    public List<DataClass> dataList;
    boolean isLiked = false;
    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass data = dataList.get(position);
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recUserName.setText(dataList.get(position).getUserName());
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recDesc.setText(dataList.get(position).getPlaceName());
        holder.postCategory.setText(dataList.get(position).getCategory());
        holder.recDesc.setVisibility(View.VISIBLE);

        holder.recTitle.setVisibility(View.VISIBLE);

         // initialize the flag to false




        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("PlaceName", dataList.get(holder.getAdapterPosition()).getPlaceName());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Category", dataList.get(holder.getAdapterPosition()).getCategory());
                intent.putExtra("Username", dataList.get(holder.getAdapterPosition()).getUserName());
                intent.putExtra("PostAddedUserImage", dataList.get(holder.getAdapterPosition()).getUserImage());
                intent.putExtra("Contacts", dataList.get(holder.getAdapterPosition()).getContacts());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView recImage;
    TextView recUserName, recDesc, postCategory, recTitle, postContacts;
    CardView recCard;



    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recUserName = itemView.findViewById(R.id.recUserName);
        recDesc = itemView.findViewById(R.id.recDesc);
        postCategory = itemView.findViewById(R.id.postCategory);
        recTitle = itemView.findViewById(R.id.recTitle);
        postContacts = itemView.findViewById(R.id.detailContact);
    }
}