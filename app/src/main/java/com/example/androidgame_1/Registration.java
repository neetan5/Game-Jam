package com.example.androidgame_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class Registration extends AppCompatActivity {
   // EditText name,email,username,pw,conpw,verifyotp;
    EditText phone_no,verify_otp,name;
    String verificationId;
    public static String phone1;
    public static String name1;
    Button get_otp,verify_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        phone_no=findViewById(R.id.phone_no);
        name=findViewById(R.id.name);

//     Button button=findViewById(R.id.button4);
//     button.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View view) {
//             name=findViewById(R.id.name);
//             email=findViewById(R.id.email);
//             username=findViewById(R.id.username);
//             pw=findViewById(R.id.pw);
//             conpw=findViewById(R.id.conpw);
//             String name1=name.getText().toString();
//             String email1=email.getText().toString();
//             String username1=username.getText().toString();
//             String pw1=pw.getText().toString();
//             String conpw1=conpw.getText().toString();
//             if(name1.isEmpty() || email1.isEmpty() || pw1.isEmpty()  || username1.isEmpty() || conpw1.isEmpty())
//             {
//                 Toast.makeText(Registration.this,"Please fill all the details!",Toast.LENGTH_SHORT).show();
//             }
//             else
//             {
//                 if(pw1.equals(conpw1)) {
//                     String hashpw="";
//                     try {
//                          hashpw=SHA1(pw1);
//                     }
//                     catch(Exception e)
//                     {
//                         Toast.makeText(Registration.this,"Something went Wrong please try later!",Toast.LENGTH_SHORT).show();
//                     }
//                     Registerfinal db = new Registerfinal(Registration.this);
//                     Boolean ans = db.insertinto(name1, email1, username1,hashpw);
//                     if(ans)
//                     {
//                         Toast.makeText(Registration.this,"You are registered succesfully",Toast.LENGTH_SHORT).show();
//                         Intent intent2=new Intent(Registration.this,MainActivity.class);
//                         startActivity(intent2);
//                     }
//                     else
//                     {
//                         Toast.makeText(Registration.this," Username already exists!",Toast.LENGTH_SHORT).show();
//                     }
//                 }
//                 else
//                 {
//                     Toast.makeText(Registration.this,"Password does not match!!",Toast.LENGTH_SHORT).show();
//                 }
//
//             }
//         }
//     });

    }
    
}