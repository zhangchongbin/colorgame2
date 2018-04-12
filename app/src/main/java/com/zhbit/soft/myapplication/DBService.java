package com.zhbit.soft.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by JasonBin on 2017/5/11.
 */

public class DBService extends SQLiteOpenHelper {
    private Context mContext;
    public DBService(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table score ("
                + "id integer primary key AUTOINCREMENT,"
                + "name text,"
                + "score integer,"
                + "date text);";
        db.execSQL(sql);
        Toast.makeText(mContext,"OK",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists score");
        onCreate(db);
    }
    public Cursor query(String sql) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
}
