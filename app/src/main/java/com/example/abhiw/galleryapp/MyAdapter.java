package com.example.abhiw.galleryapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Cell> galleryList;
    private Context context;

    public MyAdapter(Context context,ArrayList<Cell> galleryList){
        this.galleryList = galleryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, final int i) {

        final Cell cell = galleryList.get(i);
        final Uri image_uri = cell.getUri();

        Glide.with(context).load(image_uri).centerInside().into(viewHolder.img);
        viewHolder.title.setText(cell.getTitle());

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / 2;

        ViewGroup.LayoutParams layoutParams = viewHolder.img.getLayoutParams();
        layoutParams.width = (int)screenWidthDp-16;
        layoutParams.height = (int)screenWidthDp-16;
        viewHolder.img.setLayoutParams(layoutParams);


        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,galleryList.get(i).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = ImageActivity.newIntent(context,image_uri,cell.getTitle());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView img;
        public ViewHolder(View view){
            super(view);

            title= (TextView) view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }
}
