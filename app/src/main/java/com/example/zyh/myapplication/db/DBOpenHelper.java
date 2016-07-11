package com.example.zyh.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper{

	public DBOpenHelper(Context context, String name, CursorFactory factory,
						int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String articleSql = "create table if not exists arttb(_id integer primary key,content text not null)";
		db.execSQL(articleSql);
		String picSql = "create table if not exists pictb(_id integer primary key,content text not null)";
		db.execSQL(picSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	
}
