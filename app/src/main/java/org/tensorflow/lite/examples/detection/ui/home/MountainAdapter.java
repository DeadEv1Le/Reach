package org.tensorflow.lite.examples.detection.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tensorflow.lite.examples.detection.R;

import java.util.List;
import java.util.Locale;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.ViewHolder> {
    private List<Mountain> mountains;

    public MountainAdapter(List<Mountain> mountains) {
        this.mountains = mountains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mountain mountain = mountains.get(position);
        holder.mountainName.setText(mountain.getName());
        holder.mountainHeight.setText(String.format(Locale.getDefault(), "%d m", mountain.getHeight()));
        holder.mountainImage.setImageResource(mountain.getImage());
    }

    @Override
    public int getItemCount() {
        return mountains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mountainImage;
        public TextView mountainName;
        public TextView mountainHeight;

        public ViewHolder(View itemView) {
            super(itemView);
            mountainImage = itemView.findViewById(R.id.imageView);
            mountainName = itemView.findViewById(R.id.mountName1);
            mountainHeight = itemView.findViewById(R.id.mountainHeight);
        }
    }
}