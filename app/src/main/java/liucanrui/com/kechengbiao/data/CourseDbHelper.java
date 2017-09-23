package liucanrui.com.kechengbiao.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 刘灿锐 on 2017/9/19.
 */

public class CourseDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "course.db";

    private static final int DATABASE_VERSION = 1;

    public CourseDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_CLASS_TABLE =
                "CREATE TABLE " + CourseContract.CourseEntry.COURSE_TABLE_NAME + " (" +
                        CourseContract.CourseEntry._ID + " INTEGER PRIMARY KEY, " +
                        CourseContract.CourseEntry.COLUMN_CLASS_ID + " TEXT, " +
                        CourseContract.CourseEntry.COLUMN_C_NAME + " TEXT, " +
                        CourseContract.CourseEntry.COLUMN_CLASS_NAME + " TEXT, " +
                        CourseContract.CourseEntry.COLUMN_C_METHOD + " TEXT, " +
                        CourseContract.CourseEntry.COLUMN_C_TEACHER_NAME + " TEXT, " +
                        CourseContract.CourseEntry.COLUMN_C_NUM + " INTEGER, " +

                        CourseContract.CourseEntry.COLUMN_C_WEEK_NUM + " TEXT, " +
                        CourseContract.CourseEntry.COLUMN_C_WEEK + " INTEGER, " +
                        CourseContract.CourseEntry.COLUMN_C_SESSION + " TEXT, " +
                        CourseContract.CourseEntry.COLUMN_C_S_OR_D + " INTEGER, " +

                        CourseContract.CourseEntry.COLUMN_C_ADDRESS + " TEXT" +
                        ");";

        sqLiteDatabase.execSQL(SQL_CREATE_CLASS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CourseContract.CourseEntry.COURSE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
