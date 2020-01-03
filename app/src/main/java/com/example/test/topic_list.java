package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class topic_list extends AppCompatActivity {

    RecyclerView recyclerView;
    TopicAdapter adapter;
    List<Topic> topicList=new ArrayList<>();
    List<Topic> topics;
    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api = retrofit.create(Api.class);
        final Bundle b = getIntent().getExtras();
        button = findViewById(R.id.notes);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(topic_list.this, NotesActivity.class);
                Bundle bd = new Bundle();
                bd.putInt("moduleId",(Integer) b.get("id"));
                intent.putExtras(bd);
                startActivity(intent);
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(topic_list.this, CreateActivity.class);
                Bundle bd = new Bundle();
                bd.putString("flag", "topic");
                bd.putInt("moduleId",(Integer) b.get("id"));
                intent.putExtras(bd);
                startActivity(intent);
            }
        });

        Call<List<Topic>> call = api.getTopics((Integer) b.get("id"));
        call.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {

                topics = response.body();
                for(Topic t: topics) {
                    topicList.add(
                            new Topic(
                                    t.getTitle(),
                                    t.getModule(),
                                    t.getId()
                            )
                    );
                }
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(topic_list.this));


                adapter = new TopicAdapter(topic_list.this,topicList);
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}

