package com.zhbit.soft.myapplication;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreShow extends AppCompatActivity implements View.OnClickListener{
    private DBService dbHelper;
    EditText editText;
    ListView listView;
    SimpleAdapter adapter;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    int signal;
    String score,name,date;
    LayoutInflater factory;
    View myView;
    private SoundPool soundPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_show);
        Button Back = (Button) this.findViewById(R.id.back);
        soundPool=new SoundPool(1, AudioManager.STREAM_SYSTEM,5);
        soundPool.load(this,R.raw.click1,5);
        Back.setOnClickListener(this);

        dbHelper = new DBService(this, "Score.db", null, 1);
        dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.query("select * from score order by score DESC");
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int score = cursor.getInt(cursor.getColumnIndex("score"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", name);
                map.put("score", score);
                map.put("date", date);
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter = new SimpleAdapter(this, list, R.layout.score, new String[]{"name", "score", "date"}, new int[]{R.id.textname, R.id.textscore, R.id.textdate});
        listView = (ListView) this.findViewById(R.id.ListView);
        listView.setAdapter(adapter);
        factory = LayoutInflater.from(this);
        myView = factory.inflate(R.layout.ediname, null);
        editText = (EditText) myView.findViewById(R.id.editText);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        signal = bundle.getInt("signal");
        switch (signal) {
            case 0:
                show();
                break;
            case 1:
                break;
        }
    }

    public void show() {
        new AlertDialog.Builder(this,R.style.DialogStyle).setView(myView).setPositiveButton("确定录入", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                soundPool.play(1,1,1,0,0,1);
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                score = bundle.getString("score");
                date = bundle.getString("date");
                name = editText.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", name);
                map.put("score", score);
                map.put("date", date);
                list.add(map);
                values.put("name", name);
                values.put("score", score);
                values.put("date", date);
                db.insert("score", null, values);
                Intent intent1=new Intent(ScoreShow.this,ScoreShow.class);
                intent1.putExtra("signal",1);
                finish();
                startActivity(intent1);
            }
        }).setNegativeButton("取消录入",null).show().setCancelable(false);

    }


    @Override
    public void onClick(View v) {
        soundPool.play(1,1,1,0,0,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from score where score=0;");
        Intent i=new Intent(ScoreShow.this,Login.class);
        startActivity(i);
        this.finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ScoreShow.this,musicService.class);
        stopService(intent);
    }
}
