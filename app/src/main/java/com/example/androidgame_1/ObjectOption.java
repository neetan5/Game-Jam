package com.example.androidgame_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ObjectOption extends AppCompatActivity {
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_option);
        getSupportActionBar().hide();
       b1=findViewById(R.id.button8);
        b2=findViewById(R.id.button11);
        b3=findViewById(R.id.button12);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder timer=new AlertDialog.Builder(ObjectOption.this);
                timer.setTitle("Enter time limit(in minutes)");
                final EditText input = new EditText(ObjectOption.this);
                timer.setView(input);
                timer.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String ans=input.getText().toString();
                        if(ans.equals(" ") || ans.equals(""))
                        {
                            Toast.makeText(ObjectOption.this,"Enter valid Time", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            values.timer =Integer.parseInt(ans) ;
                            values.timer=values.timer*1000*60;
                            Intent intent2 =new Intent(ObjectOption.this,Gameoption.class);
                            values.objectname="Flowers";
                            startActivity(intent2);
                        }
                    }
                });
                timer.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });
                timer.show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder timer=new AlertDialog.Builder(ObjectOption.this);
                timer.setTitle("Enter time limit(in minutes)");
                final EditText input = new EditText(ObjectOption.this);
                timer.setView(input);
                timer.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String ans=input.getText().toString();
                        if(ans.equals(" ") || ans.equals(""))
                        {
                            Toast.makeText(ObjectOption.this,"Enter valid Time", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            values.timer =Integer.parseInt(ans) ;
                            values.timer=values.timer*1000*60;
                            Intent intent2 =new Intent(ObjectOption.this,Gameoption.class);
                            values.objectname="Fruits";
                            startActivity(intent2);
                        }
                    }
                });
                timer.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });
                timer.show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder timer=new AlertDialog.Builder(ObjectOption.this);
                timer.setTitle("Enter time limit(in minutes)");
                final EditText input = new EditText(ObjectOption.this);
                timer.setView(input);
                timer.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String ans=input.getText().toString();
                        if(ans.equals(" ") || ans.equals(""))
                        {
                            Toast.makeText(ObjectOption.this,"Enter valid Time", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            values.timer =Integer.parseInt(ans) ;
                            values.timer=values.timer*1000*60;
                            Intent intent2 =new Intent(ObjectOption.this,Gameoption.class);
                            values.objectname="Animals";
                            startActivity(intent2);
                        }
                    }
                });
                timer.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });
                timer.show();
                
            }
        });
    }
}