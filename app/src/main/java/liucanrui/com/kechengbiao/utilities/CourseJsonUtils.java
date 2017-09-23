package liucanrui.com.kechengbiao.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import liucanrui.com.kechengbiao.data.CourseContract;

/**
 * Created by 刘灿锐 on 2017/9/14.
 */

public class CourseJsonUtils {
    private final static String CLASS_NAME = "className";
    private final static String CLASS_NUM = "classNum";


    public static ContentValues[] getClassContentValuesFromJson(String forecastJsonStr)
            throws JSONException {
        JSONArray forecastJson = new JSONArray(forecastJsonStr);

        long classId;

        String className;


        String classNum;

        ContentValues[] classContentValues = new ContentValues[forecastJson.length()];

        for (int i = 0; i < forecastJson.length(); i++) {


            /* Get the JSON object representing the day */
            JSONObject classForecast = forecastJson.getJSONObject(i);

            /*
             * We ignore all the datetime values embedded in the JSON and assume that
             * the values are returned in-order by day (which is not guaranteed to be correct).
             */

            classId = i;
            className = classForecast.getString(CLASS_NAME);
            classNum = classForecast.getString(CLASS_NUM);


            ContentValues classValues = new ContentValues();
            classValues.put(CourseContract.ClassEntry._ID, classId);
            classValues.put(CourseContract.ClassEntry.COLUMN_CLASS_NAME, className);
            classValues.put(CourseContract.ClassEntry.COLUMN_CLASS_ID, classNum);
            classValues.put(CourseContract.ClassEntry.COLUMN_SUGGEST, className);

            classContentValues[i] = classValues;
        }


        return classContentValues;
    }

    public static ContentValues[] getCourseContentValuesFromJson(String forecastJsonStr)
            throws JSONException {
        JSONArray forecastJson = new JSONArray(forecastJsonStr);

        String classId;

        String cName;

        String className;

        String cMethod;

        String cTeaName;

        int cPeoNum;

        String cZhoushu;

        int cXingqi;

        String cJieci;

        int cDanshuang;

        String cAddress;


        ContentValues[] courseContentValues = new ContentValues[forecastJson.length()];

        for (int i = 0; i < forecastJson.length(); i++) {


            /* Get the JSON object representing the day */
            JSONObject courseForecast = forecastJson.getJSONObject(i);

            /*
             * We ignore all the datetime values embedded in the JSON and assume that
             * the values are returned in-order by day (which is not guaranteed to be correct).
             */

            classId = courseForecast.getString(CourseContract.CourseEntry.COLUMN_CLASS_ID);
            cName = courseForecast.getString(CourseContract.CourseEntry.COLUMN_C_NAME);
            className = courseForecast.getString(CourseContract.CourseEntry.COLUMN_CLASS_NAME);
            cMethod = courseForecast.getString(CourseContract.CourseEntry.COLUMN_C_METHOD);
            cTeaName = courseForecast.getString(CourseContract.CourseEntry.COLUMN_C_TEACHER_NAME);
            cPeoNum = courseForecast.getInt(CourseContract.CourseEntry.COLUMN_C_NUM);
            cZhoushu = courseForecast.getString(CourseContract.CourseEntry.COLUMN_C_WEEK_NUM);
            cXingqi = courseForecast.getInt(CourseContract.CourseEntry.COLUMN_C_WEEK);
            cJieci = courseForecast.getString(CourseContract.CourseEntry.COLUMN_C_SESSION);
            cDanshuang = courseForecast.getInt(CourseContract.CourseEntry.COLUMN_C_S_OR_D);
            cAddress = courseForecast.getString(CourseContract.CourseEntry.COLUMN_C_ADDRESS);


            ContentValues courseValues = new ContentValues();
            courseValues.put(CourseContract.CourseEntry.COLUMN_CLASS_ID, classId);
            courseValues.put(CourseContract.CourseEntry.COLUMN_C_NAME, cName);
            courseValues.put(CourseContract.CourseEntry.COLUMN_CLASS_NAME, className);
            courseValues.put(CourseContract.CourseEntry.COLUMN_C_METHOD, cMethod);
            courseValues.put(CourseContract.CourseEntry.COLUMN_C_TEACHER_NAME, cTeaName);
            courseValues.put(CourseContract.CourseEntry.COLUMN_C_NUM, cPeoNum);
            courseValues.put(CourseContract.CourseEntry.COLUMN_C_WEEK_NUM, cZhoushu);
            courseValues.put(CourseContract.CourseEntry.COLUMN_C_WEEK, cXingqi);
            courseValues.put(CourseContract.CourseEntry.COLUMN_C_SESSION, cJieci);
            courseValues.put(CourseContract.CourseEntry.COLUMN_C_S_OR_D, cDanshuang);
            courseValues.put(CourseContract.CourseEntry.COLUMN_C_ADDRESS, cAddress);

            courseContentValues[i] = courseValues;
        }


        return courseContentValues;
    }
}
