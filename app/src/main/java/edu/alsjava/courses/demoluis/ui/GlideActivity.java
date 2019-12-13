package edu.alsjava.courses.demoluis.ui;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;

import edu.alsjava.courses.demoluis.R;
import edu.alsjava.courses.demoluis.domain.Author;
import edu.alsjava.courses.demoluis.model.Seasons;
import edu.alsjava.courses.demoluis.repository.AuthorRepository;
import edu.alsjava.courses.demoluis.utils.Constants;
import edu.alsjava.courses.demoluis.utils.lifecycle.LifeCycleCheckBluetooth;
import edu.alsjava.courses.demoluis.utils.lifecycle.LifeCycleDemo;

public class GlideActivity extends AppCompatActivity {

    private static final int NOTIFICATION_NETWORK = 65431;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

        getLifecycle().addObserver(new LifeCycleCheckBluetooth(this, getClass().getName()));
        getLifecycle().addObserver(new LifeCycleDemo(this, getClass().getName()));

        AppCompatImageView ivGif = findViewById(R.id.ivGif);

        Glide.with(this).asGif().load(R.raw.cat).into(ivGif);
        ivGif.setVisibility(View.VISIBLE);

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

                    // Channel ID is the group, multiple notification on the same ChannelID going to be together.
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(GlideActivity.this, "Network")
                            .setSmallIcon(R.drawable.ic_network_check_black_24dp)
                            .setContentTitle(getString(R.string.network_state))
                            .setContentText("Much longer text that cannot fit one line...")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    Intent intent = new Intent(GlideActivity.this, AppActivity.class); // App default is AppActivity, open when click on notification
                    intent.setAction("lalala.my.pretty.action"); // Action define on manifest
                    intent.putExtra("state", connected); // Data to process on broadcast

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(GlideActivity.this, 1002, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.addAction(R.drawable.ic_save_black_24dp, getString(R.string.ok), pendingIntent);

                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(GlideActivity.this);
                    notificationManagerCompat.notify(NOTIFICATION_NETWORK, builder.build());
                }

                @Override
                public void onLost(@NonNull Network network) {
                    super.onLost(network);
                }
            });
        }

        new InsertOnDB().execute();
    }

    private void printDat(@Seasons.Season int day) {
        Toast.makeText(this, "Day: " + day, Toast.LENGTH_SHORT).show();
    }

    public static class InsertOnDB extends AsyncTask<Void, Void, Void> {

        InsertOnDB() {
        }

        @Override
        protected void onPostExecute(Void data) {
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // No podemos hacer esto en el hilo principal, siempre debe ser un hilo, sino explota
            AuthorRepository authorRepository = Constants.get().demoLuisDB.getAuthorRepository();
            Author author = new Author();
            author.setName("Steven King");
            author.setDescription("King of Horror");
            authorRepository.insert(author);
            return null;
        }
    }
}
