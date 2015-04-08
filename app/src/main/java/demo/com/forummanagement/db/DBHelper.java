package demo.com.forummanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MS on 2015/4/5.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "forum.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists users" +
                "(name varchar primary key,gender varchar,age integer,level integer,coin integer, intro varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nothing to do because this is a demo ~ :)
    }
}
