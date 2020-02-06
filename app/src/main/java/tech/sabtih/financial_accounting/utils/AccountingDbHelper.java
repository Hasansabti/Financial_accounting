package tech.sabtih.financial_accounting.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class AccountingDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Accounting.db";


    private static final String SQL_CREATE_USERS =
            "CREATE TABLE " + UserEntry.TABLE_NAME.getText() + " (" +
                    UserEntry.COLUMN_ID.getText() + " TEXT PRIMARY KEY," +
                    UserEntry.COLUMN_NAME.getText() + " TEXT," +
                    UserEntry.COLUMN_PHONE.getText()+ " TEXT)";

    private static final String SQL_CREATE_CONTRACTS =
            "CREATE TABLE " + ContEntry.TABLE_NAME.getText() + " (" +
                    ContEntry.COLUMN_ID.getText() + " TEXT PRIMARY KEY," +
                    ContEntry.COLUMN_USER.getText() + " TEXT," +
                     " FOREIGN KEY ("+ContEntry.COLUMN_USER.getText()+") REFERENCES "+UserEntry.TABLE_NAME.getText()+"("+ UserEntry.COLUMN_ID.getText() +"), "+
                    ContEntry.COLUMN_DATE.getText() + " TEXT, " +
                    ContEntry.COLUMN_DET.getText() + " TEXT, " +
                    ContEntry.COLUMN_FOR.getText() + " BOOLEAN, " +
                    ContEntry.COLUMN_AMOUNT.getText()+ " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME.getText();
    private static final String SQL_DELETE_CONTRACTS =
            "DROP TABLE IF EXISTS " + ContEntry.TABLE_NAME.getText();


    public AccountingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static AccountingDbHelper newInstance(Context context){
        return new AccountingDbHelper(context);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_CONTRACTS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_CONTRACTS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
