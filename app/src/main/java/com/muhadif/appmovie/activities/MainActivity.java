package com.muhadif.appmovie.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.muhadif.appmovie.R;
import com.muhadif.appmovie.data.adapters.MainAdapter;
import com.muhadif.appmovie.data.models.Movie;
import com.muhadif.appmovie.presenters.MainPresenter;
import com.muhadif.appmovie.views.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private Button btnSearch;
    private TextInputEditText textInputSearch;
    private MainPresenter presenter;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private List<Movie> movies = new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Search Movie");

        btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        textInputSearch = findViewById(R.id.input_search);

        refreshLayout = findViewById(R.id.swipeRefreshLayout);

        recyclerView = findViewById(R.id.recycler_container);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mainAdapter = new MainAdapter(getApplicationContext(), movies);
        recyclerView.setAdapter(mainAdapter);
        presenter = new MainPresenter(this);
        presenter.getData();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData();
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (textInputSearch.getText() != null) {
            switch (v.getId()) {
                case R.id.btn_search:
                    presenter.searchMovie(textInputSearch.getText() + "");
                    break;
            }
        } else {
            presenter.getData();
        }
    }

    @Override
    public void showLoad() {
        refreshLayout.setRefreshing(true);
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishLoad() {
        refreshLayout.setRefreshing(false);
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<Movie> data) {
        movies.clear();
        movies.addAll(data);
        mainAdapter.notifyDataSetChanged();
    }
}
