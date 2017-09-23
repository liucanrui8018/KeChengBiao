package liucanrui.com.kechengbiao;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.text.ParseException;

import liucanrui.com.kechengbiao.data.CourseContract;
import liucanrui.com.kechengbiao.databinding.ActivityMainBinding;
import liucanrui.com.kechengbiao.utilities.CourseJsonUtils;
import liucanrui.com.kechengbiao.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity implements LoadClass, LoaderManager.LoaderCallbacks<Cursor> {

    Button week_button;
    DateCalculation dateCalculation;

    ActivityMainBinding mainBinding;

    private static final String CLASS_NAME = "ClassName";

    private static final int ID_FORECAST_LOADER = 44;

    int thisWeek;


    RelativeLayout mRelativeLayout;

    Dialog loadDialog;

    String className;

    TextView mTvWord;

    CourseView[] courseViews;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(CLASS_NAME)) {
            className = intentThatStartedThisActivity.getStringExtra(CLASS_NAME);
            mTvWord.setText(className);

        } else {
            handleIntent(intentThatStartedThisActivity);
        }

        loadDialog.show();
        new UserDataBaseTask().execute();

        getSupportLoaderManager().initLoader(ID_FORECAST_LOADER, null, MainActivity.this);


    }

    private void init() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        try {
            dateCalculation = new DateCalculation(mainBinding, this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        thisWeek = dateCalculation.thisWeek();

        String sWeekFormat = getResources().getString(R.string.show_week);
        String sFinalWeek = String.format(sWeekFormat, thisWeek);


        week_button = (Button) findViewById(R.id.pick_week);

        week_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new ShowDialog().onCreateWeekDialog(MainActivity.this, mainBinding, thisWeek, dateCalculation, courseViews);
                dialog.show();
            }
        });

        loadDialog = new ShowDialog().onCreateLoadDialog(this, getLayoutInflater());
        week_button.setText(sFinalWeek + "(本周)");
        mTvWord = (TextView) findViewById(R.id.class_name);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        dateCalculation.setDateAndMonth(thisWeek);


    }


    private class UserDataBaseTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Uri forecastQueryUri = CourseContract.CourseEntry.CONTENT_COURSE_URI;

            Cursor cursor = getContentResolver().query(
                    forecastQueryUri,
                    null,
                    null,
                    null,
                    null);

            if (null == cursor || cursor.getCount() == 0) {

                try {

                    String jsonWeatherResponse = NetworkUtils.getCourseResponse("'" + className + "'");

                    Log.d(MainActivity.class.getSimpleName(), jsonWeatherResponse);


                    ContentValues[] classValues = CourseJsonUtils.getCourseContentValuesFromJson(jsonWeatherResponse);


                    if (classValues.length != 0) {
                /* Get a handle on the ContentResolver to delete and insert data */
                        ContentResolver contentResolver = getContentResolver();

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
                    Log.d(MainActivity.class.getSimpleName(), className + "数据库创建成功");


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
        protected void onPostExecute(Void v) {
            loadDialog.cancel();


        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        switch (id) {
            case ID_FORECAST_LOADER:


                Uri QueryUri = CourseContract.CourseEntry.CONTENT_COURSE_URI;

                return new CursorLoader(this,
                        QueryUri,
                        null,
                        null,
                        null,
                        null);

            default:
                throw new RuntimeException("Loader Not Implemented: " + id);
        }

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();

        courseViews = new CourseView[data.getCount()];

        mRelativeLayout = (RelativeLayout) findViewById(R.id.id_all_class);

        for (int i = 0; i < data.getCount(); i++) {

            System.out.println(data.getString(3) + " " + data.getString(9) + " " + data.getString(8) + " " + data.getInt(10) + " " + data.getString(7));
            String[] sessions = data.getString(9).split("-");
            String s = data.getString(3) + "@" + data.getString(11);

            int dangshuang = data.getInt(10);


            courseViews[i] = new CourseView(this, Integer.parseInt(sessions[0]) - 1,
                    Integer.parseInt(sessions[1]) - Integer.parseInt(sessions[0]) + 1,
                            data.getInt(8), s, dangshuang, thisWeek);

            courseViews[i].setShowWeekNumber(data.getString(7));

            courseViews[i].showView();

            mRelativeLayout.addView(courseViews[i]);

            data.moveToNext();
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        switch (intent.getAction()) {
            case Intent.ACTION_SEARCH:
                String query = intent.getStringExtra(SearchManager.QUERY);
                mTvWord.setText(query);

                break;
            case "android.Intent.action.VIEW":
                className = intent.getDataString();
                mTvWord.setText(className);
                saveClass(className);
                break;

            default:
                mTvWord.setText("班级未找到");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


    private void saveClass(String className) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPre", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CLASS_NAME, className);
        editor.apply();
    }

    @Override
    public void show() {
        loadDialog.show();
    }

    @Override
    public void cancel() {
        loadDialog.cancel();
    }


}
