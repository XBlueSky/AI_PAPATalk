package com.example.bluesky.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main02Activity extends AppCompatActivity {

    private TextView q1;
    private RadioGroup a1,a2,a3,a4;
    int[] psyanswer= new int[4];
    private int age,height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);
        TextView agetext=(TextView)findViewById(R.id.age);
        TextView heighttext=(TextView)findViewById(R.id.height);
        q1=(TextView)findViewById(R.id.q1);
        a1=(RadioGroup)findViewById(R.id.a1);
        a2=(RadioGroup)findViewById(R.id.a2);
        a3=(RadioGroup)findViewById(R.id.a3);
        a4=(RadioGroup)findViewById(R.id.a4);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        age = bundle.getInt("age");
        height=bundle.getInt("height");
        agetext.setText(age + "  years");
        heighttext.setText(height + " cm");

        a1.setOnCheckedChangeListener(a1Listener);
        a2.setOnCheckedChangeListener(a2Listener);
        a3.setOnCheckedChangeListener(a3Listener);
        a4.setOnCheckedChangeListener(a4Listener);



    }

    private RadioGroup.OnCheckedChangeListener a1Listener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group,int checkedId){
            if(checkedId == R.id.a11){
                psyanswer[0]=0;
                q1.setVisibility(View.GONE);
                a1.setVisibility(View.GONE);
            }
            else if(checkedId == R.id.a12){
                psyanswer[0]=1;
                q1.setVisibility(View.GONE);
                a1.setVisibility(View.GONE);
            }
            else if(checkedId == R.id.a13){
                psyanswer[0]=2;
                q1.setVisibility(View.GONE);
                a1.setVisibility(View.GONE);
            }
            else if(checkedId == R.id.a14){
                psyanswer[0]=3;
                q1.setVisibility(View.GONE);
                a1.setVisibility(View.GONE);
            }
            q1.setText("你喝飲料時會將吸管套放哪裡?");
            q1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
        }
    };
    private RadioGroup.OnCheckedChangeListener a2Listener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group,int checkedId){
            if(checkedId == R.id.a21){
                psyanswer[1]=0;
                q1.setVisibility(View.GONE);
                a2.setVisibility(View.GONE);
            }
            else if(checkedId == R.id.a22){
                psyanswer[1]=1;
                q1.setVisibility(View.GONE);
                a2.setVisibility(View.GONE);
            }
            else if(checkedId == R.id.a23){
                psyanswer[1]=2;
                q1.setVisibility(View.GONE);
                a2.setVisibility(View.GONE);
            }
            else if(checkedId == R.id.a24){
                psyanswer[1]=3;
                q1.setVisibility(View.GONE);
                a2.setVisibility(View.GONE);
            }
            q1.setText("出門習慣以什麼代步?");
            q1.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
        }
    };
    private RadioGroup.OnCheckedChangeListener a3Listener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group,int checkedId){
            if(checkedId == R.id.a31){
                psyanswer[2]=0;
                q1.setVisibility(View.GONE);
                a3.setVisibility(View.GONE);
            }
            else if(checkedId == R.id.a32){
                psyanswer[2]=1;
                q1.setVisibility(View.GONE);
                a3.setVisibility(View.GONE);
            }
            else if(checkedId == R.id.a33){
                psyanswer[2]=2;
                q1.setVisibility(View.GONE);
                a3.setVisibility(View.GONE);
            }
            else if(checkedId == R.id.a34){
                psyanswer[2]=3;
                q1.setVisibility(View.GONE);
                a3.setVisibility(View.GONE);
            }
            q1.setText("你和朋友出去你會坐車子裡哪個位子?");
            q1.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
        }
    };
    private RadioGroup.OnCheckedChangeListener a4Listener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group,int checkedId){
            Intent intent = new Intent();
            Bundle bundle=new Bundle();
            bundle.putInt("age",age);
            bundle.putInt("height", height);
            bundle.putIntArray("psyanswer",psyanswer);
            intent.putExtras(bundle);
            if(checkedId == R.id.a41){
                psyanswer[3]=0;
                q1.setVisibility(View.GONE);
                a4.setVisibility(View.GONE);
                intent.setClass(Main02Activity.this, Main02aActivity.class);
            }
            else if(checkedId == R.id.a42){
                psyanswer[3]=1;
                q1.setVisibility(View.GONE);
                a4.setVisibility(View.GONE);
                intent.setClass(Main02Activity.this, Main02aActivity.class);
            }
            else if(checkedId == R.id.a43){
                psyanswer[3]=2;
                q1.setVisibility(View.GONE);
                a4.setVisibility(View.GONE);
                intent.setClass(Main02Activity.this, Main02aActivity.class);
            }
            else if(checkedId == R.id.a44){
                psyanswer[3]=3;
                q1.setVisibility(View.GONE);
                a4.setVisibility(View.GONE);
                intent.setClass(Main02Activity.this, Main02aActivity.class);
            }
            startActivity(intent);
        }
    };

}
