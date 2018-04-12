package com.zhbit.soft.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView word;
    TextView time;
    TextView score;
    ImageView life1;
    ImageView life2;
    ImageView life3;
    TranslateAnimation tAnim;
    TranslateAnimation tAnim2;
    RotateAnimation rAnima;
    RotateAnimation rAnima2;
    ScaleAnimation sAnima;
    ScaleAnimation sAnima2;
    AlphaAnimation aAnima;
    AlphaAnimation aAnima2;
    private SoundPool soundPool;
    int i;
    int score1;
    int falsecount=0;//记录错误次数
    private static String[] data= new String[]{"黄", "蓝", "红", "紫", "黑", "绿", "白", "橙", "粉"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button yellow=(Button) this.findViewById(R.id.btnyellow);
        yellow.setOnClickListener(this);
        Button blue=(Button) this.findViewById(R.id.btnblue);
        blue.setOnClickListener(this);
        Button red=(Button) this.findViewById(R.id.btnred);
        red.setOnClickListener(this);
        Button purple=(Button) this.findViewById(R.id.btnpurple);
        purple.setOnClickListener(this);
        Button black=(Button) this.findViewById(R.id.btnblack);
        black.setOnClickListener(this);
        Button green=(Button) this.findViewById(R.id.btngreen);
        green.setOnClickListener(this);
        Button white=(Button) this.findViewById(R.id.btnwhite);
        white.setOnClickListener(this);
        Button orange=(Button) this.findViewById(R.id.btnorange);
        orange.setOnClickListener(this);
        Button pink=(Button) this.findViewById(R.id.btnpink);
        pink.setOnClickListener(this);
        life1=(ImageView) this.findViewById(R.id.life1);
        life2=(ImageView) this.findViewById(R.id.life2);
        life3=(ImageView) this.findViewById(R.id.life3);
        word=(TextView) this.findViewById(R.id.textView);
        time=(TextView) this.findViewById(R.id.time);
        score=(TextView) this.findViewById(R.id.score) ;
        soundPool=new SoundPool(10, AudioManager.STREAM_SYSTEM,5);
        soundPool.load(this,R.raw.sure,1);
        soundPool.load(this,R.raw.bingo,2);
        soundPool.load(this,R.raw.false1,3);
        tAnim = new TranslateAnimation(-600, 600, 0,0);tAnim.setDuration(1000);
        tAnim2 = new TranslateAnimation(0, 0, 600,-600);tAnim2.setDuration(1000);
        rAnima = new RotateAnimation(0,360); rAnima.setDuration(1000);
        rAnima2 = new RotateAnimation(360,0); rAnima2.setDuration(1000);
        sAnima = new ScaleAnimation(2, 0, 2, 0);sAnima.setDuration(1000);
        sAnima2 = new ScaleAnimation(0, 2, 0, 2);sAnima2.setDuration(1000);
        aAnima = new AlphaAnimation(1.0f, 0.0f);aAnima.setDuration(1000);
        aAnima2 = new AlphaAnimation(0.0f, 1.0f);aAnima2.setDuration(1000);
        change();
        changecolor();
    }
    /**
     * 取消倒计时
     */
    public void oncancel() {
        timer.cancel();
    }
    /**
     * 开始倒计时
     */
    public void restart() {
        timer.start();
    }

    private CountDownTimer timer = new CountDownTimer(3000, 1000){

        @Override
        public void onTick(long millisUntilFinished) {
            time.setText( (millisUntilFinished / 1000)-1+"");
        }

        @Override
        public void onFinish() {
            setFalsecount();
        }
    };
    public void change()
    {
        int randomNum = new Random().nextInt(data.length);
        word.setText(data[randomNum]);
        style();
        changecolor();
        restart();
    }
    public void style(){
        int randomNum =new Random().nextInt(8);
        switch (randomNum){
            case 0: word.startAnimation(tAnim);break;
            case 1: word.startAnimation(tAnim2);break;
            case 2: word.startAnimation(rAnima);break;
            case 3: word.startAnimation(rAnima2);break;
            case 4:word.startAnimation(sAnima);break;
            case 5:word.startAnimation(sAnima2);break;
            case 6:word.startAnimation(aAnima);break;
            case 7:word.startAnimation(aAnima2);break;
            default:break;
        }
    }
    public void changecolor(){
        int randomNum =new Random().nextInt(data.length);
        switch (randomNum){
            case 0:word.setTextColor(getResources().getColor(R.color.colorYellow));seti(0);break;
            case 1:word.setTextColor(getResources().getColor(R.color.colorBlue));seti(1);break;
            case 2:word.setTextColor(getResources().getColor(R.color.colorRed));seti(2);break;
            case 3:word.setTextColor(getResources().getColor(R.color.colorPurple));seti(3);break;
            case 4:word.setTextColor(getResources().getColor(R.color.colorBlack));seti(4);break;
            case 5:word.setTextColor(getResources().getColor(R.color.colorGreen));seti(5);break;
            case 6:word.setTextColor(getResources().getColor(R.color.colorWhite));seti(6);break;
            case 7:word.setTextColor(getResources().getColor(R.color.colorOrange));seti(7);break;
            case 8:word.setTextColor(getResources().getColor(R.color.colorPink));seti(8);break;
            default:break;
        }
    }
    public void seti(int i){
        this.i=i;
    }
    public void setFalsecount(){
        falsecount++;
        change();
            switch (falsecount){
                case 1:life3.setVisibility(View.INVISIBLE);soundPool.play(3,1,1,0,0,1);restart();break;
                case 2:life2.setVisibility(View.INVISIBLE);soundPool.play(3,1,1,0,0,1);restart();break;
                case 3:life1.setVisibility(View.INVISIBLE);oncancel();gameover();break;
                default:oncancel();break;
            }
    }
    public void star(){
        falsecount=0;
        life3.setVisibility(View.VISIBLE);
        life2.setVisibility(View.VISIBLE);
        life1.setVisibility(View.VISIBLE);
        score1=0;
        score.setText("积分:"+score1);
        change();
        restart();
    }
    //积分函数
    public void setScore(){
        soundPool.play(2,1,1,0,0,1);
        score1++;
        score.setText("积分:"+score1);
    }
    public void gameover(){
        soundPool.play(1,1,1,0,0,1);
        timer.cancel();
        new AlertDialog.Builder(MainActivity.this).setTitle("游戏结束").setMessage("你怕是真的色盲哦！要重来吗？").setPositiveButton("玩玩玩玩", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                star();
            }
        }).setNegativeButton("老子输了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent I = new Intent(MainActivity.this,ScoreShow.class);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String date=sdf.format(new java.util.Date());
                I.putExtra("score",score1+"");
                I.putExtra("date",date);
                I.putExtra("signal",0);
                startActivity(I);
                finish();
            }
        }).show().setCancelable(false);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnyellow:
                if(i==0)
                {
                    setScore();
                }else{

                    setFalsecount();
                }
                change();
                break;
            case R.id.btnblue:
                if(i==1)
                {
                    setScore();
                }else{

                    setFalsecount();
                }
                change();
                break;
            case R.id.btnred:
                if(i==2)
                {
                    setScore();
                }else{

                    setFalsecount();
                }
                change();
                break;
            case R.id.btnpurple:
                if(i==3)
                {
                    setScore();
                }else{

                    setFalsecount();
                }
                change();
                break;
            case R.id.btnblack:
                if(i==4)
                {
                    setScore();
                }else{

                    setFalsecount();
                }
                change();
                break;
            case R.id.btngreen:
                if(i==5)
                {
                    setScore();
                }else{

                    setFalsecount();
                }
                change();
                break;
            case R.id.btnwhite:
                if(i==6)
                {
                    setScore();
                }else{

                    setFalsecount();
                }
                change();
                break;
            case R.id.btnorange:
                if(i==7)
                {
                    setScore();
                }else{

                    setFalsecount();
                }
                change();
                break;
            case R.id.btnpink:
                if(i==8)
                {
                    setScore();
                }else{

                    setFalsecount();
                }
                change();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MainActivity.this,musicService.class);
        stopService(intent);
        oncancel();
    }
}
