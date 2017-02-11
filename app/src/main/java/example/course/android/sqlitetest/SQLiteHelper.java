package example.course.android.sqlitetest;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLiteHelper";
    private static final String DB_NAME = "contacts.db";
    private static final String TABLE_NAME = "contact";
    private static final String UID = "_id";
    private static final String NAME = "Name";
    private static final String ADDRESS = "Address";

    private static final int DB_VERSION = 4;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "  (" +
            UID + " integer primary key autoincrement, " +
            NAME + " varchar(255), " +
            ADDRESS + " varchar(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(TAG, "CTor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Log.d(TAG, "onCreate called!");
        } catch (SQLException e) {
            Log.d(TAG, "onCreate Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Log.d(TAG, "onUpgrade called!");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            Log.d(TAG, "onUpgrade Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
