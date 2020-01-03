package com.example.test;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
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
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.DOWNLOAD_SERVICE;
import static android.os.Environment.DIRECTORY_PICTURES;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private Context mCtx;
    private List<Notes> notesList;
    View view;

    public NotesAdapter(Context mCtx, List<Notes> notesList) {
        this.mCtx = mCtx;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        view = inflater.inflate(R.layout.list_layout, null);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, int position) {
        final Notes notes = notesList.get(position);

        holder.textView.setText(notes.getDoc());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(notes.getDoc());
            }
        });
    }

    private void downloadFile(String image_uri) {

        try {
            File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES), "myfolder");


            if (!imageStorageDir.exists()) {
//noinspection ResultOfMethodCallIgnored
                imageStorageDir.mkdirs();
            }

// default image extension
//            String imgExtension = ".jpg";
//            String image_uri = "http://www.zidsworld.com/wp-content/uploads/2018/06/cat_1530281469.jpg";
//
//            if (image_uri.contains(".gif"))
//                imgExtension = ".gif";
//            else if (image_uri.contains(".png"))
//                imgExtension = ".png";
//            else if (image_uri.contains(".3gp"))
//                imgExtension = ".3gp";
//
//            String date = DateFormat.getDateTimeInstance().format(new Date());
//            String file = "imgg" + "-image-" + date.replace(" ", "").replace(":", "").replace(".", "") + imgExtension;

            String file = image_uri.substring( image_uri.lastIndexOf('/')+1, image_uri.length() );

            DownloadManager dm = (DownloadManager) mCtx.getSystemService(DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(image_uri);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);

            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setDestinationInExternalPublicDir(DIRECTORY_PICTURES + File.separator, file)
                    .setTitle(file).setDescription("save")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            dm.enqueue(request);

            Toast.makeText(mCtx,"processing", Toast.LENGTH_LONG).show();


        } catch (IllegalStateException ex) {
            Toast.makeText(mCtx,"Storage Error", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        } catch (Exception ex) {
            // just in case, it should never be called anyway
            Toast.makeText(mCtx,"Unable to save image", Toast.LENGTH_LONG).show();
            ex.printStackTrace();


        }}


    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RelativeLayout relativeLayout;
        RadioGroup rgp;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

}
