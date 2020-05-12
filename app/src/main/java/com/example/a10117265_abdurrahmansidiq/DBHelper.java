package com.example.a10117265_abdurrahmansidiq;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "data_teman";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_KELAS = "kelas";
    public static final String COLUMN_TELEPON = "telepon";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_SOSIALMEDIA = "sosialmedia";
    private static final String db_name ="daftar_teman.db";
    private static final int db_version=1;

    private static final String db_create = "create table "
            + TABLE_NAME + "("
            + COLUMN_NIM +" integer primary key not null, "
            + COLUMN_NAMA+ " varchar(50) not null, "
            + COLUMN_KELAS+ " varchar(20) not null, "
            + COLUMN_TELEPON+ " varchar(12) not null, "
            + COLUMN_EMAIL+ " varchar(50) not null, "
            + COLUMN_SOSIALMEDIA+ " varchar(50) not null);";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
        // Auto generated
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
//11-05-2020,10117265,AbdurrahmanSidiq,IF-8