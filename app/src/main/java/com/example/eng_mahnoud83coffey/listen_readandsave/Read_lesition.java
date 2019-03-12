package com.example.eng_mahnoud83coffey.listen_readandsave;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Read_lesition extends AppCompatActivity implements
        TextToSpeech.OnInitListener,AdapterView.OnItemSelectedListener {
    private TextToSpeech tts;
    private Button buttonSpeak;
    private Button btn_finish;
    private EditText editText;
    private Spinner speedSpinner,pitchSpinner;


    TextView textcopy;

    private static String speed="الافتراضي";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_lesition);

        StartAppSDK.init(this, "202852472", true);




        tts = new TextToSpeech(this, this);
        buttonSpeak = (Button) findViewById(R.id.button1);
        editText = (EditText) findViewById(R.id.editText1);
        speedSpinner = (Spinner) findViewById(R.id.spinner1);
        textcopy=(TextView) findViewById(R.id.textcopy);
         btn_finish=(Button)findViewById(R.id.finith_dialog);




//تحميل البيانات من الـ سبينر
        loadSpinnerData();
        speedSpinner.setOnItemSelectedListener(this);

//الضغط على زر تحدث
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setSpeed();
                speakOut();
            }

        });
    }


    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
// تستطيع من هنا تغيير اللغة UK
            int result = tts.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "للأسف لم يتم اعتماده");
            } else {
                buttonSpeak.setEnabled(true);
                speakOut();
            }

        } else { Log.e("TTS", "Initilization فشل!");}

    }

    @Override
    public void onDestroy() {
// ايقاف تحويل الصوت
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    // قائمة السرعة مع سرعة كلاً منها
    private void setSpeed(){
        if(speed.equals("بطيء جداً")){
            tts.setSpeechRate(0.1f);
        }
        if(speed.equals("بطيء")){
            tts.setSpeechRate(0.5f);
        }
        if(speed.equals("الافتراضي")){
            tts.setSpeechRate(1.0f); // السرعة الافتراضية 1.0f
        }
        if(speed.equals("سريع")){
            tts.setSpeechRate(1.5f);
        }
        if(speed.equals("سريع جداً")){
            tts.setSpeechRate(2.0f);
        }

    }

    private void speakOut() {
        String text = editText.getText().toString();
        String text2=textcopy.getText().toString();

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void loadSpinnerData() {
        //البيانات داخل عنصر سرعة سبينر
        List<String> lables = new ArrayList<String>();
        lables.add("بطيء جداً");
        lables.add("بطيء");
        lables.add("الافتراضي");
        lables.add("سريع");
        lables.add("سريع جداً");

        // إنشاء محول لـ سبينر
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // ربط محول  adapter بـ سبينر
        speedSpinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // بعد اختيار عنصر من قائمة سبينر.. توست
        speed = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "القد اخترت:" + speed,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }



    //Listenir Button Finish
    public void btn_Finish(View view) {

        finish();
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
                Intent intent=new Intent(Read_lesition.this,MainActivity.class);
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }





}