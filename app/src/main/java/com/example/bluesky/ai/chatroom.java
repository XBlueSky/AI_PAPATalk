package com.example.bluesky.ai;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.example.bluesky.ai.ChatEntity;
import android.widget.BaseAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import com.squareup.okhttp.OkHttpClient;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;




public class chatroom extends AppCompatActivity {
    private Button sendButton = null;
    private EditText contentEditText = null;
    private ListView chatListView = null;
    private List<ChatEntity> chatList = null;
    private ChatAdapter chatAdapter = null;
    private int age, height;
    public boolean genderflag=false;
    public boolean gender=false;
    public int gendercount=0;
    public int years=0;
    public int giftcount = 0;
    public boolean byeflag = false;
    public boolean likefalg = false;
    public boolean azureflag = false;

    /**
     * Mobile Service Client reference
     */
    private MobileServiceClient mClient;

    /**
     * Mobile Service Table used to access data
     */
    private MobileServiceTable<ToDoItem> mToDoTable;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    Calendar mCal = Calendar.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chatroom);



        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        age = bundle.getInt("age");
        height=bundle.getInt("height");
        contentEditText = (EditText) this.findViewById(R.id.et_content);
        sendButton = (Button) this.findViewById(R.id.btn_send);
        chatListView = (ListView) this.findViewById(R.id.listview);


        chatList = new ArrayList<ChatEntity>();
        ChatEntity chatEntity = null;
        chatEntity = new ChatEntity();
        chatEntity.setComeMsg(true);
        chatEntity.setContent("Hello,nice to meet you!\n我有問題想問你~");
        mCal = Calendar.getInstance();
        chatEntity.setChatTime(DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime()).toString());

        chatList.add(chatEntity);

        chatEntity = new ChatEntity();
        chatEntity.setComeMsg(true);
        chatEntity.setContent("你的目標PAPA已了解許多\n現在來談談你自己吧!\n你心裡性別是??");
        mCal = Calendar.getInstance();
        chatEntity.setChatTime(DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime()).toString());

        chatList.add(chatEntity);


        chatAdapter = new ChatAdapter(this, chatList);
        chatListView.setAdapter(chatAdapter);
        sendButton.setOnClickListener(new View.OnClickListener() {
            String[][] chatBot={
                    //standard greetings
                    {"hi","hello","hola","ola","howdy"},
                    {"hi","hello","hey"},
                    //question greetings
                    {"how are you","how r you","how r u","how are u"},
                    {"good","doing well"},
                    //yes
                    {"yes"},
                    {"no","NO","NO!!!!!!!"},
                    //default
                    {"shut up","you're bad","noob","stop talking",
                            "(michael is unavailable, due to LOL)"}
            };
            @Override
            public void onClick(View v) {
                if (!contentEditText.getText().toString().equals("")) {
                    //发送消息
                    send();
                    String rebound =think(contentEditText.getText().toString());
                    try {
                        recall(rebound,contentEditText.getText().toString());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    if(byeflag) {
                        Intent intent = new Intent();
                        intent.setClass(chatroom.this,MainActivity.class);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(chatroom.this, "Content is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void send() {
        ChatEntity chatEntity = new ChatEntity();
        mCal = Calendar.getInstance();
        chatEntity.setChatTime(DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime()).toString());
        chatEntity.setContent(contentEditText.getText().toString());
        chatEntity.setComeMsg(false);
        chatList.add(chatEntity);
        chatAdapter.notifyDataSetChanged();
        chatListView.setSelection(chatList.size() - 1);
    }
    private String think(String rebound) {

        if ((rebound.contains("女") == true || rebound.contains("girl") == true || rebound.contains("woman") == true)&& genderflag == false) {
            genderflag = true;
            gender = false;
            gendercount++;
            return "妹子，幾歲?";
        } else if ((rebound.contains("男") == true || rebound.contains("boy") == true || rebound.contains("man") == true)&& genderflag == false) {
            genderflag = true;
            gender = true;
            gendercount++;
            return "帥哥，多大?(年齡)";
        }

        if (gendercount > 0) {
            years = Integer.parseInt(rebound);
            gendercount--;
            if(years>30)
                return "喔~"+rebound+"歲"+"感覺現在才來找我有點趕阿@@";
            else if(years>15)
                return "喔~"+rebound+"歲"+"加油!想問什麼呢?";
            else
                return "喔~"+rebound+"歲"+"你還是去讀書吧!別玩糞game了!";
        }
        if (rebound.contains("哪") == true|| rebound.contains("where") == true || rebound.contains("何處") == true) {
            return "我是小金門";
        }
        if (rebound.contains("屁") == true|| rebound.contains("幹") == true || rebound.contains("ass") == true || rebound.contains("fuck") == true || rebound.contains("靠") == true || rebound.contains("shit") == true||rebound.contains("哭爸") == true) {
            return rebound;
        }
        if (rebound.contains("不要") == true|| rebound.contains("明明") == true || rebound.contains("就你") == true || rebound.contains("還說") == true) {
            return "我沒有";
        }
        if (rebound.contains("生氣") == true|| rebound.contains("不爽") == true || rebound.contains("很煩") == true || rebound.contains("討厭") == true) {
            return "別為這種事不爽";
        }
        if ((rebound.contains("送禮物") == true|| rebound.contains("送什麼禮物") == true ||rebound.contains("gift") == true||rebound.contains("送什麼") == true)&& giftcount ==0) {
            giftcount ++;
            return "心意最重要，但是不要讓他覺得很尷尬";
        }
        if ((rebound.contains("送禮物") == true|| rebound.contains("送什麼禮物") == true ||rebound.contains("gift") == true||rebound.contains("送什麼") == true )&& giftcount >0) {
            giftcount ++;
            return "親自做簡單的卡片，非常的有心意";
        }
        if(rebound.contains("想認識") == true|| rebound.contains("當朋友") == true||rebound.contains("想跟她認識") == true||rebound.contains("想跟他認識") == true){
            if (gender == false){
                return "別怕，去跟他說話就好了";
            }
            else if(gender == true){
                return "先認識看看她的朋友";
            }
        }
        if(rebound.contains("喜歡") == true|| rebound.contains("愛") == true||rebound.contains("love")==true||rebound.contains("like")==true||likefalg == true) {
            likefalg = false;
            if (rebound.contains("吃") == true || rebound.contains("eat") == true) {
                if (rebound.contains("辣") == true || rebound.contains("spicy") == true) {
                    return "喜歡吃辣的人個性通常比較大膽，外向，可以很容易跟他當朋友";
                } else if (rebound.contains("甜")==true || rebound.contains("sweet")==true) {
                    return "喜歡吃甜食的人，性格通常比較溫和，崇尚美好的生活，想認識他送他甜食就好";
                } else if (rebound.contains("苦")==true || rebound.contains("bitter")) {
                    return "根據英國研究，喜歡吃苦的人，通常跟自戀以及虐待甚至是精神病有著很大的關聯，你確定要認識他?";
                } else if (rebound.contains("酸")==true || rebound.contains("sour")) {
                    return "我從百度上看的，喜歡吃酸的人，個性比較孤僻，但是有很大的事業心";
                } else if (rebound.contains("屎")==true||rebound.contains("shit")==true||rebound.contains("poo")==true) {
                    return "你想認識的人是狗?狗才改不了吃屎";
                } else {
                    return "不管他喜歡吃什麼，你跟著吃，就能更認識他了";
                }
            }
            else if (rebound.contains("辣") == true || rebound.contains("spicy") == true) {
                return "喜歡吃辣的人個性通常比較大膽，外向，可以很容易跟他當朋友";
            } else if (rebound.contains("甜")==true || rebound.contains("sweet")==true) {
                return "喜歡吃甜食的人，性格通常比較溫和，崇尚美好的生活，想認識他送他甜食就好";
            } else if (rebound.contains("苦")==true || rebound.contains("bitter")) {
                return "根據英國研究，喜歡吃苦的人，通常跟自戀以及虐待甚至是精神病有著很大的關聯，你確定要認識他?";
            } else if (rebound.contains("酸")==true || rebound.contains("sour")) {
                return "我從百度上看的，喜歡吃酸的人，個性比較孤僻，但是有很大的事業心";
            } else if (rebound.contains("屎")==true||rebound.contains("shit")==true||rebound.contains("poo")==true) {
                return "你想認識的人是狗?狗才改不了吃屎";
            }
            else if(rebound.contains("運動")||rebound.contains("打球")||rebound.contains("慢跑")||rebound.contains("跑步")) {
                return "運動好，才不容易生病，跟著運動就對了";
            }
            else if(rebound.contains("什麼")||rebound.contains("what")) {
                if (rebound.contains("she") || rebound.contains("她")) {
                    return "女生通常喜歡演藝圈的事情，以及流行服飾";
                } else if (rebound.contains("he") || rebound.contains("他")) {
                    return "男生不免是運動，跟電玩，以台灣男生來說，絕大部分的男生都知道NBA跟英雄聯盟";
                } else {
                    return "絕大部分的人，不分男女都喜歡小動物，尤其是狗跟貓";
                }
            }
            else {
                likefalg = true;
                return "喜歡啥?";
            }
        }
        if (rebound.contains("安安") == true|| rebound.contains("你好") == true || rebound.contains("嗨") == true || rebound.contains("h") == true || rebound.contains("哈囉") == true) {
            return "哈囉 魯蛇";
        }
        if(rebound.contains("祝我好運") == true|| rebound.contains("給我勇氣") == true){
            return "加油你行的";
        }
        if(rebound.contains("是喔") == true|| rebound.contains("嗯") == true||rebound.contains("喔") == true){
            return "知道了還想問什麼??";
        }
        if (rebound.contains("想哭") == true|| rebound.contains("QQ") == true || rebound.contains("難過") == true||rebound.contains("Qq") == true||rebound.contains("qq") == true||rebound.contains("難受") == true||rebound.contains("哭") == true) {
            return "別難過了，你不是唯一的魯蛇";
        }
        if (rebound.contains("掰掰") == true|| rebound.contains("bye") == true || rebound.contains("拜拜") == true||rebound.contains("88") == true||rebound.contains("再見") == true) {
            byeflag = true;
            return "再見，希望下次能再見到你";
        }

        azureflag = true;
        return "asshole";

    }

    private void recall(String rebound,final String text) throws MalformedURLException {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setComeMsg(true);
        chatEntity.setContent(rebound);
        mCal = Calendar.getInstance();
        chatEntity.setChatTime(DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime()).toString());
        chatList.add(chatEntity);

        mClient = new MobileServiceClient(
                "https://larrypig03.azurewebsites.net",
                this).withFilter(new ProgressFilter());

        // Extend timeout from default of 10s to 20s
        mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
            @Override
            public OkHttpClient createOkHttpClient() {
                OkHttpClient client = new OkHttpClient();
                client.setReadTimeout(20, TimeUnit.SECONDS);
                client.setWriteTimeout(20, TimeUnit.SECONDS);
                return client;
            }
        });
        // Get the Mobile Service Table instance to use
        mToDoTable = mClient.getTable(ToDoItem.class);
        // Offline Sync
        //mToDoTable = mClient.getSyncTable("ToDoItem", ToDoItem.class);
        // Load the items from the Mobile Service
        if(azureflag) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {

                    ToDoItem item = new ToDoItem();

                    item.setText(text);

                    item.setComplete(false);

                    mToDoTable.insert(item);


                }
            }, 1000);
            azureflag =false;
        }
        contentEditText.setText("");
    }

    private class ProgressFilter implements ServiceFilter {

        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback) {

            final SettableFuture<ServiceFilterResponse> resultFuture = SettableFuture.create();


            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                }
            });

            ListenableFuture<ServiceFilterResponse> future = nextServiceFilterCallback.onNext(request);

            Futures.addCallback(future, new FutureCallback<ServiceFilterResponse>() {
                @Override
                public void onFailure(Throwable e) {
                    resultFuture.setException(e);
                }

                @Override
                public void onSuccess(ServiceFilterResponse response) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                        }
                    });

                    resultFuture.set(response);
                }
            });

            return resultFuture;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "chatroom Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.bluesky.ai/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "chatroom Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.bluesky.ai/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private class ChatAdapter extends BaseAdapter {
        private Context context = null;
        private List<ChatEntity> chatList = null;
        private LayoutInflater inflater = null;
        private int COME_MSG = 0;
        private int TO_MSG = 1;

        public ChatAdapter(Context context, List<ChatEntity> chatList) {
            this.context = context;
            this.chatList = chatList;
            inflater = LayoutInflater.from(this.context);
        }

        @Override
        public int getCount() {
            return chatList.size();
        }

        @Override
        public Object getItem(int position) {
            return chatList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            // 区别两种view的类型，标注两个不同的变量来分别表示各自的类型
            ChatEntity entity = chatList.get(position);
            if (entity.isComeMsg()) {
                return COME_MSG;
            } else {
                return TO_MSG;
            }
        }

        @Override
        public int getViewTypeCount() {
            // 这个方法默认返回1，如果希望listview的item都是一样的就返回1，我们这里有两种风格，返回2
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatHolder chatHolder = null;
            if (convertView == null) {
                chatHolder = new ChatHolder();
                if (chatList.get(position).isComeMsg()) {
                    convertView = inflater.inflate(R.layout.chat_from_item, null);
                } else {
                    convertView = inflater.inflate(R.layout.chat_to_item, null);
                }
                chatHolder.timeTextView = (TextView) convertView.findViewById(R.id.tv_time);
                chatHolder.contentTextView = (TextView) convertView.findViewById(R.id.tv_content);
                chatHolder.userImageView = (ImageView) convertView.findViewById(R.id.iv_user_image);
                convertView.setTag(chatHolder);
            } else {
                chatHolder = (ChatHolder) convertView.getTag();
            }

            chatHolder.timeTextView.setText(chatList.get(position).getChatTime());
            chatHolder.contentTextView.setText(chatList.get(position).getContent());
            chatHolder.userImageView.setImageResource(chatList.get(position).getUserImage());

            return convertView;
        }

        private class ChatHolder {
            private TextView timeTextView;
            private ImageView userImageView;
            private TextView contentTextView;
        }

    }
}







