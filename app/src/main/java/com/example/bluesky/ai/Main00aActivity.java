package com.example.bluesky.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main00aActivity extends AppCompatActivity {

    private int age, height;
    private int[] psyanswer;
    private TextView a1,a2,a3,a4;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main00a);

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
               a1.setText("認真上進的個性，將睡眠時間與醒著的時間分割得很清楚，即使課程在無聊也很尊重老師");
               break;
           case 1:
               a1.setText("對於老師毫不在意，在意的是自己的睡眠，這種人常常在下課的時候還在睡，通常具有非常率真自然的性格");
               break;
           case 2:
               a1.setText("有點在意老師的眼光，一單手撐起頭部來讓自己看起來有在聽課，時時刻刻警戒著聽上課重點，這種人缺點就是，常常會導致自己睡不好也沒聽到重點，對於事情常常沒有定見，模擬兩可。");
               break;
           case 3:
               a1.setText("這種人對於自己的身體看得比任何事情還要重要，只要想睡覺就回去睡最舒服的床，此種人容易睡過頭，在早上也通常看不到他，想要在早上看到他比登天還要難。");
               break;
       }
        switch (psyanswer[1]){
            case 0:
                a2.setText("非常重視健康，但有時候是非常矯情的人，當一群人要買飲料的時，卻說我喝水就好，到時候還是會喝你飲料的王八蛋");
                break;
            case 1:
                a2.setText("是個瘦子卻常喝珍奶，彷彿是在告訴大家他的瘦是天生的，此種人就是喜歡喝高熱量飲料，在昭告天下他很瘦，可以喝");
                break;
            case 2:
                a2.setText("茶乃是中國傳統飲品，喜歡喝茶的人，通常有著修身養性的性格，也有著能耐苦的本事");
                break;
            case 3:
                a2.setText("這種人要非常的小心，當他一天沒喝到咖啡時，他會非常的暴躁，盡量少喝咖啡，小心咖啡因中毒");
                break;
        }

        switch (psyanswer[2]){
            case 0:
                a3.setText("此人有一種擔心對方誤解自己的心理，因此性格有些急躁，內心常有不平。");
                break;
            case 1:
                a3.setText("表現出其有\"動搖\"的心理，即使是很確定的事情，也有應該的事情，很怕被打臉，做事很小心");
                break;
            case 2:
                a3.setText("這種人說話很慢，需要先有語助詞，告訴大家他要開始說話了，常是詞彙少，或是思維慢，因怕說錯話，需有間歇來思考。這種人的內心也常常是很孤獨的。");
                break;
            case 3:
                a3.setText("這個人一定有打LOL，也對亞洲統神有一定的了解，可以跟他多聊這方面的事情。");
                break;
        }

        switch (psyanswer[3]){
            case 0:
                a4.setText("大多做事情很勤奮，對待工作認真，對自己本分內的事情具有很強的責任感。");
                break;
            case 1:
                a4.setText("沒有明顯修理痕跡的男性，大多不看重外表，喜歡內在的收獲，很多人都是工作狂。");
                break;
            case 2:
                a4.setText("大多很注重外在形象，甚至有點虛榮愛面子，對事物也比較挑剔，喜歡吹毛求疵，有的甚至有完美主義傾向。");
                break;
            case 3:
                a4.setText("此種人做事乾脆率直，不讓頂上的毛髮成為自己的負擔，此種人一定有堅定的決心，通常是運動員，和尚，跟禿頭乾脆剃光的人。");
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
            intent.setClass(Main00aActivity.this,chatroom.class);
            startActivity(intent);
        }

    };

}



