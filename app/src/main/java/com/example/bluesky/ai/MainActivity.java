package com.example.bluesky.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
   private Button btnDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get button widget
        btnDo = (Button)findViewById(R.id.button1);

        //after press button
        btnDo.setOnClickListener(btnDoListener);

    }

    private  Button.OnClickListener btnDoListener = new Button.OnClickListener(){
        @Override
       public void onClick(View v){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,Main2Activity.class);
            startActivity(intent);

        }

    };
}
