package com.example.androidgame_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Gameoption extends AppCompatActivity {
    //TextView t1;
    Button count,tile,matchtile,match;
    String s;
    public static final String  EXTRA_string="sbxguedcdwvcvw";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameoption);
        getSupportActionBar().hide();
        count=findViewById(R.id.button7);
        tile=findViewById(R.id.button10);

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Gameoption.this,countgame.class);
                startActivity(intent1);
            }
        });
        tile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registerfinal f = new Registerfinal(Gameoption.this);
                float ans = f.set_level(MainActivity.ans,2,values.objectname);
                Intent intent1;
                if(ans<80)
                {
                     intent1=new Intent(Gameoption.this,TileGame6.class);
                }
                else
                {
                     intent1=new Intent(Gameoption.this,TileGame.class);
                }
                intent1.putExtra("Score","0");
                intent1.putExtra("Total","0");
                startActivity(intent1);
            }
        });
    }
}