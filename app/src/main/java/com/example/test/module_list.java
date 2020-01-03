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

public class module_list extends AppCompatActivity {

    RecyclerView recyclerView;
    ModuleAdapter adapter;
    List<Module> moduleList=new ArrayList<>();
    List<Module> modules;
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
                Intent intent = new Intent(module_list.this, CreateActivity.class);
                Bundle bd = new Bundle();
                bd.putString("flag", "module");
                bd.putInt("subjectId",(Integer) b.get("id"));
                intent.putExtras(bd);
                startActivity(intent);
            }
        });

        Call<List<Module>> call = api.getModules((Integer) b.get("id"));
        call.enqueue(new Callback<List<Module>>() {
            @Override
            public void onResponse(Call<List<Module>> call, Response<List<Module>> response) {

                modules = response.body();
                for(Module s: modules) {
                    moduleList.add(
                            new Module(
                                    s.getTitle(),
                                    s.getSubject(),
                                    s.getId()
                            )
                    );
                }
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(module_list.this));


                adapter = new ModuleAdapter(module_list.this,moduleList);
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Module>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}

