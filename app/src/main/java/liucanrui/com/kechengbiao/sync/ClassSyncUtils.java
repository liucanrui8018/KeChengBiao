package liucanrui.com.kechengbiao.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import liucanrui.com.kechengbiao.WelcomeActivity;
import liucanrui.com.kechengbiao.data.CourseContract;
import liucanrui.com.kechengbiao.utilities.CourseJsonUtils;
import liucanrui.com.kechengbiao.utilities.NetworkUtils;

/**
 * Created by 刘灿锐 on 2017/9/22.
 */

public class ClassSyncUtils {

    private static boolean sInitialized;

    synchronized public static void initialize(@NonNull final Context context, final WelcomeActivity activity) {


        if (sInitialized) return;

        sInitialized = true;

        activity.show();

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {

                Uri forecastQueryUri = CourseContract.ClassEntry.CONTENT_CLASS_URI;

                Cursor cursor = context.getContentResolver().query(
                        forecastQueryUri,
                        null,
                        null,
                        null,
                        null);

                if (null == cursor || cursor.getCount() == 0) {
//                    startImmediateSync(context);
                    try {

                        String jsonWeatherResponse = NetworkUtils.getClassResponse();


                        ContentValues[] classValues = CourseJsonUtils.getClassContentValuesFromJson(jsonWeatherResponse);

                        Log.d(ClassSyncUtils.class.getSimpleName(), classValues.length + "");


                        if (classValues.length != 0) {
                /* Get a handle on the ContentResolver to delete and insert data */
                            ContentResolver contentResolver = context.getContentResolver();

                /* Delete old weather data because we don't need to keep multiple days' data */
                            contentResolver.delete(
                                    CourseContract.ClassEntry.CONTENT_CLASS_URI,
                                    null,
                                    null);

                /* Insert our new weather data into Sunshine's ContentProvider */
                            contentResolver.bulkInsert(
                                    CourseContract.ClassEntry.CONTENT_CLASS_URI,
                                    classValues);

                        }


                    } catch (Exception e) {
            /* Server probably invalid */
                        e.printStackTrace();
                    }

                }

                assert cursor != null;
                cursor.close();

                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {

                activity.cancel();


            }
        }.execute();

    }
}
