package edu.alsjava.courses.demoluis.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import edu.alsjava.courses.demoluis.R;
import edu.alsjava.courses.demoluis.model.BigData;

public class ImageViewerActivity extends AppCompatActivity {

    public static final String IMAGE_URL_EXTRA = "IMAGE_URL_EXTRA";
    public static final String BIG_DATA_EXTRA = "BIG_DATA_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

        BigData bigData;
        String image;
        Intent intent = getIntent();
        if (intent != null) {
            image = intent.getStringExtra(IMAGE_URL_EXTRA);
            bigData = intent.getParcelableExtra(BIG_DATA_EXTRA);
        } else {
            Toast.makeText(this, "No image Loaded", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (bigData != null) {
            Toast.makeText(this, bigData.toString(), Toast.LENGTH_SHORT).show();
        }

        AppCompatImageView ivImage = findViewById(R.id.ivImage);
        Glide.with(this).load(image).diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.drawable.ic_autorenew_black_24dp).into(ivImage);
    }
}
