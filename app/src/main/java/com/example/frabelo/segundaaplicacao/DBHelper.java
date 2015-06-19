package com.example.frabelo.segundaaplicacao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FRabelo on 19/06/2015.
 */

/**
 * Created by Tan on 2/5/2015.
 */
public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "spinner.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Student.TABLE  + "("
                + Student.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Student.KEY_name + " TEXT, "
                + Student.KEY_age + " INTEGER, "
                + Student.KEY_email + " TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE);

        // Create tables again
        onCreate(db);

    }
}
