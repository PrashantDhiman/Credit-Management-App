package com.prashant.creditmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static SQLiteDatabase userDB;
    static ArrayList<String> usersName=new ArrayList<>();
    static ArrayList<String> usersEmail=new ArrayList<>();
    static ArrayList<String> usersPhoneNo=new ArrayList<>();
    static ArrayList<String> usersBalance=new ArrayList<>();
    static int noOfUsers=10;  // change this also when you increase no of users
    Button showUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showUsers=findViewById(R.id.button);

        userDB=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        //userDB.execSQL("DROP TABLE users");
        //userDB.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name VARCHAR, email VARCHAR, phoneNo INTEGER, balance INTEGER)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Prashant','prashant@pd.com',1165784398,2500)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Prateek','prateek@pd.com',1256474993,460)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Sahil','sahil@pd.com',3165435348,50)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Parul','parul@pd.com',6354354935,355)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Nikhil','nikhil@pd.com',1145435344,200)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Simran','simran@pd.com',2343553435,782)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Aashish','aashish@pd.com',1165754353,290)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Suman','suman@pd.com',1435436542,460)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Sachin','sachin@pd.com',7675465938,876)");
        //userDB.execSQL("INSERT INTO users (name,email,phoneNo,balance) VALUES ('Ajay','ajay@pd.com',3434324544,660)");

        Cursor c=MainActivity.userDB.rawQuery("Select * from users",null);
        int nameIndex=c.getColumnIndex("name");
        int emailIndex=c.getColumnIndex("email");
        int phoneNoIndex=c.getColumnIndex("phoneNo");
        int balanceIndex=c.getColumnIndex("balance");
        //int idIndex=c.getColumnIndex("id");
        c.moveToFirst();
        MainActivity.usersName.clear();
        MainActivity.usersEmail.clear();
        MainActivity.usersPhoneNo.clear();
        MainActivity.usersBalance.clear();
        while(c!=null){
            MainActivity.usersName.add(c.getString(nameIndex));
            MainActivity.usersEmail.add(c.getString(emailIndex));
            MainActivity.usersPhoneNo.add(c.getString(phoneNoIndex));
            MainActivity.usersBalance.add(c.getString(balanceIndex));
            //Log.i("id is ",c.getString(idIndex));
            if(!c.isLast()){
                c.moveToNext();
            }else{
                break;
            }
        }

    }

    public void showAllUsers(View v){

        Intent intent1=new Intent(getApplicationContext(),AllUsersActivity.class);
        startActivity(intent1);

    }

    public static void saveData(){

        for(int i=0;i<noOfUsers;i++){

            int item=Integer.parseInt(usersBalance.get(i));
            String updateSql="UPDATE users SET balance="+item+" WHERE id="+(i+1);
            userDB.execSQL(updateSql);

        }

    }

}
