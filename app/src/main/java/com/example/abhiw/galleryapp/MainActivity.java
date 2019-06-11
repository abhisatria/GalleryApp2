package com.example.abhiw.galleryapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private boolean checkBasic;

    private final static int READ_EXTERNAL_STORAGE_PERMISSION_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBasic=true;
        recyclerView = (RecyclerView) findViewById(R.id.gallery);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.basic:
                showBasicList();
                checkBasic=true;
                return true;
            case R.id.advance:
                showAdvancedList();
                checkBasic=false;

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showAdvancedList() {
        ArrayList<Cell> cells = prepareData("Advance");
        MyAdapter adapter = new MyAdapter(getApplicationContext(),cells);
        recyclerView.setAdapter(adapter);
        setActionBarTitle("Advance");
    }

    private void showBasicList() {
        ArrayList<Cell> cells = prepareData("Basic");
        MyAdapter adapter = new MyAdapter(getApplicationContext(),cells);
        recyclerView.setAdapter(adapter);
        setActionBarTitle("Basic");
    }

    public void setActionBarTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkReadExternalStoragePermission();
        if(checkBasic) showBasicList();
        else showAdvancedList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    private ArrayList<Cell> prepareData(String path) {
        ArrayList<Cell> theimage = new ArrayList<>();
        theimage.clear();

        File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/"+path);

        if(downloadFolder.exists())
        {
            File files[] = downloadFolder.listFiles();

            for (File file : files)
            {
                Cell cell = new Cell();
                cell.setTitle(file.getName());
                cell.setUri(Uri.fromFile(file));
                theimage.add(cell);
            }
        }
//        for (int i=0;i<image_titles.length;i++)
//        {
//            Cell cell =  new Cell();
//            cell.setTitle(image_titles[i]);
//            cell.setImg(image_ids[i]);
//            theimage.add(cell);
//        }
        return theimage;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case READ_EXTERNAL_STORAGE_PERMISSION_RESULT:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //call cursor loader
                    Toast.makeText(this,"Now have acces to view thumbs",Toast.LENGTH_SHORT).show();
                }
                    break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void checkReadExternalStoragePermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                //start cursor loader
            }
            else{
                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Toast.makeText(this,"Apps needs to view thumbnails",Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},READ_EXTERNAL_STORAGE_PERMISSION_RESULT);
            }
        }else{
            //start cursor loader
        }
    }
}











