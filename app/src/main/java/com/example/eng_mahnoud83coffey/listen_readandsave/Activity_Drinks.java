package com.example.eng_mahnoud83coffey.listen_readandsave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Activity_Drinks extends AppCompatActivity {


    private InputStream is;
    private BufferedReader reader;
    private String result;
    ////////////////
    private String[] mStringArrayEng;
    private String[] mStringArrayAra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__drinks);
        StartAppSDK.init(this, "202852472", true);



        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview_Drinks);
        List<ModelTextRecyclerview> all_text=new ArrayList<>();

        try {
            result="";
            ArrayList<String>  mStringList= new ArrayList<String>();

            is = this.getAssets().open("direction_foods.txt"); // الوصول الى الملف النصي
            reader = new BufferedReader(new InputStreamReader(is, "UTF8")); // قارئ بفرد وهو يستخدم عادة لقراءة الملفات الضخمة, وسوف يقرأ من الملف النصي

            if (is!=null) { // اذا تم ايجاد الملف
                while ((result = reader.readLine()) != null) { // قم بقراءة البفرد المدخل سطر سطر اي بشكل سليم ووضعه داخل سترنج str
                    mStringList.add(result);
                }
                mStringArrayEng = new String[mStringList.size()];
                mStringArrayEng = mStringList.toArray(mStringArrayEng);
            }

            is.close(); // اغلاق الادخال
        } catch (IOException e) {
        }

////////////////////////Array Arabic////////////////////
        try {
            result="";
            ArrayList<String>  mStringList= new ArrayList<String>();

            is = this.getAssets().open("الاطعمه والمشروبات.txt"); // الوصول الى الملف النصي
            reader = new BufferedReader(new InputStreamReader(is, "UTF8")); // قارئ بفرد وهو يستخدم عادة لقراءة الملفات الضخمة, وسوف يقرأ من الملف النصي

            if (is!=null) { // اذا تم ايجاد الملف
                while ((result = reader.readLine()) != null) { // قم بقراءة البفرد المدخل سطر سطر اي بشكل سليم ووضعه داخل سترنج str
                    mStringList.add(result);
                }
                mStringArrayAra = new String[mStringList.size()];
                mStringArrayAra = mStringList.toArray(mStringArrayAra);
            }

            is.close(); // اغلاق الادخال
        } catch (IOException e) {
        }


        for (int i=0;i<mStringArrayEng.length;i++){
            ModelTextRecyclerview m=new ModelTextRecyclerview(mStringArrayEng[i],mStringArrayAra[i]);
            all_text.add(m);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AllRecyclerviewAdabter adabter=new AllRecyclerviewAdabter(all_text,Activity_Drinks.this);
        recyclerView.setAdapter(adabter);




    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater myMenu=getMenuInflater();
        myMenu.inflate(R.menu.back_mainactivity,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id=item.getItemId();
        switch(id){

            case R.id.Back_MainActivity:
                Intent intent=new Intent(Activity_Drinks.this,MainActivity.class);
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }









}
