package com.example.bluesky.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main10aActivity extends AppCompatActivity {

    private int age, height;
    private int[] psyanswer;
    private TextView a1,a2,a3,a4;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10a);

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
                a1.setText("較有自信，喜歡風光民媚引人注目成為眾人焦點，FB百讚甚至千讚，IG粉絲遠遠高於追蹤者");
                break;
            case 1:
                a1.setText("有個性，喜歡街頭風格，給人一種神祕不容易親近的感覺");
                break;
            case 2:
                a1.setText("不拘小節，懶惰亦不在乎他人眼光，覺得自己是無拘無束的小鳥。");
                break;
            case 3:
                a1.setText("崇尚自然，萬物一體的概念，或是小時候受過某些刺激導致腦袋有破洞的情形。");
                break;
        }
        switch (psyanswer[1]){
            case 0:
                a2.setText("較為嬉鬧不正經的人，不喜歡嚴肅，有時會讓別人覺得很機車，但是你如果生氣了她會更高興");
                break;
            case 1:
                a2.setText(" 較內向害羞，給人靦腆的感覺，但是你不能以為他在對你害羞有好感，千萬別會錯意");
                break;
            case 2:
                a2.setText(" 豪邁直爽的人，有許多男性朋友，比較不在意自己的形象，通常很容易相處");
                break;
            case 3:
                a2.setText("可能被孤立排擠的人，朋友較少，但也有可能只是單純不喜歡你的笑話，就是你自己的問題了");
                break;
        }

        switch (psyanswer[2]){
            case 0:
                a3.setText("有正當理由堅持自己看法的人，喜歡喝咖啡來強調自己很高貴。");
                break;
            case 1:
                a3.setText("較愛玩，喜歡自由不喜歡被管教，喜歡表現自我，即使唱的不是很好聽。");
                break;
            case 2:
                a3.setText("不是缺乏文化知識修養的人，就是喜歡安靜，不想要跟你講話，來圖書館就不用聊天了。");
                break;
            case 3:
                a3.setText("想法古怪、難以理解的人，想要穿泳裝來展現自己纖細的身材，但他的目的可就不只是簡單的聊天了。");
                break;
        }

        switch (psyanswer[3]){
            case 0:
                a4.setText("告訴大家自己心裡是個很年輕的學生，像背學生側背包一樣，有可能是個內心單純的少女，不然就是不服老的女人。");
                break;
            case 1:
                a4.setText("貴婦手肘挽包法 -->想要炫耀自己帶的包包都是名牌，自認高人一等，包包就是榮耀或獎賞的象徵，這種人通常識包無數，也可以跟她討論當季的包包流行。");
                break;
            case 2:
                a4.setText("女強人拿法，通常包包很重，用肩膀背易受傷，所以她通常是個個性好強的人且兼具強壯的二頭肌。");
                break;
            case 3:
                a4.setText("如此人物有非常多的乾哥，一定有很多人當他司機，通常具有厲害的撒嬌能力。");
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
            intent.setClass(Main10aActivity.this,chatroom.class);
            startActivity(intent);
        }

    };

}



