package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class question_list extends AppCompatActivity {

    RecyclerView recyclerView;
    QuestionAdapter adapter;
    List<Question> questionList=new ArrayList<>();
    List<Question> questions;
    ProgressBar progressBar;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        floatingActionButton = findViewById(R.id.fab);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        final Bundle b = getIntent().getExtras();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(question_list.this, CreateActivity.class);
                Bundle bd = new Bundle();
                bd.putString("flag", "question");
                bd.putInt("topicId",(Integer) b.get("id"));
                intent.putExtras(bd);
                startActivity(intent);
            }
        });

        Call<List<Question>> call = api.getQuestions((Integer) b.get("id"));
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {

                questions = response.body();
                for(Question s: questions) {
                    questionList.add(
                            new Question(
                                    s.getTitle(),
                                    s.getId(),
                                    s.getTopic()
                            )
                    );
                }
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(question_list.this));


                adapter = new QuestionAdapter(question_list.this,questionList);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}

