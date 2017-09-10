package liucanrui.com.kechengbiao;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button week_button;
    TextView mMonth;
    TextView mMonday, mTuesday, mWednesday, mThursday, mFriday, mSaturday, mSunday;
    DateCalculation dateCalculation;

    WeekOnclick weekOnclick = new WeekOnclick() {
        @Override
        public int addWeek(int i) {




            return 0;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateCalculation = new DateCalculation();

        findView();

        week_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new WeekDialog(dateCalculation.getWeek(), weekOnclick).onCreateDialog(MainActivity.this, week_button);
                dialog.show();
            }
        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        setMonth();


//        mTextView = (TextView) findViewById(R.id.textView);
//
//        final ViewGroup.LayoutParams lp = mTextView.getLayoutParams();
//        lp.height = lp.height * 4;
//        mTextView.setLayoutParams(lp);


    }


    private void findView() {
        week_button = (Button) findViewById(R.id.pick_week);
        mMonth = (TextView) findViewById(R.id.month);

        mMonday = (TextView) findViewById(R.id.date1);
        mTuesday = (TextView) findViewById(R.id.date2);
        mWednesday = (TextView) findViewById(R.id.date3);
        mThursday = (TextView) findViewById(R.id.date4);
        mFriday = (TextView) findViewById(R.id.date5);
        mSaturday = (TextView) findViewById(R.id.date6);
        mSunday = (TextView) findViewById(R.id.date7);


    }


    private void setMonth() {


        String sMonthFormat = getResources().getString(R.string.show_month);

        String sFinalMonth = String.format(sMonthFormat, dateCalculation.getMonth());

        mMonth.setText(sFinalMonth);

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
