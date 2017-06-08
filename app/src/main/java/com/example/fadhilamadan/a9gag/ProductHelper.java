package com.example.fadhilamadan.a9gag;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Rama Adhiguna on 6/8/2017.
 */

public class ProductHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TokoOnline.db";
    private static final String SQL_CREATE_USERNAME =
            "CREATE TABLE `Username` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `username` TEXT, `password` TEXT)";
    private static final String SQL_CREATE_COMMENT =
            "CREATE TABLE `Komen` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `username_id` INTEGER, `posting_id` INTEGER, `description` TEXT, `upvote` INTEGER )";

    private static final String SQL_DELETE_USERNAME = "DROP TABLE IF EXISTS `Product`";
    private static final String SQL_DELETE_COMMENT = "DROP TABLE IF EXISTS `Komen`";

    public ProductHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ProductHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void sqlInsertUsername(String name, String password) {
        String query = "INSERT INTO `Username`(`id`,`username`,`password`) VALUES (NULL,\"" + name + "\",\"" + password + "\");";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(query);
        } catch (Exception e) {
            Log.d("error", e.getMessage().toString());
        }
    }
    public void sqlInsertComment(int id, int posting, String komen) {
        //String query = "INSERT INTO `Komen`(`id`,`username_id`,`posting_id`,`text`,`upvote`) VALUES (NULL," + username + "," + posting + ",\"" + komen +"\",0);";
        String query = "INSERT INTO `Komen`(`id`,`username_id`,`posting_id`,`description`,`upvote`) VALUES (NULL,37,"+posting+",'"+komen+"',0);";
        //String query = "INSERT INTO `Komen`(`id`,`username_id`,`posting_id`,`description`,`upvote`) VALUES (NULL,1,1,'lol',0);";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(query);
        } catch (Exception e) {
            Log.d("error", e.getMessage().toString());
        }
    }
    public ArrayList<Username> sqlSelectUsername() {
        ArrayList<Username> prods = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT id, username, password FROM `Username` ORDER BY `id` ASC;", null);
            c.moveToFirst();
            do {
                int id = c.getInt(0);
                String usernama = c.getString(1);
                String password = c.getString(2);
                prods.add(new Username(id, usernama, password));
            } while (c.moveToNext());
            c.close();

        } catch (Exception e) {
            Log.e("error", e.getMessage().toString());
        }
        return prods;
    }
    public ArrayList<Comment> sqlSelectComment() {
        ArrayList<Comment> prods = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT id, username_id, posting_id,description,upvote FROM `Komen` ORDER BY `id` ASC;", null);
            c.moveToFirst();
            do {
                int id = c.getInt(0);
                int username_id = c.getInt(1);
                int posting_id = c.getInt(2);
                String text = c.getString(3);
                int upvote = c.getInt(4);
                prods.add(new Comment(id, username_id, posting_id,text,upvote));
            } while (c.moveToNext());
            c.close();

        } catch (Exception e) {
            Log.e("error", e.getMessage().toString());
        }
        return prods;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COMMENT);
        db.execSQL(SQL_CREATE_USERNAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USERNAME);
        db.execSQL(SQL_DELETE_COMMENT);
        onCreate(db);
    }
}
