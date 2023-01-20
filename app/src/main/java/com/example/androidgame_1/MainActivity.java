package com.example.androidgame_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class values
{

    public static String name1;
    public static String objectname;
    public static long timer;

}
public class MainActivity extends AppCompatActivity {
    Button button ,b1,forgotpw;
    EditText name,password;
    public static int ans;
    //public static String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.flipbutton);
        b1=findViewById(R.id.button2);
        name=findViewById(R.id.editTextTextPersonName);
        password=findViewById(R.id.editTextTextPassword);
       // forgotpw=findViewById(R.id.forgotpw);
//        forgotpw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ForgotPassword.class);
//                startActivity(intent);
//            }
//        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               values.name1=name.getText().toString();
                String password1=password.getText().toString();
                String hashpw="";
                try{
                    hashpw=SHA1(password1);
                    Log.d("{}",hashpw);
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this,"Incorrect Username or password",Toast.LENGTH_SHORT).show();
                }
                Registerfinal db= new Registerfinal(MainActivity.this);
                ans=db.checklogin(values.name1,hashpw);
                if(ans!=-1)
                {
                    Intent intent=new Intent(MainActivity.this,ObjectOption.class);
                    startActivity(intent);
                    name.setText("");
                    password.setText("");
                }
               else
                   Toast.makeText(MainActivity.this,"Incorrect Username or password",Toast.LENGTH_SHORT).show();
            }
        });
       b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, Registration1.class);
                startActivity(intent1);
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