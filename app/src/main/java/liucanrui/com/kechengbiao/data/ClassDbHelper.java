package liucanrui.com.kechengbiao.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 刘灿锐 on 2017/9/12.
 */

public class ClassDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "class.db";

    private static final int DATABASE_VERSION = 1;


    public ClassDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_CLASS_TABLE =

                "CREATE TABLE " + CourseContract.ClassEntry.CLASS_TABLE_NAME + " (" +
                        CourseContract.ClassEntry._ID + " INTEGER PRIMARY KEY, " +
                        CourseContract.ClassEntry.COLUMN_CLASS_ID + " TEXT NOT NULL, " +
                        CourseContract.ClassEntry.COLUMN_CLASS_NAME + " TEXT NOT NULL," +
                        CourseContract.ClassEntry.COLUMN_SUGGEST + " TEXT NOT NULL" +
                        ");";

        sqLiteDatabase.execSQL(SQL_CREATE_CLASS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CourseContract.ClassEntry.CLASS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public Cursor getSuggestionWords(String word) {

        String selection = CourseContract.ClassEntry.COLUMN_CLASS_NAME + " LIKE '%" + word + "%' ";

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.query(
                CourseContract.ClassEntry.CLASS_TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                null);


    }

}
