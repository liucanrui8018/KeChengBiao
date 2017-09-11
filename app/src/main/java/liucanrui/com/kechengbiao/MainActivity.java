package liucanrui.com.kechengbiao;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.ParseException;

import liucanrui.com.kechengbiao.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Button week_button;
    DateCalculation dateCalculation;

    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        try {
            dateCalculation = new DateCalculation(mainBinding, this);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        week_button = (Button) findViewById(R.id.pick_week);

        week_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new WeekDialog(dateCalculation).onCreateDialog(MainActivity.this, week_button);
                dialog.show();
            }
        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


//        mTextView = (TextView) findViewById(R.id.textView);
//
//        final ViewGroup.LayoutParams lp = mTextView.getLayoutParams();
//        lp.height = lp.height * 4;
//        mTextView.setLayoutParams(lp);

        long i = dateCalculation.thisWeek();

        dateCalculation.setDateAndMonth(i);
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


}
