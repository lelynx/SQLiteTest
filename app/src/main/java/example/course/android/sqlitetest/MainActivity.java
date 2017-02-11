package example.course.android.sqlitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SQLiteDatabase db;
    private String SQL_SELECT = "SELECT * FROM contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instanciation
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        // cette instrcution va déclencher la méthode onCreate()
        db = sqLiteHelper.getWritableDatabase();
    }

    public void insertContacts_old(View v) {
        Log.d(TAG, "insertContacts: ");
        String sql = "INSERT INTO contact(Name, Address) VALUES('Paul HOCHON', 'Paris');";
        db.execSQL(sql);
        // lire le contenu de la table
        Cursor query = db.rawQuery(SQL_SELECT, null);
        if (query.moveToFirst()) {
            int uid = query.getInt(0);
            String name = query.getString(1);
            String address = query.getString(2);
            String msg = "Nom: " + name + " Address: " + address;
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void insertContacts(View v) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", "Jack London");
        contentValues.put("Address", "London");
        long id =db.insert("contact", null, contentValues);
        String msg = "Identifiant: " + id;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showContacts(View v) {
        Cursor query = db.rawQuery(SQL_SELECT, null);
        if (query.moveToFirst()) {
            int uid = query.getInt(0);
            String name = query.getString(1);
            String address = query.getString(2);
            String msg = "Nom: " + name + " Address: " + address;
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }


}
