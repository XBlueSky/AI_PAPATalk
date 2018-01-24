package com.example.bluesky.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main11aActivity extends AppCompatActivity {

    private int age, height;
    private int[] psyanswer;
    private TextView a1,a2,a3,a4;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11a);

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
                a1.setText("如果先洗身體再洗頭，洗頭的髒水不就又會把身體弄髒。通常會較有計畫的處理事情，但性格較容易分心。");
                break;
            case 1:
                a1.setText("身體佔的面積最大，應該先處理。通常較為熱血，只看到事物的表面，遇到事情很容易腦衝送頭，其實內心希望受到別人注意。");
                break;
            case 2:
                a1.setText("應該從身體最髒的地方開始洗。你是一個很有自信的人，喜歡照自己的步調生活，不太介意他人的眼光，但有時較為自私。");
                break;
            case 3:
                a1.setText("聞一聞，那味道就是人與生俱來最純真的體味，他是個崇尚大自然不一般之人。");
                break;
        }
        switch (psyanswer[1]){
            case 0:
                a2.setText("容易受他人影響，大家都用叉子吃啊不然哩!!");
                break;
            case 1:
                a2.setText("固執，天然呆，以為義大利麵和鐵板麵是一樣的東西，並認為叉子是西方的東西。");
                break;
            case 2:
                a2.setText("認為這世界上沒有不可能的事情，相信外星人與鬼的存在。萬中選一的練武奇才，百年難得一見，不信你用湯匙去吃義大利麵看看。");
                break;
            case 3:
                a2.setText("熱血，完全不忌諱他人眼光，事實上吃所有東西都是用手，有點愛炫耀。就像是在說\"我手完全不會怕燙，超猛的\"。");
                break;
        }

        switch (psyanswer[2]){
            case 0:
                a3.setText("小氣，視錢如命。:這三根手指在日本文化裡有錢的意思。");
                break;
            case 1:
                a3.setText("不喜歡獨自一個人，熱血，並熱愛籃球。籃球裡裁判比出三分球所用的手勢。");
                break;
            case 2:
                a3.setText("很喜歡看電影，偶像是休傑克曼。只有真的很喜歡金鋼狼的人才會這樣比。");
                break;
            case 3:
                a3.setText("中二屁孩，喜歡挑釁別人。用這三隻手指比出三十分的不禮貌。");
                break;
        }

        switch (psyanswer[3]){
            case 0:
                a4.setText("過動，但注重團隊合作。棒球本身就是一種團隊合作的運動，而棒球帽正是本運動的代表。");
                break;
            case 1:
                a4.setText("注重外表，喜歡成為他人目光的焦點。能夠理解五分割帽與棒球帽的差別，就代表你真的很懂帽子。");
                break;
            case 2:
                a4.setText("缺乏安全感，但危機意識很強，走路常常會跌倒。安全帽能夠在你跌倒時給予你全方位的保護。");
                break;
            case 3:
                a4.setText("代表你是一個有大愛的人，十分樂於與他人分享你所擁有的人事物。:戴綠帽本身就是一種分享的行為。");
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
            intent.setClass(Main11aActivity.this,chatroom.class);
            startActivity(intent);
        }

    };

}



