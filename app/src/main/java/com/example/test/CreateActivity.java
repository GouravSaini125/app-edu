package com.example.test;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateActivity extends AppCompatActivity {

    EditText editText;
    String answer, choiceA, choiceB, choiceC, choiceD;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        button = findViewById(R.id.newButton);
        textView = findViewById(R.id.newCreate);
        editText = findViewById(R.id.newTitle);
        choiceA = choiceB = choiceC = choiceD = "ss";
        answer = "a";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew(editText.getText().toString());
            }
        });

    }

    private void addNew(final String title) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api = retrofit.create(Api.class);

        final Bundle b = getIntent().getExtras();

        if(b.get("flag").toString().equals("subject")) {
            Subject subject = new Subject(title, 1, null);
            Call<Subject> call = api.postSubject(subject);

            call.enqueue(new Callback<Subject>() {
                @Override
                public void onResponse(Call<Subject> call, Response<Subject> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Subject" + response.body().getTitle(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Subject> call, Throwable t) {
                    Log.d("asa", t.getMessage());
                }
            });
        }

        else if(b.get("flag").toString().equals("module")){

            Module module = new Module(title, (Integer) b.get("subjectId"), null);
            Call<Module> call = api.postModule(module);
            call.enqueue(new Callback<Module>() {
                @Override
                public void onResponse(Call<Module> call, Response<Module> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Module" + response.body().getTitle(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Module> call, Throwable t) {
                    Log.d("asa", t.getMessage());
                }
            });
        }

        else if (b.get("flag").toString().equals("topic")){
            Topic topic = new Topic(title, (Integer) b.get("moduleId"), null);
            Call<Topic> call = api.postTopic(topic);
            call.enqueue(new Callback<Topic>() {
                @Override
                public void onResponse(Call<Topic> call, Response<Topic> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Topic" + response.body().getTitle(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Topic> call, Throwable t) {
                    Log.d("asa", t.getMessage());
                }
            });
        }

        else if (b.get("flag").toString().equals("question")){
            final Question question = new Question(title,null,(Integer) b.get("topicId"));
            Call<Question> call = api.postQuestion(question);
            call.enqueue(new Callback<Question>() {
                @Override
                public void onResponse(Call<Question> call, Response<Question> response) {
                    if (response.isSuccessful()) {


                        Choice choice = new Choice(choiceA, choiceB, choiceC, choiceD, answer, response.body().getId(),null);
                        Call<Choice> callChoice = api.postChoice(choice);
                        callChoice.enqueue(new Callback<Choice>() {
                            @Override
                            public void onResponse(Call<Choice> callChoice, Response<Choice> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Que " + response.body().getQuestion(),Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Choice> callChoice, Throwable t) {
                                Log.d("asa", t.getMessage());
                            }
                        });


                    }
                }

                @Override
                public void onFailure(Call<Question> call, Throwable t) {
                    Log.d("asa", t.getMessage());
                }
            });
        }

    }
}
