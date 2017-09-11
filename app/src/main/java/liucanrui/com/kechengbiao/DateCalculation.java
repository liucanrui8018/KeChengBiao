package liucanrui.com.kechengbiao;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import liucanrui.com.kechengbiao.databinding.ActivityMainBinding;

/**
 * Created by 刘灿锐 on 2017/9/10.
 */

public class DateCalculation {

    private static Date START_SCHOOL_DAY;



    private Context mContext;

    private final static long MILLISECOND_ON_DAY = 24 * 60 * 60 * 1000;

    private ActivityMainBinding mBinding;

    public DateCalculation(ActivityMainBinding mBinding, Context context) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        START_SCHOOL_DAY = format.parse("2017-09-04");
        this.mBinding = mBinding;
        mContext = context;
    }

    long thisWeek() {
        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(df.format(START_SCHOOL_DAY) + "    .........");

        long diff = date.getTime() - START_SCHOOL_DAY.getTime() ;
        System.out.println(diff + "    .........");


        long days = diff / MILLISECOND_ON_DAY;


        long thisWeek = days / 7;

        if (thisWeek > 20) return 20;

        return thisWeek;
    }


    @SuppressLint("SetTextI18n")
    void setDateAndMonth(long week) {


        long diff = (week) * 7 * MILLISECOND_ON_DAY;

        Date date = new Date(START_SCHOOL_DAY.getTime() + diff);


        String sDay = mContext.getResources().getString(R.string.show_day);
        String sMonth = mContext.getResources().getString(R.string.show_month);

        SimpleDateFormat df = new SimpleDateFormat("dd");

        SimpleDateFormat df2 = new SimpleDateFormat("MM");

        String month = String.format(sMonth, df2.format(date));


        String date1 = String.format(sDay, df.format(date));
        String date2 = String.format(sDay, df.format(new Date(date.getTime() + MILLISECOND_ON_DAY)));
        String date3 = String.format(sDay, df.format(new Date(date.getTime() + 2 * MILLISECOND_ON_DAY)));
        String date4 = String.format(sDay, df.format(new Date(date.getTime() + 3 * MILLISECOND_ON_DAY)));
        String date5 = String.format(sDay, df.format(new Date(date.getTime() + 4 * MILLISECOND_ON_DAY)));
        String date6 = String.format(sDay, df.format(new Date(date.getTime() + 5 * MILLISECOND_ON_DAY)));
        String date7 = String.format(sDay, df.format(new Date(date.getTime() + 6 * MILLISECOND_ON_DAY)));

        mBinding.month.setText(month);
        mBinding.date1.setText(date1);
        mBinding.date2.setText(date2);
        mBinding.date3.setText(date3);
        mBinding.date4.setText(date4);
        mBinding.date5.setText(date5);
        mBinding.date6.setText(date6);
        mBinding.date7.setText(date7);


    }
}
