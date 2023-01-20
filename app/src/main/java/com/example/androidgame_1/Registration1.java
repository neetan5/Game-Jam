package com.example.androidgame_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Registration1 extends AppCompatActivity {
    Button register;
    EditText username,pw,conpw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);
        register=findViewById(R.id.register);
        username=findViewById(R.id.username);
        pw=findViewById(R.id.password);
        conpw=findViewById(R.id.confirm_password);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1=username.getText().toString();
                 String pw1=pw.getText().toString();
                 String conpw1=conpw.getText().toString();
                 if( username1.isEmpty() || pw1.isEmpty()  || conpw1.isEmpty())
                 {
                     Toast.makeText(Registration1.this,"Please fill all the details!",Toast.LENGTH_SHORT).show();
                 }
                 else
             {
                 if(pw1.equals(conpw1)) {
                     String hashpw="";
                     try {
                          hashpw=SHA1(pw1);
                     }
                     catch(Exception e)
                     {
                         Toast.makeText(Registration1.this,"Something went Wrong please try later!",Toast.LENGTH_SHORT).show();
                     }
                     Registerfinal db = new Registerfinal(Registration1.this);
                     Boolean ans = db.insertinto(Registration.name1, username1,hashpw);
                     if(ans)
                     {
                         Toast.makeText(Registration1.this,"You are registered succesfully",Toast.LENGTH_SHORT).show();
                         Intent intent2=new Intent(Registration1.this,MainActivity.class);
                         startActivity(intent2);
                     }
                     else
                     {
                         Toast.makeText(Registration1.this," Username already exists!",Toast.LENGTH_SHORT).show();
                     }
                 }
                 else
                 {
                     Toast.makeText(Registration1.this,"Password does not match!!",Toast.LENGTH_SHORT).show();
                 }

             }
            }
        });
    }
        private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] textBytes = text.getBytes("iso-8859-1");
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }
}