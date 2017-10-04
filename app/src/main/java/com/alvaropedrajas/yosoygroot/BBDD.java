package com.alvaropedrajas.yosoygroot;

import android.provider.BaseColumns;

public class BBDD {

    private BBDD(){}

    //public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "datosPersonales";
        public static final String COLUMN_NAME_1 = "Id";
        public static final String COLUMN_NAME_2 = "Nombre";
        public static final String COLUMN_NAME_3 = "Apellido";

    //}

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BBDD.TABLE_NAME + " (" +
                    BBDD.COLUMN_NAME_1 + " INTEGER PRIMARY KEY," +
                    BBDD.COLUMN_NAME_2 + BBDD.TEXT_TYPE + BBDD.COMMA_SEP +
                    BBDD.COLUMN_NAME_3 + BBDD.TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BBDD.TABLE_NAME;
}
