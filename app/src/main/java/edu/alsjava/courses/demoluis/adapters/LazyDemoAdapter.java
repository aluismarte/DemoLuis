package edu.alsjava.courses.demoluis.adapters;

import android.app.Activity;
import android.util.SparseArray;
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
import edu.alsjava.courses.demoluis.model.RetrieveDemo;
import edu.alsjava.courses.demoluis.model.network.request.DemoRequest;
import edu.alsjava.courses.demoluis.tasks.DemoTask;

/**
 * Created by aluis on 11/26/19.
 */
public class LazyDemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RetrieveDemo {

    private static final int VIEW_TYPE_ITEM = 0;

    private Activity activity;
    private int size = 0;
    private boolean loading = false;
    private SparseArray<Example> items = null;

    public LazyDemoAdapter(@NonNull Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) == null) {
            if (!loading) {
                new DemoTask(new DemoRequest(position - 1), this).execute();
                loading = true;
            }
            return 1;
        }
        return VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            return new LazyDemoViewHolder(LayoutInflater.from(activity).inflate(R.layout.cardview_demo, parent, false));
        }
        return new LoadingViewHolder(LayoutInflater.from(activity).inflate(R.layout.cardview_loading, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LazyDemoViewHolder) {
            LazyDemoViewHolder lazyDemoViewHolder = (LazyDemoViewHolder) holder;
            lazyDemoViewHolder.currentItem = items.get(position);
            lazyDemoViewHolder.visualize();
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }

    @Override
    public void history(int offset, int total, List<Example> examples) {
        size = total;
        if (items == null) {
            items = new SparseArray<>(size);
        }
        if (examples != null) {
            for (int i = 0; i < examples.size(); i++) {
                items.put(offset + i, examples.get(i));
            }
            loading = false;
            this.notifyDataSetChanged();
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class LazyDemoViewHolder extends RecyclerView.ViewHolder {

        private Example currentItem;

        private AppCompatTextView tvName;
        private AppCompatImageView ivImage;

        LazyDemoViewHolder(@NonNull View itemView) {
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
