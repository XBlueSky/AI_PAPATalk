package com.example.bluesky.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main02aActivity extends AppCompatActivity {

    private int age, height;
    private int[] psyanswer;
    private TextView a1,a2,a3,a4;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02a);

        TextView agetext = (TextView) findViewById(R.id.age);
        TextView heighttext = (TextView) findViewById(R.id.height);
        home = (Button)findViewById(R.id.home);
        a1 = (TextView) findViewById(R.id.a1);
        a2 = (TextView) findViewById(R.id.a2);
        a3 = (TextView) findViewById(R.id.a3);
        a4 = (TextView) findViewById(R.id.a4);


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        age = bundle.getInt("age");
        height = bundle.getInt("height");
        psyanswer = bundle.getIntArray("psyanswer");
        agetext.setText(age + " years");
        heighttext.setText(height + " cm");

        switch (psyanswer[0]){
            case 0:
                a1.setText("為人正直，在工作上不懂逢迎、巴結，一切依照自己的方式過日子，是個理想主義者。");
                break;
            case 1:
                a1.setText("認真、固執、腳踏實地，且不占人便宜，是個君子。但過於嘮叨又一絲不苟，是個相當無趣人。");
                break;
            case 2:
                a1.setText("他應該有憂鬱症吧！凡事都太謹慎了，而且開車的技術可能不佳，缺少自信。");
                break;
            case 3:
                a1.setText("任性、虛榮、驕做得像隻孔雀，以自我為中心就是你給人的感覺。");
                break;
        }
        switch (psyanswer[1]){
            case 0:
                a2.setText("是一個非常懶惰的人，連垃圾桶都不願意找");
                break;
            case 1:
                a2.setText("可見你常常去飲料店買飲料。因為你知道飲料店櫃檯都有一個小垃圾桶可以丟");
                break;
            case 2:
                a2.setText("不注重環保的人，連買杯飲料都需要塑膠袋，只求方便不求環保");
                break;
            case 3:
                a2.setText("一定是個不在意他人眼光的人，直接用飲料杯喝，真男人");
                break;
        }

        switch (psyanswer[2]){
            case 0:
                a3.setText("健康且注重環保，腳踏車不需要加汽油也不會排出二氧化碳，還能鍛煉身體。");
                break;
            case 1:
                a3.setText("喜愛追求刺激，騎機車可以穿梭在車流中，享受超車與鑽車的快感。");
                break;
            case 2:
                a3.setText("重視安全，大家都說汽車是鐵包肉，可想而知開車出門是最安全的選擇，而且還可能多金。");
                break;
            case 3:
                a3.setText("注重環保，希望跟從大家得腳步，是個務實的人。");
                break;
        }

        switch (psyanswer[3]){
            case 0:
                a4.setText("一定是個健談的人，因為右前方的位子可以輕鬆地跟駕駛座的人聊天，比較沒有距離感。");
                break;
            case 1:
                a4.setText("通常坐右後方是把駕駛座的人當作司機來看待，可見你是個喜歡奴役別人且高傲的人。");
                break;
            case 2:
                a4.setText("左後方可以說是完全被擋住，駕駛座不僅看不到他也很難跟他聊天，可見他們不熟，還是不要當朋友好了。");
                break;
            case 3:
                a4.setText("寧願自己開一台車也不願意當作乘客，可見他非常不信任他人的技術，以後就給他當司機好了。");
                break;
        }

        home.setOnClickListener(homeListener);




    }

    public void a1click(View view) {
        a1.setVisibility(View.INVISIBLE);
        a3.setVisibility(View.VISIBLE);
    };
    public void a2click(View view) {
        a2.setVisibility(View.INVISIBLE);
        a4.setVisibility(View.VISIBLE);
        home.setVisibility(View.VISIBLE);
    };
    private  Button.OnClickListener homeListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent();
            Bundle bundle=new Bundle();
            bundle.putInt("age",age);
            bundle.putInt("height", height);
            intent.putExtras(bundle);
            intent.setClass(Main02aActivity.this,chatroom.class);
            startActivity(intent);
        }

    };

}



