package com.example.bluesky.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main12aActivity extends AppCompatActivity {

    private int age, height;
    private int[] psyanswer;
    private TextView a1,a2,a3,a4;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12a);

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
                a1.setText("擁有少女心喜歡甜蜜感，很愛裝可愛");
                break;
            case 1:
                a1.setText("成熟文青風，愛逛誠品書店");
                break;
            case 2:
                a1.setText("喜歡挑戰刺激生活，可能沒那麼專情，愛追韓星，跟韓風。");
                break;
            case 3:
                a1.setText("比較喜愛傳統純樸生活，生活比較一成不變，照著樸實的步調。");
                break;
        }
        switch (psyanswer[1]){
            case 0:
                a2.setText("喜歡看書旅行，體會這世界的美好，喜歡DIY");
                break;
            case 1:
                a2.setText("喜歡一成不變的生活，就得人生就是如此，簡簡單單");
                break;
            case 2:
                a2.setText("喜歡新鮮的玩意，愛追求新的事物，對舊的事情置之不理");
                break;
            case 3:
                a2.setText("標準的天然呆，什麼都點頭，感覺什麼都不知道");
                break;
        }

        switch (psyanswer[2]){
            case 0:
                a3.setText("沒什麼太強烈的個人意識，不太注重社交活動，覺得不去也沒關西。");
                break;
            case 1:
                a3.setText("自我感覺良好充滿信心，喜歡把自己最好的一面顯露出來");
                break;
            case 2:
                a3.setText("習慣低調不引人目光，但在自己的潛意識中，仍希望自己能成為暗戀對象的焦點。");
                break;
            case 3:
                a3.setText("總是大家的開心果，喜歡大家聚在一起的感覺，討厭孤獨一人。");
                break;
        }

        switch (psyanswer[3]){
            case 0:
                a4.setText("特別胖的那種，注定瘦不下來，對自己身材並不重視。");
                break;
            case 1:
                a4.setText("平常喜歡男人注視的目光。");
                break;
            case 2:
                a4.setText("喜愛韓劇，以後可能會整形。");
                break;
            case 3:
                a4.setText("很愛做白日夢但總是不付出行動。");
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
            intent.setClass(Main12aActivity.this,chatroom.class);
            startActivity(intent);
        }

    };

}



