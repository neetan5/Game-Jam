package com.example.androidgame_1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TileGame extends AppCompatActivity {
    ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9,ques,celebration;
    int ans;
    int index;
    int count=0;
    Button b1;
    int flag=1;
    int score=0;
    int total=0;
    TextToSpeech textToSpeech;
    List<String> flowers = new ArrayList<>(
            Arrays.asList("rose","sunflower","lotus","daisy","hibiscus","marigold","lily","tulip","bali")
    );
    List<String> fruits = new ArrayList<>(
            Arrays.asList("pineapple","apple","orange","banana","cherry","grapes","litchi","mango","strawberry")
    );
    List<String> animals = new ArrayList<>(
            Arrays.asList("dog","cat","cow","lion","cheetah","panda","zebra","elephant","giraffe")
    );
    List<ImageView> imageReferences = new ArrayList<>();
    List<String> ansarray = new ArrayList<>();
    Button flip;
    List<String> objectNameArray = new ArrayList<>();
    private int giveRandom(int range)
    {
        int rand=(int)(Math.random() * range);
        return rand;
    }
    private void speak(String ans)
    {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!= TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                    textToSpeech.speak(ans,TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });
    }
    private int doStuff(int position,int index) {
       if(position==index) {
          // Toast.makeText(this, "Correct!!", Toast.LENGTH_SHORT).show();
           Resources res = getResources();
           int resID ;
           Drawable drawable;
           resID = res.getIdentifier(ansarray.get(index) , "drawable", getPackageName());
           drawable = res.getDrawable(resID );
           imageReferences.get(index).setImageDrawable(drawable);
           score++;
           count++;
           /*Glide.with(TileGame.this).load(R.drawable.cutu).into(celebration);
           Handler handler = new Handler();
           celebration.setVisibility(View.VISIBLE);
           handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   celebration.setVisibility(celebration.GONE);
               }
           },3000);*/
           total++;
           speak("CORRECT");
           return 1;
       }
       else {
         //  Toast.makeText(this, "Incorrect!!", Toast.LENGTH_SHORT).show();
           total++;
           speak("TRY AGAIN");
           return 0;
       }
    }
    private int setQues(List<String> ObjectNameArray)
    {
        int resID;
        Resources res = getResources();
        Drawable drawable;
        int index=giveRandom(9);
        while("".equals(objectNameArray.get(index)))
            index=giveRandom(9);
        resID = res.getIdentifier(objectNameArray.get(index) , "drawable", getPackageName());
        drawable = res.getDrawable(resID );
        ques.setImageDrawable(drawable);
        objectNameArray.set(index,"");
        return index;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_game);
        getSupportActionBar().hide();
        Intent intent5=getIntent();
        celebration=findViewById(R.id.celebration1tile);
        this.score=Integer.parseInt(intent5.getStringExtra("Score"));
        this.total=Integer.parseInt(intent5.getStringExtra("Total"));
        new CountDownTimer(values.timer, 1000) {

            public void onTick(long millisUntilFinished) {
                values.timer=millisUntilFinished;
            }
            public void onFinish() {
                if(flag==1) {
                        Registerfinal db = new Registerfinal(TileGame.this);
                        String Score = String.valueOf(score);
                        Date date = new Date();
                        Timestamp timeStamp =  new Timestamp(date.getTime());
                        Log.d("",""+timeStamp);
                        String timestamp=timeStamp.toString();
                        Boolean answer = db.insertScore(MainActivity.ans,Score,timestamp,3,values.objectname,total);
                        if (answer) {
                            Toast.makeText(TileGame.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                        }
                    Intent intent=new Intent(TileGame.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();
        Resources res = getResources();
        int resID ;
        Drawable drawable;
        i1=findViewById(R.id.iv_11);
        i2=findViewById(R.id.iv_12);
        i3=findViewById(R.id.iv_13);
        i4=findViewById(R.id.iv_21);
        i5=findViewById(R.id.iv_22);
        i6=findViewById(R.id.iv_23);
        i7=findViewById(R.id.iv_31);
        i8=findViewById(R.id.iv_32);
        i9=findViewById(R.id.iv_33);
        ques=findViewById(R.id.imageView11);
        imageReferences.add(i1);
        imageReferences.add(i2);
        imageReferences.add(i3);
        imageReferences.add(i4);
        imageReferences.add(i5);
        imageReferences.add(i6);
        imageReferences.add(i7);
        imageReferences.add(i8);
        imageReferences.add(i9);
        //Log.d("{}",objectName);
        switch (values.objectname){
           case "Fruits": objectNameArray= fruits;
                          Collections.shuffle(objectNameArray);
               ansarray=new ArrayList<>(objectNameArray);
                          index=setQues(objectNameArray);
                          break;

            case "Flowers": objectNameArray =flowers;
                            Collections.shuffle(objectNameArray);
                ansarray=new ArrayList<>(objectNameArray);
                            index= setQues(objectNameArray);
                            break;

            case "Animals": objectNameArray=animals;
                             Collections.shuffle(objectNameArray);
                ansarray=new ArrayList<>(objectNameArray);
                             index=setQues(objectNameArray);
                             break;
        }
        flip=findViewById(R.id.button6);
        flip.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               for(int i=0;i<9;i++)
               {
                   imageReferences.get(i).setImageResource(R.drawable.ic_back );
                   imageReferences.get(i).setEnabled(true);
               }
               flip.setVisibility(View.INVISIBLE);
               celebration.setVisibility(View.INVISIBLE);
               ques.setVisibility(View.VISIBLE);
           }
       });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans=doStuff(0,index);
                if(count==3)
                {
                    flag=0;
                    Intent intent1=new Intent(TileGame.this,TileGame.class);
                    String Score=String.valueOf(score);
                    intent1.putExtra("Score",Score);
                    String Total=String.valueOf(total);
                    intent1.putExtra("Total",Total);
                    startActivity(intent1);
                    finish();
                }
                if(ans==1)
                {
                    i1.setEnabled(false);
                    index=setQues(TileGame.this.objectNameArray);
                }

            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans=doStuff(1,index);
                if(count==3)
                {
                    flag=0;
                    Intent intent1=new Intent(TileGame.this,TileGame.class);
                    String Score=String.valueOf(score);
                    intent1.putExtra("Score",Score);
                    String Total=String.valueOf(total);
                    intent1.putExtra("Total",Total);
                    startActivity(intent1);
                    finish();
                }
                if(ans==1)
                {
                    i2.setEnabled(false);
                    index=setQues(TileGame.this.objectNameArray);
                }
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans=doStuff(2,index);
                if(count==3)
                {
                    flag=0;
                    Intent intent1=new Intent(TileGame.this,TileGame.class);
                    String Score=String.valueOf(score);
                    intent1.putExtra("Score",Score);
                    String Total=String.valueOf(total);
                    intent1.putExtra("Total",Total);
                    startActivity(intent1);
                    finish();
                }
                if(ans==1)
                {
                    i3.setEnabled(false);
                    index=setQues(TileGame.this.objectNameArray);
                }
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ans= doStuff(3,index);
                if(count==3)
                {
                    flag=0;
                    Intent intent1=new Intent(TileGame.this,TileGame.class);
                    String Score=String.valueOf(score);
                    intent1.putExtra("Score",Score);
                    String Total=String.valueOf(total);
                    intent1.putExtra("Total",Total);
                    startActivity(intent1);
                    finish();
                }
                if(ans==1)
                {
                    i4.setEnabled(false);
                    index=setQues(TileGame.this.objectNameArray);
                }
            }
        });
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans=doStuff(4,index);
                if(count==3)
                {
                    flag=0;
                    Intent intent1=new Intent(TileGame.this,TileGame.class);
                    String Score=String.valueOf(score);
                    intent1.putExtra("Score",Score);
                    String Total=String.valueOf(total);
                    intent1.putExtra("Total",Total);
                    startActivity(intent1);
                    finish();
                }
                if(ans==1)
                {
                    i5.setEnabled(false);
                    index=setQues(TileGame.this.objectNameArray);
                }
            }
        });
        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ans= doStuff(5,index);
                if(count==3)
                {
                    flag=0;
                    Intent intent1=new Intent(TileGame.this,TileGame.class);
                    String Score=String.valueOf(score);
                    intent1.putExtra("Score",Score);
                    String Total=String.valueOf(total);
                    intent1.putExtra("Total",Total);
                    startActivity(intent1);
                    finish();
                }
                if(ans==1)
                {
                    i6.setEnabled(false);
                    index=setQues(TileGame.this.objectNameArray);
                }
            }
        });
        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans= doStuff(6,index);
                if(count==3)
                {
                    flag=0;
                    Intent intent1=new Intent(TileGame.this,TileGame.class);
                    String Score=String.valueOf(score);
                    intent1.putExtra("Score",Score);
                    String Total=String.valueOf(total);
                    intent1.putExtra("Total",Total);
                    startActivity(intent1);
                    finish();
                }
                if(ans==1)
                {
                    i7.setEnabled(false);
                    index=setQues(TileGame.this.objectNameArray);
                }
            }
        });
        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans=doStuff(7,index);
                if(count==3)
                {
                    flag=0;
                    Intent intent1=new Intent(TileGame.this,TileGame.class);
                    String Score=String.valueOf(score);
                    intent1.putExtra("Score",Score);
                    String Total=String.valueOf(total);
                    intent1.putExtra("Total",Total);
                    startActivity(intent1);
                    finish();
                }
                if(ans==1)
                {
                    i8.setEnabled(false);
                    index=setQues(TileGame.this.objectNameArray);
                }
            }
        });
        i9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans= doStuff(8,index);
                if(count==3)
                {
                    flag=0;
                    Intent intent1=new Intent(TileGame.this,TileGame.class);
                    String Score=String.valueOf(score);
                    intent1.putExtra("Score",Score);
                    String Total=String.valueOf(total);
                    intent1.putExtra("Total",Total);
                    startActivity(intent1);
                    finish();
                }
                if(ans==1)
                {
                    i9.setEnabled(false);
                    index=setQues(TileGame.this.objectNameArray);
                }
            }
        });
    }
}