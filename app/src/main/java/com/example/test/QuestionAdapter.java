package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private Context mCtx;
    private List<Question> questionList;
    View view;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Api api = retrofit.create(Api.class);

    public QuestionAdapter(Context mCtx, List<Question> questionList) {
        this.mCtx = mCtx;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
            view = inflater.inflate(R.layout.activity_question_list, null);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionViewHolder holder, int position) {
        final Question question = questionList.get(position);

        holder.textView.setText(question.getTitle());
            Call<List<Choice>> call = api.getChoices(question.getId());

            call.enqueue(new Callback<List<Choice>>() {
                @Override
                public void onResponse(Call<List<Choice>> call, Response<List<Choice>> response) {

                    for (Choice c : response.body()) {
                        RadioButton rbn = new RadioButton(mCtx);
                        int id = View.generateViewId();
                        rbn.setId(id);
                        rbn.setText(c.getA());
                        holder.rgp.addView(rbn);
                        rbn = new RadioButton(mCtx);
                        id = View.generateViewId();
                        rbn.setId(id);
                        rbn.setText(c.getB());
                        holder.rgp.addView(rbn);
                        rbn = new RadioButton(mCtx);
                        id = View.generateViewId();
                        rbn.setId(id);
                        rbn.setText(c.getC());
                        holder.rgp.addView(rbn);
                        rbn = new RadioButton(mCtx);
                        id = View.generateViewId();
                        rbn.setId(id);
                        rbn.setText(c.getD());
                        holder.rgp.addView(rbn);
                        Log.d("asa",c.getAnswer());
                    }

                }

                @Override
                public void onFailure(Call<List<Choice>> call, Throwable t) {
                    Toast.makeText(mCtx, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            holder.rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb = group.findViewById(checkedId);
//                if (null != rb && checkedId >= -1) {
                    Toast.makeText(mCtx, ""+group.getId(), Toast.LENGTH_SHORT).show();
//                }
                }
            });

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RelativeLayout relativeLayout;
        RadioGroup rgp;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);

            if(mCtx instanceof question_list) {

                rgp = itemView.findViewById(R.id.radio_group);

            }
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }

    }

}
