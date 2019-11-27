package edu.alsjava.courses.demoluis.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.alsjava.courses.demoluis.R;
import edu.alsjava.courses.demoluis.adapters.LazyDemoAdapter;
import edu.alsjava.courses.demoluis.model.Example;
import edu.alsjava.courses.demoluis.model.network.request.DemoRequest;
import edu.alsjava.courses.demoluis.tasks.DemoTask;

public class AppActivity extends AppCompatActivity {

    private final List<Example> data = new ArrayList<>();
    //    private DemoAdapter demoAdapter;
    private LazyDemoAdapter demoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

//        demoAdapter = new DemoAdapter(this, data);
        demoAdapter = new LazyDemoAdapter(this);

        AppCompatButton btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(view -> loadData());

        RecyclerView rvExample = findViewById(R.id.rvExample);
        rvExample.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvExample.setAdapter(demoAdapter);
        rvExample.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        new DemoTask(new DemoRequest(0), demoAdapter).execute();
    }

    private void loadData() {
        data.add(new Example("Uva", "https://www.herbazest.com/imgs/d/3/d/354166/uva.png"));
        data.add(new Example("Naranja", "https://naranjasamparo.net/81-large_default/naranjas-zumo-extra-.jpg"));
        demoAdapter.notifyDataSetChanged();
    }
}
