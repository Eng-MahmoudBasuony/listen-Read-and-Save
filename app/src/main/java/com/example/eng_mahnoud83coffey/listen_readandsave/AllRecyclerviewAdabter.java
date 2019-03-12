package com.example.eng_mahnoud83coffey.listen_readandsave;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by eng-mahnoud on 27/02/18.
 */

public class AllRecyclerviewAdabter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
                                                                     {

    String text_Language="Hello";
    private TextToSpeech tts;
    List<ModelTextRecyclerview> Alltext;
    LayoutInflater inflater;
    Context context;
    private static float speed=1.0f;

public AllRecyclerviewAdabter(List<ModelTextRecyclerview> Alltext,Context context) {

    this.Alltext = Alltext;
    this.context = context;
    inflater = (LayoutInflater.from(context));


}

public static void setSpeed(float s){

    speed=s;
}


 @Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


//Row Defaulet
return new TextMoveHolder(inflater.inflate(R.layout.row_recyclerview_defaulet, parent, false));




}

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {





        TextMoveHolder TM_holder   =(TextMoveHolder)holder;
        ModelTextRecyclerview m_Text=Alltext.get(position);


        TM_holder.Eng_Text.setText(m_Text.Eng_Text);
        TM_holder.Ara_Text.setText(m_Text.Ara_Text);





    }

    @Override
    public int getItemCount() {
        return Alltext.size();
    }

 @Override
 public int getItemViewType(int position) {



         return super.getItemViewType(position);



}

///inerr class View Holder///
public class TextMoveHolder extends RecyclerView.ViewHolder {

  TextView Eng_Text;
  TextView Ara_Text;
  CardView cardView;
  LinearLayout layout;

  public TextMoveHolder(View view){
      super(view);

       cardView=(CardView)view.findViewById(R.id.cardView);
       Eng_Text=(TextView)view.findViewById(R.id.text_Eng);
       Ara_Text=(TextView)view.findViewById(R.id.text_Ara);
       layout=(LinearLayout)view.findViewById(R.id.layoutcardview);

      cardView.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("ResourceAsColor")
           @Override
           public void onClick(View view) {

               final ModelTextRecyclerview text=Alltext.get(getAdapterPosition());

               text_Language =text.Eng_Text;
               text_Language = text_Language.replace("=", "or");
               text_Language = text_Language.replace("-", "");

               Toast.makeText(context,text_Language, Toast.LENGTH_SHORT).show();

               tts=new TextToSpeech(context, new TextToSpeech.OnInitListener() {

                   @Override
                   public void onInit(int status) {
                       // TODO Auto-generated method stub
                       if(status ==TextToSpeech.SUCCESS){
                           tts.setSpeechRate(speed);
                           tts.setLanguage(Locale.UK);
                           playNextChunk(text_Language);
                       }
                   }
               });

           }
       });

  }



/////////////////////////Method Speach////////////////////////////////////
        private void playNextChunk(String text) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ttsGreater21(text);
            } else {
                ttsUnder20(text);
            }}
        @SuppressWarnings("deprecation")
        private void ttsUnder20(String text) {
            HashMap<String, String> map = new HashMap<>();
            map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
        }
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private void ttsGreater21(String text) {
            String utteranceId = this.hashCode() + "";
            Bundle params = new Bundle();
            params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, params, utteranceId);
        }

////////////////////////////////////////////////////////////////////////////////


}


}
