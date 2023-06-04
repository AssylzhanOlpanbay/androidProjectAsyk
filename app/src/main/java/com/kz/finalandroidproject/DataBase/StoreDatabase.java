package com.kz.finalandroidproject.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDatabase extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "quiz.db";
    static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_HOBBY = "hobby";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_INSTAGRAM = "instagram";
    public static final String COLUMN_TELEGRAM = "telegram";
    public static final String COLUMN_TWITTER = "twitter";
    public static final String COLUMN_PASSWORD = "password";

    Context context;

    public StoreDatabase (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME + "("+
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_HOBBY + " TEXT, " +
                COLUMN_BIRTHDAY + " TEXT, " +
                COLUMN_INSTAGRAM + " TEXT, " +
                COLUMN_TELEGRAM + " TEXT, " +
                COLUMN_TWITTER + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)");

        /*

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "Ata");
        values.put(COLUMN_PHONE,"87771112233");
        values.put(COLUMN_EMAIL, "ata@gmail.com");
        values.put(COLUMN_PASSWORD, "123123");

        sqLiteDatabase.insert(TABLE_NAME, null, values);

         */

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
