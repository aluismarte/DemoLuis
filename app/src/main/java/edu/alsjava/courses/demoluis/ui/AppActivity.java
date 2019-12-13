package edu.alsjava.courses.demoluis.ui;

import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.linecorp.apng.ApngDrawable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import edu.alsjava.courses.demoluis.R;
import edu.alsjava.courses.demoluis.adapters.LazyDemoAdapter;
import edu.alsjava.courses.demoluis.model.Example;
import edu.alsjava.courses.demoluis.model.Operation;
import edu.alsjava.courses.demoluis.model.network.request.DemoRequest;
import edu.alsjava.courses.demoluis.tasks.DemoTask;
import edu.alsjava.courses.demoluis.tasks.LoadAPNG2;

public class AppActivity extends AppCompatActivity implements Operation {

    private final List<Example> data = new ArrayList<>();
    //    private DemoAdapter demoAdapter;
    private LazyDemoAdapter demoAdapter;

    private LinearLayoutCompat llLoading;
    private AppCompatTextView tvLoading;
    private AppCompatImageView ivLoading;

    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

//        demoAdapter = new DemoAdapter(this, data);
        demoAdapter = new LazyDemoAdapter(this);

        llLoading = findViewById(R.id.llLoading);
        tvLoading = findViewById(R.id.tvLoading);
        ivLoading = findViewById(R.id.ivLoading);

        new LoadAPNG2(this).execute();

        AppCompatButton btnGlide = findViewById(R.id.btnGlide);
        btnGlide.setOnClickListener(view -> {
            Intent intent = new Intent(this, GlideActivity.class);
            startActivity(intent);
        });

        AppCompatButton btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(view -> {
            Intent intent = new Intent(this, AppActivity.class);
            intent.putExtra("data", "Hola, soy la alarma");

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 15000, intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 0, 20000, pendingIntent);
            }
        });


        AppCompatButton btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(view -> {
            animator.start();
            loadData();
        });

        Path path = new Path();
        path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
        animator = ObjectAnimator.ofFloat(btnLoad, View.X, View.Y, path);
        animator.setDuration(2000);

        RecyclerView rvExample = findViewById(R.id.rvExample);
        rvExample.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvExample.setAdapter(demoAdapter);
        rvExample.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        new DemoTask(this, new DemoRequest(0), demoAdapter).execute();
    }

    private void loadData() {
        data.add(new Example("Uva", "https://www.herbazest.com/imgs/d/3/d/354166/uva.png"));
        data.add(new Example("Naranja", "https://naranjasamparo.net/81-large_default/naranjas-zumo-extra-.jpg"));
        demoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 15000:
                if (data != null) {
                    String message = data.getStringExtra("data");
                    if (message == null) {
                        message = "";
                    }
                    Toast.makeText(this, String.format("Tengo: %s", message), Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void operation(boolean result) {
        if (result) {
            llLoading.setVisibility(View.GONE);
        } else {
            tvLoading.setText(R.string.error_loading);
        }
    }

    public AppCompatImageView getIvLoading() {
        return ivLoading;
    }

    // Ejemplo dentro sin warning, solo demostrativo para en caso de que no se requiera crear una clase y archivo independiente.
    public static class LoadAPNG extends AsyncTask<Void, Void, ApngDrawable> {

        private WeakReference<AppActivity> appActivityWeakReference;

        LoadAPNG(AppActivity appActivity) {
            appActivityWeakReference = new WeakReference<>(appActivity);

        }

        @Override
        protected void onPostExecute(ApngDrawable apngDrawable) {
            AppActivity appActivity = appActivityWeakReference.get();
            if (appActivity != null && !appActivity.isFinishing()) {
                if (apngDrawable != null) {
                    Glide.with(appActivity).load(apngDrawable).into(appActivity.getIvLoading());
                }
            }
        }

        @Override
        protected ApngDrawable doInBackground(Void... voids) {
            try {
                AppActivity appActivity = appActivityWeakReference.get();
                if (appActivity != null && !appActivity.isFinishing()) {
                    return ApngDrawable.Companion.decode(appActivity.getResources(), R.raw.cat, null, null);
                }
            } catch (Exception ignored) {
            }
            return null;
        }
    }
}
