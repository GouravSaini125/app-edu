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

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {

    private Context mCtx;
    private List<Module> moduleList;
    View view;

    public ModuleAdapter(Context mCtx, List<Module> moduleList) {
        this.mCtx = mCtx;
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
//        if(!(mCtx instanceof question_list)) {
            view = inflater.inflate(R.layout.list_layout, null);
//        }
//        else {
//            view = inflater.inflate(R.layout.activity_question_list, null);
//        }
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ModuleViewHolder holder, int position) {
        final Module module = moduleList.get(position);

        holder.textView.setText(module.getTitle());


//        if(mCtx instanceof question_list) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api api = retrofit.create(Api.class);
//        Call<List<Choice>> call = api.getChoices(module.getId());
//        call.enqueue(new Callback<List<Choice>>() {
//            @Override
//            public void onResponse(Call<List<Choice>> call, Response<List<Choice>> response) {
//
//                for (Choice c : response.body()) {
//                    RadioButton rbn = new RadioButton(mCtx);
//                    int id = View.generateViewId();
//                    rbn.setId(id);
//                    rbn.setText(c.getA());
//                    holder.rgp.addView(rbn);
//                    rbn = new RadioButton(mCtx);
//                    id = View.generateViewId();
//                    rbn.setId(id);
//                    rbn.setText(c.getB());
//                    holder.rgp.addView(rbn);
//                    rbn = new RadioButton(mCtx);
//                    id = View.generateViewId();
//                    rbn.setId(id);
//                    rbn.setText(c.getC());
//                    holder.rgp.addView(rbn);
//                    rbn = new RadioButton(mCtx);
//                    id = View.generateViewId();
//                    rbn.setId(id);
//                    rbn.setText(c.getD());
//                    holder.rgp.addView(rbn);
//                    Log.d("asa",c.getAnswer());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Choice>> call, Throwable t) {
//                Toast.makeText(mCtx, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        holder.rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton rb = group.findViewById(checkedId);
////                if (null != rb && checkedId >= -1) {
//                    Toast.makeText(mCtx, ""+group.getId(), Toast.LENGTH_SHORT).show();
////                }
//            }
//        });
//
//    }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx,module.getTitle(),Toast.LENGTH_SHORT).show();
//                if(mCtx instanceof module_list) {
                    Intent intent = new Intent(mCtx, topic_list.class);
                    Bundle b = new Bundle();
                    b.putInt("id", module.getId());
                    intent.putExtras(b);
                    mCtx.startActivity(intent);
//                }
//                else if(mCtx instanceof topic_list) {
//                    Intent intent = new Intent(mCtx, question_list.class);
//                    Bundle b = new Bundle();
//                    b.putInt("id", module.getId());
//                    intent.putExtras(b);
//                    mCtx.startActivity(intent);
//                }
//                else{
//                    Intent intent = new Intent(mCtx, question_list.class);
//                    Bundle b = new Bundle();
//                    b.putInt("id", module.getId());
//                    intent.putExtras(b);
//                    mCtx.startActivity(intent);
//                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RelativeLayout relativeLayout;
        RadioGroup rgp;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);

            if(mCtx instanceof question_list) {

                rgp = itemView.findViewById(R.id.radio_group);

            }
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

}
