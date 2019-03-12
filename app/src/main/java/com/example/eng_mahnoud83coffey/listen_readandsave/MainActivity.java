package com.example.eng_mahnoud83coffey.listen_readandsave;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Intent intentGo;
    private static final int MY_PERMISSIONS_REQUEST_Multiple = 1;
    //////////////////////////////////
    GridView simpleGrid;

    int logos[] = {R.drawable.learn_easily, R.drawable.learn_easily, R.drawable.learn_easily, R.drawable.learn_easily,
            R.drawable.learn_easily, R.drawable.learn_easily, R.drawable.learn_easily, R.drawable.learn_easily,
            R.drawable.learn_easily, R.drawable.learn_easily,
            R.drawable.learn_easily, R.drawable.learn_easily
    };
    String namelogo[] = {"الكتابه والابجديه", "عبارات المناسبات", "الاعداد وكتابتها", "اجزاء الجسم", "المتشابهه والانترنت", "الكلمات الشائعه"
            , "الرياضه والطبيعه", "الدول والسياحه", "الوقت والاطعمه", "التعليم والوظائف", "الطبى والطيور..."
            , "التجارى والملابس"};

    private StartAppAd startAppAd = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "202852472", true);
         setContentView(R.layout.activity_main);

        StartAppAd.showSplash(this,savedInstanceState);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();




        ////////////////////////////
        simpleGrid = (GridView) findViewById(R.id.simpleGridView); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), logos, namelogo);
        simpleGrid.setAdapter(customAdapter);
        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity

                switch (position) {
                    case 0:
                        intentGo = new Intent(MainActivity.this, Activity_Writing.class);
                        //ntent.putExtra("image", logos[position]); // put image data in Intent
                        startActivity(intentGo); // start Intent
                        break;
                    case 1:
                        intentGo = new Intent(MainActivity.this, Activity_Greetings.class);
                        //ntent.putExtra("image", logos[position]); // put image data in Intent
                        startActivity(intentGo); // start Intent
                        break;
                    case 2:
                        intentGo = new Intent(MainActivity.this, Activity_Numbers.class);
                        startActivity(intentGo);
                        break;
                    case 3:
                        intentGo = new Intent(MainActivity.this, Activity_Parts_the_body.class);
                        startActivity(intentGo);
                        break;
                    case 4:
                        intentGo = new Intent(MainActivity.this, Activity_Internet.class);
                        startActivity(intentGo);
                        break;
                    case 5:
                        Intent i5 = new Intent(MainActivity.this, Activity_PubularWards.class);
                        startActivity(i5);
                        break;
                    case 6:
                        intentGo = new Intent(MainActivity.this, Activity_Natural_suports.class);
                        startActivity(intentGo);
                        break;
                    case 7:
                        intentGo = new Intent(MainActivity.this, Activity_Country.class);
                        startActivity(intentGo);
                        break;
                    case 8:
                        //
                        intentGo = new Intent(MainActivity.this, Activity_Drinks.class);
                        startActivity(intentGo);
                        break;
                    case 9:
                        intentGo = new Intent(MainActivity.this, Activity_Education.class);
                        startActivity(intentGo);
                        break;
                    case 10:
                        intentGo = new Intent(MainActivity.this, Activity_Animals.class);
                        startActivity(intentGo);
                        break;

                    case 11:
                        intentGo = new Intent(MainActivity.this, Activity_Clothes.class);
                        startActivity(intentGo);
                        break;
                }


            }


        });

/////////////////////////
        Animation ss = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce_interpolator);
        simpleGrid.setAnimation(ss);
        //simpleGrid.setBackgroundResource(R.drawable.custom_bg);
    }
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {

                    case R.id.btn_Speach:
                        Intent i = new Intent(MainActivity.this, Read_lesition.class);
                        startActivity(i);

                        drawerLayout.closeDrawers();
                        break;


                    case R.id.logout:
                        finish();

                }


                return true;
            }
        });

/// //////////////////////////////////////////////////////////////////
        View header = navigationView.getHeaderView(0);

        TextView tv_email = (TextView) header.findViewById(R.id.tv_email);
        tv_email.setText("Practice makes perfect\nالممارسة تقود إلى الإتقان");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater myMenu = getMenuInflater();
        myMenu.inflate(R.menu.chosee_speed_speach, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        switch (id) {

            case R.id.slow:
                AllRecyclerviewAdabter.setSpeed(0.1f);
                break;

            case R.id.very_slow:
                AllRecyclerviewAdabter.setSpeed(0.5f);
                break;

            case R.id.defaulet:
                AllRecyclerviewAdabter.setSpeed(1.0f);
                break;
            case R.id.fast:
                AllRecyclerviewAdabter.setSpeed(1.5f);
                break;

            case R.id.very_fast:
                AllRecyclerviewAdabter.setSpeed(2.0f);
                break;
        }


        return super.onOptionsItemSelected(item);
    }



}