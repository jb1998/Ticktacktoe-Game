package me.jaxbot.ticktacktoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
public static final String shared="sh";
    EditText t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button b;

        t=(EditText) findViewById(R.id.textView3);
        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(this);
        SharedPreferences sp = getSharedPreferences("TicTacToe", MODE_PRIVATE);
        boolean firstLogin = sp.getBoolean("first_login",true);
        if(firstLogin){
            Toast.makeText(this,"ENTER NAME", Toast.LENGTH_LONG).show();
            // set First Login to false

            if(!t.getText().equals("")) {
                sp.edit().putString(shared,t.getText().toString());
            }
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("first_login",false);
            editor.commit();
    }
        else
        {

            String name2=sp.getString(shared,"");
            t.setText(name2);
            Toast.makeText(this,"Welcome "+name2,Toast.LENGTH_SHORT);
        }


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        Intent i = new Intent();
        i.putExtra(IntentConstants.PLAYER_NAME, "Abc");
        i.setClass(this, MainActivity.class);
        startActivity(i);


    }
}



