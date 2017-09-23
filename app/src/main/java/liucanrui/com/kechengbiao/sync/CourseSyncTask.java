package liucanrui.com.kechengbiao.sync;



import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.text.format.DateUtils;

import org.ksoap2.serialization.SoapObject;

import java.net.URL;

import liucanrui.com.kechengbiao.data.ConnectService;
import liucanrui.com.kechengbiao.data.CourseContract;
import liucanrui.com.kechengbiao.utilities.CourseJsonUtils;
import liucanrui.com.kechengbiao.utilities.NetworkUtils;


public class CourseSyncTask {


    synchronized public static void syncCourse(Context context) {


        try {

            String jsonWeatherResponse = NetworkUtils.getClassResponse();

            ContentValues[] classValues = CourseJsonUtils.getClassContentValuesFromJson(jsonWeatherResponse);


            if (classValues != null && classValues.length != 0) {
                /* Get a handle on the ContentResolver to delete and insert data */
                ContentResolver ContentResolver = context.getContentResolver();

                /* Delete old weather data because we don't need to keep multiple days' data */
                ContentResolver.delete(
                        CourseContract.ClassEntry.CONTENT_CLASS_URI,
                        null,
                        null);

                /* Insert our new weather data into Sunshine's ContentProvider */
               ContentResolver.bulkInsert(
                        CourseContract.ClassEntry.CONTENT_CLASS_URI,
                        classValues);

            }

        } catch (Exception e) {
            /* Server probably invalid */
            e.printStackTrace();
        }


    }

}