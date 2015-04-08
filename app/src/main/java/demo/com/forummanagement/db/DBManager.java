package demo.com.forummanagement.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MS on 2015/4/5.
 */
public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
        SharedPreferences setting = context.getSharedPreferences("FIRST_TAG", 0);
        Boolean user_first = setting.getBoolean("FIRST", true);
        if (user_first) {
            initFakeData();
            setting.edit().putBoolean("FIRST", false).commit();
        }
    }

    public void addUser(User user) {
        db.execSQL("INSERT INTO users VALUES('"
                + user.getName() + "', '" + user.getGender() + "', " + user.getAge() + ", "
                + user.getLevel() + ", " + user.getCoin() + ", '" + user.getIntro() + "')");
    }

    public List<User> queryUsers(String name) {
        List<User> users = new ArrayList<>();

        Cursor cursor;
        if (name == null) {
            cursor = db.rawQuery("SELECT * FROM users", null);
        } else {
            cursor = db.rawQuery("SELECT * FROM users WHERE name like '%" + name + "%'", null);
        }

        while (cursor.moveToNext()) {
            String cName = cursor.getString(cursor.getColumnIndex("name"));
            String cGender = cursor.getString(cursor.getColumnIndex("gender"));
            int cAge = cursor.getInt(cursor.getColumnIndex("age"));
            int cLevel = cursor.getInt(cursor.getColumnIndex("level"));
            int cCoin = cursor.getInt(cursor.getColumnIndex("coin"));
            String cIntro = cursor.getString(cursor.getColumnIndex("intro"));
            users.add(new User(cName, cGender, cAge, cLevel, cCoin, cIntro));
        }
        return users;
    }

    public void deleteUser(String name) {
        db.delete("users", "name = ?", new String[]{name});
    }

    public void updateInfo(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("gender", user.getGender());
        contentValues.put("age", user.getAge());
        contentValues.put("level", user.getLevel());
        contentValues.put("coin", user.getCoin());
        contentValues.put("intro", user.getIntro());
        db.update("users", contentValues, "name = ?", new String[]{user.getName()});
    }

    public void close() {
        db.close();
    }

    private void initFakeData() {
        addUser(new User("Tom", "Male", 18, 3, 116, "ACSGSWEHDVDSRWFC"));
        addUser(new User("Peter", "Male", 15, 1, 452, "GDSCSVCSVVD"));
        addUser(new User("Toma", "Male", 30, 4, 45, "ACSGSWEHDVDSRWFC"));
        addUser(new User("Tomcc", "Female", 27, 8, 2410, "DGVDVDVF-0545-DFGT3"));
        addUser(new User("Matt", "Male", 46, 8, 1004, "540/55451DCD-"));
        addUser(new User("Lucy", "Female", 23, 1, 2, "65465415684333FFFF"));
    }

}
