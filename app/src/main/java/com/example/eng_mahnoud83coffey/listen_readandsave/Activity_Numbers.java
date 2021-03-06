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
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Activity_Numbers extends AppCompatActivity {




private InputStream is;
private BufferedReader reader;
private String result;
////////////////
private String[] mStringArrayEng;
private String[] mStringArrayAra;
////////////////
    WebView wb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__numbers);





        wb=(WebView)findViewById(R.id.webview_header_text);
        wb.loadUrl("file:///android_asset/number.html");
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerviewNumbers);
        List<ModelTextRecyclerview>all_text=new ArrayList<>();




////////////////////Array WordEng/////////////
        try {
            result="";
            ArrayList<String>  mStringList= new ArrayList<String>();

                    is = this.getAssets().open("Number.txt"); // الوصول الى الملف النصي
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

            is = this.getAssets().open("الاعداد.txt"); // الوصول الى الملف النصي
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
                  AllRecyclerviewAdabter adabter=new AllRecyclerviewAdabter(all_text,Activity_Numbers.this);
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
                Intent intent=new Intent(Activity_Numbers.this,MainActivity.class);
                 startActivity(intent);
                 break;
        }


        return super.onOptionsItemSelected(item);
     }






}
