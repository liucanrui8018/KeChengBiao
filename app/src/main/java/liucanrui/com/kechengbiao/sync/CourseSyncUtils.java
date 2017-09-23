package liucanrui.com.kechengbiao.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import liucanrui.com.kechengbiao.MainActivity;
import liucanrui.com.kechengbiao.data.CourseContract;
import liucanrui.com.kechengbiao.utilities.CourseJsonUtils;
import liucanrui.com.kechengbiao.utilities.NetworkUtils;

/**
 * Created by 刘灿锐 on 2017/9/12.
 */

public class CourseSyncUtils {

    private static boolean sInitialized;

    private static String Name;

    private static final int ID_FORECAST_LOADER = 44;


    synchronized public static void initialize(@NonNull final Context context, final MainActivity activity, final String className) {


        if (sInitialized) return;

        sInitialized = true;

        Name = className;


        activity.show();

        new AsyncTask<Void, Void, Cursor>() {

            @Override
            protected Cursor doInBackground(Void... voids) {

                Uri forecastQueryUri = CourseContract.CourseEntry.CONTENT_COURSE_URI;

                Cursor cursor = context.getContentResolver().query(
                        forecastQueryUri,
                        null,
                        null,
                        null,
                        null);

                if (null == cursor || cursor.getCount() == 0 || !Name.equals(className)) {

                    try {

                        String jsonWeatherResponse = NetworkUtils.getCourseResponse("'" + className + "'");

                        Log.d(CourseSyncUtils.class.getSimpleName(), jsonWeatherResponse);


                        ContentValues[] classValues = CourseJsonUtils.getCourseContentValuesFromJson(jsonWeatherResponse);


                        if (classValues.length != 0) {
                /* Get a handle on the ContentResolver to delete and insert data */
                            ContentResolver contentResolver = context.getContentResolver();

                /* Delete old weather data because we don't need to keep multiple days' data */
                            contentResolver.delete(
                                    CourseContract.CourseEntry.CONTENT_COURSE_URI,
                                    null,
                                    null);

                /* Insert our new weather data into Sunshine's ContentProvider */
                            contentResolver.bulkInsert(
                                    CourseContract.CourseEntry.CONTENT_COURSE_URI,
                                    classValues);

                        }
                        Log.d(CourseSyncUtils.class.getSimpleName(), Name + "数据库创建成功");


                    } catch (Exception e) {
            /* Server probably invalid */
                        e.printStackTrace();
                    }


                }


                assert cursor != null;
                cursor.close();

                return null;
            }


            @Override
            protected void onPostExecute(Cursor aBoolean) {

                activity.cancel();

                activity.getSupportLoaderManager().initLoader(ID_FORECAST_LOADER, null, activity);
            }
        }.execute();

    }


}
