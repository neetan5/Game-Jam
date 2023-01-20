package com.example.androidgame_1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Registerfinal extends SQLiteOpenHelper {
    int flag=0;
    Registerfinal(Context context)
    {
        super(context,"Registerfinal.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Register1(id Integer PRIMARY KEY, name Text,username Text UNIQUE, password Text)");
        db.execSQL("create Table Game(gameid Integer,gameName Text)");
        db.execSQL("create Table Scoreofgame(userid Integer,score Text, time Text, gameID Integer,object Text,total Integer, FOREIGN KEY(gameID) REFERENCES Game(gameid), FOREIGN KEY(userid) REFERENCES Register1(id) )");
        //SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("gameid",1);
        values.put("gameName","Count Game");
        long result=db.insert("Game",null,values);
        values=new ContentValues();
        values.put("gameid",2);
        values.put("gameName","Tile Game Level-1");
        result=db.insert("Game",null,values);
        values=new ContentValues();
        values.put("gameid",3);
        values.put("gameName","Tile Game Level-2");
        result=db.insert("Game",null,values);
        values=new ContentValues();
        values.put("gameid",4);
        values.put("gameName","Match The Tile Level-1");
        result=db.insert("Game",null,values);
        values=new ContentValues();
        values.put("gameid",5);
        values.put("gameName","Match The Tile Level-2");
        result=db.insert("Game",null,values);
        values=new ContentValues();
        values.put("gameid",6);
        values.put("gameName","Match the following");
        result=db.insert("Game",null,values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertScore(int int_id,String Score,String time,int gameId,String object,int total)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("userid",int_id);
        values.put("score",Score);
        values.put("time",time);
        values.put("gameID",gameId);
        values.put("object",object);
        values.put("total",total);
        long result=db.insert("Scoreofgame",null,values);
        if(result==-1)
        {
            return false;
        }
        return  true;
    }
    public boolean insertinto(String name,String username,String pw)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("username",username);
        values.put("password",pw);
        long result=db.insert("Register1",null,values);
        if(result==-1)
        {
            return false;
        }
        return  true;
    }
    public int  checklogin(String name,String pw) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("select id from Register1 where username=? and password =?",new String[]{name,pw});
        if(c.getCount()>0)
        {
           if(c.moveToFirst())
            {
                int userId = c.getInt(0);
                Log.d("User id",""+userId);
                return userId;
            }
        }
        return -1;
    }
    public boolean checknum(String num)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Register1 where phone=?",new String[]{num});
        if(c.getCount()>0)
        {
            return false;
        }
        return true;
    }
    public int setnewpw(String new_pw,String user_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("password",new_pw);
       // values.put("username",user_name);
        try{
            db.update("Register1",values,"username=?",new String[]{user_name});
            //Cursor c=db.rawQuery("update Register1 set password=? where username=?",new String[]{new_pw,user_name});
            return 1;
        }
        catch(Exception e)
        {
            Log.d("error",""+e);
            return -1;
        }
    }
    public int  otp(String name,String phone) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from Register1 where username=? and phone=?",new String[]{name,phone});
        if(c.getCount()>0)
        {
            Log.d("{count}",""+c.getCount());
            return 1;
        }
        return -1;
    }

    public Cursor getScore() {

        int userId = MainActivity.ans;
        Log.d("User id from getscore",""+userId);
        SQLiteDatabase db = this.getWritableDatabase();
       // Cursor c=db.rawQuery("select  sum(score), object as score from Scoreofgame where userid = ? group by object ORDER by object desc",new String[]{String.valueOf(userId)});
        Cursor c=null;
        try
        {
              c=db.rawQuery("select  sum(total),sum(score),round((sum(score)*1.0/sum(total)) *100, 2) as percentage, object as score from Scoreofgame where userid = ? group by object ORDER by object desc",new String[]{String.valueOf(userId)});
        }
        catch (Exception e)
        {
            Log.d("{}",  ""+e);
        }
        if(c.getCount()==0)
        {
            return null;
        }
        if(c.getCount()>0)
        {
            return c;
        }
        return null;
    }
   public Cursor gameRatings()
   {
       int userId = MainActivity.ans;
       Log.d("User id from getscore",""+userId);
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor c=null;
       try
       {
               c=db.rawQuery("select g.gameName,sum(total) as total, sum(score) as score from Game as g inner join Scoreofgame as s on g.gameid = s.gameid where userid = ? GROUP by s.gameid",new String[]{String.valueOf(userId)});
       }
       catch (Exception e)
       {
           Log.d("{}",  ""+e);
       }
       if(c.getCount()>0)
       {
           return c;
       }
       return null;
   }
   public float set_level(int userid,int gameid,String object)
   {
       float ans=0;
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor c=null;
       try
       {
           c=db.rawQuery("SELECT round((sum(score)*1.0/sum(total)) *100, 2) as percentage from (select * from Scoreofgame where userid=? and gameid=? and object=? order by time desc limit 5)",new String[]{String.valueOf(userid),String.valueOf(gameid),object});
           if(c.moveToFirst())
           {
               ans=c.getInt(0);
           }
       }
       catch (Exception e)
       {
           Log.d("{}",  ""+e);
       }
       return ans;
   }
}
