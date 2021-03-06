package com.alvaropedrajas.yosoygroot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BBDD_Helper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "datos_Trabajadores.db";
    private static final int DATABASE_VERSION = 1;

    public BBDD_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BBDD.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BBDD.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
