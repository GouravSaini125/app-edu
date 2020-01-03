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

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private Context mCtx;
    private List<Topic> topicList;
    View view;

    public TopicAdapter(Context mCtx, List<Topic> topicList) {
        this.mCtx = mCtx;
        this.topicList = topicList;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
            view = inflater.inflate(R.layout.list_layout, null);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopicViewHolder holder, int position) {
        final Topic topic = topicList.get(position);

        holder.textView.setText(topic.getTitle());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx,topic.getTitle(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mCtx, question_list.class);
                    Bundle b = new Bundle();
                    b.putInt("id", topic.getId());
                    intent.putExtras(b);
                    mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    class TopicViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RelativeLayout relativeLayout;
        RadioGroup rgp;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);

            if(mCtx instanceof question_list) {

                rgp = itemView.findViewById(R.id.radio_group);

            }
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

}
