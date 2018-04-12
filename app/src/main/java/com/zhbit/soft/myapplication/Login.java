package com.zhbit.soft.myapplication;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private SoundPool soundPool;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button star = (Button) this.findViewById(R.id.star);
        Button scoreshow2 = (Button) this.findViewById(R.id.scoreshow2);
        Button exit = (Button) this.findViewById(R.id.exit);
        star.setOnClickListener(this);
        scoreshow2.setOnClickListener(this);
        exit.setOnClickListener(this);
        imageView = (ImageView) this.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.t_animationlist);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
        soundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
        soundPool.load(this, R.raw.click1, 1);
        Intent intent = new Intent(Login.this, musicService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.star:
                soundPool.play(1, 1, 1, 0, 0, 1);
                Intent m = new Intent(this, MainActivity.class);
                startActivity(m);
                finish();
                break;
            case R.id.scoreshow2:
                soundPool.play(1, 1, 1, 0, 0, 1);
                Intent s = new Intent(this, ScoreShow.class);
                s.putExtra("signal", 1);
                startActivity(s);
                finish();
                break;
            case R.id.exit:
                soundPool.play(1, 1, 1, 0, 0, 1);
                Intent intent=new Intent(Login.this,musicService.class);
                stopService(intent);
                System.exit(0);
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Login.this,musicService.class);
        stopService(intent);
    }
}

    ;
