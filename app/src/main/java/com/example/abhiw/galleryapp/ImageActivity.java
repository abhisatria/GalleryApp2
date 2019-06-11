package com.example.abhiw.galleryapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
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
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Toolbar mToolbar = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            mToolbar.setTitle(getIntent().getStringExtra("title_image"));
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
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
