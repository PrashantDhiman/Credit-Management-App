package com.prashant.creditmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OneUserActivity extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    Button transferCreditButton;
    EditText editText;
    int index;
    int currentUserCredits;
    int creditsEntered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_user);

        t1=findViewById(R.id.nameView);
        t2=findViewById(R.id.emailView);
        t3=findViewById(R.id.balanceView);
        t4=findViewById(R.id.phoneNoView);
        editText=findViewById(R.id.editText);

        transferCreditButton=findViewById(R.id.transferButton);

        Intent intent2=getIntent();
        index=intent2.getIntExtra("index",-1);


        t1.setText(MainActivity.usersName.get(index));
        t2.setText(MainActivity.usersEmail.get(index));
        t3.setText(MainActivity.usersBalance.get(index));
        t4.setText(MainActivity.usersPhoneNo.get(index));

        setTitle(MainActivity.usersName.get(index)+"'s Info");

        currentUserCredits=Integer.parseInt(MainActivity.usersBalance.get(index));

    }

    public void selectUserToCredit(View v){

        //Log.i("text is -------- ","yeahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh "+editText.getText().toString());

        try{

            String str=editText.getText().toString();
            creditsEntered=Integer.parseInt(str);

            if(!str.isEmpty() && str.matches("\\d+") && creditsEntered<=currentUserCredits){ // it checks if string is not empty and all digits are integers

                creditsEntered=Integer.parseInt(str);
                Intent intent3=new Intent(getApplicationContext(),AllUsersActivity.class);
                intent3.putExtra("intentStartedByActivity",3);
                startActivity(intent3);
            }
        else if(creditsEntered>currentUserCredits){

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Invalid credits")
                    .setMessage("Please enter valid number of credits")
                    .setPositiveButton("Ok",null)
                    .show();

        }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        try{

            if(currentUserCredits>=creditsEntered){

                int x=currentUserCredits;

                //updating current user's balance
                MainActivity.usersBalance.set(index,String.valueOf(currentUserCredits-creditsEntered));
                t3.setText(String.valueOf(currentUserCredits-creditsEntered));

                //updating other user's balance
                int indexOfUserToBeCredited=AllUsersActivity.indexOfUserSelectedForTransfer;
                int initialBalanceOfUser=Integer.parseInt(MainActivity.usersBalance.get(indexOfUserToBeCredited));
                MainActivity.usersBalance.set(indexOfUserToBeCredited,String.valueOf(initialBalanceOfUser+creditsEntered));

                MainActivity.saveData();

                if(x!=(currentUserCredits-creditsEntered)){
                    new AlertDialog.Builder(this)
                            .setIcon(android.R.drawable.checkbox_on_background)
                            .setTitle("Success")
                            .setMessage("Your credits are successfully transferred")
                            .setPositiveButton("Ok",null)
                            .show();
                }

            }

        }catch (Exception e){e.printStackTrace();}

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
