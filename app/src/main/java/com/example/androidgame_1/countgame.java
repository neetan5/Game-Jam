package com.example.androidgame_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class countgame extends AppCompatActivity {
    ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9;
    List<String> flowers = new ArrayList<>();
    List<String> fruits = new ArrayList<>();
    List<String> animals = new ArrayList<>();
    ImageView celebration;
    Button b;
    TextToSpeech textToSpeech;
    int flag=1;
    int s;
    int total;
    EditText edt;
    int score=0;
   public static final String  EXTRA_str="";
    List<ImageView> imageReferences = new ArrayList<>();
    private int giveRandom(int range)
    {
        int rand=(int)(Math.random() * range);
        return rand;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countgame);
        getSupportActionBar().hide();
        celebration=findViewById(R.id.celebration);
        flowers.add("rose");
        flowers.add("sunflower");
        flowers.add("lotus");
        flowers.add("lily");
        flowers.add("hibiscus");
        flowers.add("daisy");
        flowers.add("marigold");
        flowers.add("tulip");
        flowers.add("bali");
        fruits.add("pineapple");
        fruits.add("apple");
        fruits.add("orange");
        fruits.add("litchi");
        fruits.add("cherry");
        fruits.add("mango");
        fruits.add("strawberry");
        fruits.add("grapes");
        fruits.add("banana");
        animals.add("dog");
        animals.add("cat");
        animals.add("giraffe");
        animals.add("cheetah");
        animals.add("panda");
        animals.add("cow");
        animals.add("lion");
        animals.add("zebra");
        animals.add("elephant");
        int index = this.giveRandom(9);
//        TextView mTextField = findViewById(R.id.textView11);
//        TextView time=findViewById(R.id.textView12);
        Intent intent5=getIntent();
        this.score=intent5.getIntExtra(countgame.EXTRA_str,0);
        this.total=intent5.getIntExtra("Total questions",0);
        new CountDownTimer(values.timer, 1000) {
            public void onTick(long millisUntilFinished) {
                //mTextField.setText("Remaining Time: ");
                values.timer=millisUntilFinished;
              //  time.setText( ""+millisUntilFinished / (1000*60));
            }
            public void onFinish() {
                if(flag==1) {
                    Registerfinal db = new Registerfinal(countgame.this);
                    String Score = String.valueOf(score);
                    Date date = new Date();
                    Timestamp timeStamp =  new Timestamp(date.getTime());
                    Log.d("",""+timeStamp);
                    String timestamp=timeStamp.toString();
                    Boolean answer = db.insertScore(MainActivity.ans,Score,timestamp,1,values.objectname,total);
                    if (answer) {
                        Toast.makeText(countgame.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent=new Intent(countgame.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();
        String imageName="";
        switch (values.objectname){
            case "Fruits": imageName = fruits.get(index);
            break;

            case "Flowers": imageName = flowers.get(index);
                break;

            case "Animals": imageName = animals.get(index);
                break;
        }
        i1=findViewById(R.id.iv_11);
        i2=findViewById(R.id.iv_12);
        i3=findViewById(R.id.iv_13);
        i4=findViewById(R.id.iv_21);
        i5=findViewById(R.id.iv_22);
        i6=findViewById(R.id.iv_23);
        i7=findViewById(R.id.iv_31);
        i8=findViewById(R.id.iv_32);
        i9=findViewById(R.id.iv_33);
        imageReferences.add(i1);
        imageReferences.add(i2);
        imageReferences.add(i3);
        imageReferences.add(i4);
        imageReferences.add(i5);
        imageReferences.add(i6);
        imageReferences.add(i7);
        imageReferences.add(i8);
        imageReferences.add(i9);
        for(int i=0;i<9;i++)
        {
            imageReferences.get(i).setImageResource(R.drawable.countimage);
        }
        Resources res = getResources();
        int resID = res.getIdentifier(imageName , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID );
        int count = this.giveRandom(9)+1;
        celebration.setVisibility(View.INVISIBLE);
        for(int i = 0;i< count; i++){
            imageReferences.get(i).setImageDrawable(drawable );
        }
        b=findViewById(R.id.button5);
        edt=findViewById(R.id.editTextTextPersonName4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String ans=edt.getText().toString();
               int x;
                total++;
               if(ans.equals("") || ans.equals("  "))
               {
                   x=0;
               }
               else
                    x=Integer.parseInt(ans);
               if(x==count)
               {
                   countgame.this.score++;
                   Glide.with(countgame.this).load(R.drawable.cutu).into(celebration);
                   celebration.setVisibility(View.VISIBLE);
                   Handler handler = new Handler();
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           celebration.setVisibility(view.GONE);
                       }
                   },3000);
                   textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                       @Override
                       public void onInit(int i) {
                           if(i!= TextToSpeech.ERROR){
                               textToSpeech.setLanguage(Locale.UK);
                               textToSpeech.speak("CORRECT",TextToSpeech.QUEUE_FLUSH,null);
                           }
                       }
                   });
                   Intent intent6=new Intent(countgame.this,countgame.class);
                   intent6.putExtra(EXTRA_str,countgame.this.score);
                   intent6.putExtra("Total questions",countgame.this.total);
                   flag=0;
                   startActivity(intent6);
                   finish();
               }
               else
               {
                   textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                       @Override
                       public void onInit(int i) {
                           if(i!= TextToSpeech.ERROR){
                               textToSpeech.setLanguage(Locale.UK);
                               textToSpeech.speak("TRy AGAIN",TextToSpeech.QUEUE_FLUSH,null);
                           }
                       }
                   });
                   /*Intent intent6=new Intent(countgame.this,countgame.class);
                   intent6.putExtra(EXTRA_str,countgame.this.score);
                   intent6.putExtra("Total questions",countgame.this.total);
                   flag=0;
                   startActivity(intent6);*/
                   edt.setText("");
               }
            }
        });
    }
}