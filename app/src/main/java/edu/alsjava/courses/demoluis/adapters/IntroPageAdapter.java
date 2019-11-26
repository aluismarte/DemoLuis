package edu.alsjava.courses.demoluis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * Created by aluis on 11/2/19.
 */
public class IntroPageAdapter extends PagerAdapter {

    private final Context context;
    private final int[] layouts;

    public IntroPageAdapter(@NonNull Context context, @NonNull int[] layouts) {
        this.context = context;
        this.layouts = layouts;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        View itemView = LayoutInflater.from(context).inflate(layouts[position], container, false);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
