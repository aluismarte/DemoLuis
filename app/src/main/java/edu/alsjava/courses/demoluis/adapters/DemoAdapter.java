package edu.alsjava.courses.demoluis.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import edu.alsjava.courses.demoluis.R;
import edu.alsjava.courses.demoluis.model.Example;

/**
 * Created by aluis on 11/26/19.
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

    private Activity activity;
    private final List<Example> data;

    public DemoAdapter(@NonNull Activity activity, @NonNull List<Example> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DemoViewHolder(LayoutInflater.from(activity).inflate(R.layout.cardview_demo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DemoViewHolder holder, int position) {
        holder.currentItem = data.get(position);
        holder.visualize();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DemoViewHolder extends RecyclerView.ViewHolder {

        private Example currentItem;

        private AppCompatTextView tvName;
        private AppCompatImageView ivImage;

        DemoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivImage = itemView.findViewById(R.id.ivImage);
        }

        void visualize() {
            tvName.setText(currentItem.getName());
            Glide.with(activity).load(currentItem.getImage()).diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.drawable.ic_autorenew_black_24dp).into(ivImage);
        }
    }

}
