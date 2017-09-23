package liucanrui.com.kechengbiao;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import liucanrui.com.kechengbiao.sync.ClassSyncUtils;

public class WelcomeActivity extends AppCompatActivity implements LoadClass{




    private TextView mData;

    Dialog loadDialog;

    private static final String CLASS_NAME = "ClassName";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (classInfo() != null) {
            Intent startMainActivityIntent = new Intent(WelcomeActivity.this, MainActivity.class);
            startMainActivityIntent.putExtra(CLASS_NAME, classInfo());

            startActivity(startMainActivityIntent);
            finish();
        }

        mData = (TextView) findViewById(R.id.data);
        loadDialog = new ShowDialog().onCreateLoadDialog(this, getLayoutInflater());

        ClassSyncUtils.initialize(this, this);

//        new UserDataBaseTask().execute();

    }

    @Override
    public void show() {
        showLoading();
    }

    @Override
    public void cancel() {
        showDataView();
    }




    private void showDataView() {
        /* First, hide the loading indicator */

        /* Finally, make sure the weather data is visible */
        loadDialog.cancel();
        loadDialog.dismiss();
    }

    /**
     * This method will make the loading indicator visible and hide the weather View and error
     * message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't need to check whether
     * each view is currently visible or invisible.
     */
    private void showLoading() {
        /* Then, hide the weather data */

        /* Finally, show the loading indicator */
        loadDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);    // 显示“开始搜索”的按钮
        searchView.setQueryRefinementEnabled(true); // 提示内容右边提供一个将提示内容放到搜索框的按钮
        return true;
    }

    private String classInfo() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPre", MODE_PRIVATE);
        String account = sharedPreferences.getString(CLASS_NAME, "");

        if (!account.equals("")) {
            return account;
        } else {
            return null;
        }
    }

}
