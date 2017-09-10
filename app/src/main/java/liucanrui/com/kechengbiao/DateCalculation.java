package liucanrui.com.kechengbiao;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 刘灿锐 on 2017/9/10.
 */

public class DateCalculation {

    private final static Date StartSchoolDay = new Date(2017, 9, 4);


    int getMonth(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    int getWeek() {
        return 1;
    }



    int calculationDate(int add) {
        Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
//        cal.add(Calendar.DAY_OF_MONTH, -1);//取当前日期的前一天.
        cal.add(Calendar.DAY_OF_MONTH, +add);//取当前日期的后一天.
//通过格式化输出日期
//        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
//        System.out.println("Today is:" + format.format(Calendar.getInstance().getTime()));

        return cal.getTime().getDay();
    }
}
