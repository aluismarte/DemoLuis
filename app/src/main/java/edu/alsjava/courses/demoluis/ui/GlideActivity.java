package edu.alsjava.courses.demoluis.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

import edu.alsjava.courses.demoluis.R;
import edu.alsjava.courses.demoluis.domain.Author;
import edu.alsjava.courses.demoluis.model.Seasons;
import edu.alsjava.courses.demoluis.repository.AuthorRepository;
import edu.alsjava.courses.demoluis.utils.Constants;
import edu.alsjava.courses.demoluis.utils.lifecycle.LifeCycleCheckBluetooth;
import edu.alsjava.courses.demoluis.utils.lifecycle.LifeCycleDemo;

public class GlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        getLifecycle().addObserver(new LifeCycleCheckBluetooth(this, getClass().getName()));
        getLifecycle().addObserver(new LifeCycleDemo(this, getClass().getName()));

        AppCompatImageView ivGif = findViewById(R.id.ivGif);

        Glide.with(this).asGif().load(R.raw.cat).into(ivGif);
        ivGif.setVisibility(View.VISIBLE);

        AuthorRepository authorRepository = Constants.get().demoLuisDB.getAuthorRepository();
        Author author = new Author();
        author.setName("Steven King");
        author.setDescription("King of Horror");
        authorRepository.insert(author);

        int dayOfWeek = 1; // We dont know!
        Toast.makeText(this, "Day: " + dayOfWeek, Toast.LENGTH_SHORT).show();

//        int dayOfWeek2 = Seasons.SPRING; // Good!

        printDat(Seasons.SUMMER);
        printDat(Seasons.SPRING);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            connectivityManager.registerNetworkCallback(builder.build(), new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(@NonNull Network network) {
                    super.onAvailable(network);
                    boolean connected;
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                    if (networkCapabilities != null) {
                        connected = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
                    } else {
                        connected = false;
                    }
                    Toast.makeText(GlideActivity.this, "Network?: " + connected, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onLost(@NonNull Network network) {
                    super.onLost(network);
                }
            });
        }
    }

    private void printDat(@Seasons.Season int day) {
        Toast.makeText(this, "Day: " + day, Toast.LENGTH_SHORT).show();

    }
}
