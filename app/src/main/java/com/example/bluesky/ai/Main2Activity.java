package com.example.bluesky.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup.OnCheckedChangeListener;
import  android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textView;
    private ImageButton imageButton;
    private RadioGroup radgender,radshape;
    private EditText editText;

    int[] basicinformation= new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Gender
        radgender = (RadioGroup) findViewById(R.id.gender);
        //radgender 觸發事件
        radgender.setOnCheckedChangeListener(radgenderListener);

        //Age
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.age);
        //Age seekbar 觸發事件
        textView.setText(String.valueOf(seekBar.getProgress()+12));
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

                textView.setText(String.valueOf(seekBar.getProgress()+12));
                basicinformation[3] = seekBar.getProgress();
            }
        });

        //shape
        radshape = (RadioGroup) findViewById(R.id.shape);
        //radgender 觸發事件
        radshape.setOnCheckedChangeListener(radshapeListener);

        //get button widget
        imageButton = (ImageButton)findViewById(R.id.imageButton);

        //after press button
        imageButton.setOnClickListener(imageButtonListener);
    }

    private RadioGroup.OnCheckedChangeListener radgenderListener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group,int checkedId){

            if(checkedId == R.id.boy)basicinformation[0]=0;

            else if(checkedId == R.id.girl)basicinformation[0]=1;

        }
    };

    private RadioGroup.OnCheckedChangeListener radshapeListener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group,int checkedId){

            if(checkedId == R.id.slim)basicinformation[1]=0;

            else if(checkedId == R.id.fit)basicinformation[1]=1;

            else if(checkedId == R.id.chubby)basicinformation[1]=2;

        }
    };



    private  ImageButton.OnClickListener imageButtonListener = new ImageButton.OnClickListener(){
        @Override
        public void onClick(View v){
            //height
            editText = (EditText)findViewById(R.id.height);
            basicinformation[2] = Integer.parseInt(editText.getText().toString());
            Intent intent = new Intent();
            Bundle bundle=new Bundle();
            bundle.putInt("age",basicinformation[3]+12);
            bundle.putInt("height",basicinformation[2]);
            intent.putExtras(bundle);
            if(basicinformation[0]==0){
                switch (basicinformation[1]){
                  case 0:
                        intent.setClass(Main2Activity.this,Main00Activity.class);
                        break;
                  case 1:
                        intent.setClass(Main2Activity.this,Main01Activity.class);
                        break;
                  case 2:
                        intent.setClass(Main2Activity.this,Main02Activity.class);
                        break;
                }
            }
            else if (basicinformation[0]==1){
                switch (basicinformation[1]){
                    case 0:
                          intent.setClass(Main2Activity.this,Main10Activity.class);
                          break;
                    case 1:
                          intent.setClass(Main2Activity.this,Main11Activity.class);
                          break;
                    case 2:
                          intent.setClass(Main2Activity.this,Main12Activity.class);
                          break;
                }
            }
            startActivity(intent);

        }

    };

}







