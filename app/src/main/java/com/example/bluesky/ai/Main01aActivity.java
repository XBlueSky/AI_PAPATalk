package com.example.bluesky.ai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main01aActivity extends AppCompatActivity {

    private int age, height;
    private int[] psyanswer;
    private TextView a1,a2,a3,a4;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01a);

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
                a1.setText("這種人通常不在意外表，喜歡自由自在的生活，不希望拘束自己。");
                break;
            case 1:
                a1.setText("這種人會重視自己的儀容，會挑選適合自己的衣服，平常的外觀應該也是乾淨整潔的面容。");
                break;
            case 2:
                a1.setText("這種人不是個怪咖，就是就是對自己的身材超有自信，是個非常有自信的人，可能時不時就喜歡展現他的肌肉。");
                break;
            case 3:
                a1.setText("如果現在不在海灘，他可能有對你很大程度上的信任，又或者他只是個不穿衣服的暴露狂。");
                break;
        }
        switch (psyanswer[1]){
            case 0:
                a2.setText("狗非常喜歡黏人，也需要外出活動，所以喜歡狗的人通常較外向，已具備具撒嬌心理，親切體貼。");
                break;
            case 1:
                a2.setText("貓本身是個非常獨立的動物，也古怪難搞。喜歡貓的人，通常甘願為貓做事，行事上也是跟貓一樣非常獨立、慵懶。也有可能是被虐狂。");
                break;
            case 2:
                a2.setText("喜歡做研究，特別喜歡大自然，有可能只是不想花太多時間養貓狗，只是想滿足餵食的興趣。");
                break;
            case 3:
                a2.setText("別與他深交，智商有問題，花草不是動物!!!");
                break;
        }

        switch (psyanswer[2]){
            case 0:
                a3.setText("沒創意，通常很少人會喜歡如此普通的水果，喜歡此類的水果代表是 個普通沒特別想法的人。");
                break;
            case 1:
                a3.setText("大懶人，不需要剝皮以及吐籽，不是懶鬼是什麼。");
                break;
            case 2:
                a3.setText("不受外人眼光影響，特愛此臭味極濃的水果，此人通常很古怪，不會跟著潮流走，有著獨特的眼光。");
                break;
            case 3:
                a3.setText("此類的人即容易有便秘，身體健康也可能有問題，請多多關心他。");
                break;
        }

        switch (psyanswer[3]){
            case 0:
                a4.setText("熱愛籃球的熱血份子，平常穿球鞋，就是為了有球場時就可以去報隊打球，求不一定打得好，但有看NBA的機率高達87%。");
                break;
            case 1:
                a4.setText("顯示自己尊榮不凡的社會地位，有愛炫耀的傾向，你不知道他穿的鞋子的名子還會被恥笑。");
                break;
            case 2:
                a4.setText("這個人十分拘謹，連平常都要打扮成跟上班一樣，有過於固執老實地傾向。");
                break;
            case 3:
                a4.setText("這種人比穿拖鞋更不會理會其他人的想法，最重要的是，他擁有一般人不會有的超後腳皮。");
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
            intent.setClass(Main01aActivity.this,chatroom.class);
            startActivity(intent);
        }

    };

}



