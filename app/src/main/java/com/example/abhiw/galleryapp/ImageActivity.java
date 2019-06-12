package com.example.abhiw.galleryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    public static Intent newIntent(Context context,Uri imageUri,String title){
        Intent intent = new Intent(context,ImageActivity.class);
        intent.putExtra("extra_image_uri",imageUri.toString());
        intent.putExtra("title_image",title);
        return intent;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView fullscreenimg = (ImageView) findViewById(R.id.imagefs);

        String image_uri = getIntent().getStringExtra("extra_image_uri");
        Uri uri = Uri.parse(image_uri);

        if(image_uri!=null && uri!=null &&fullscreenimg!=null)
        {
            Glide.with(this).load(uri).into(fullscreenimg);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
