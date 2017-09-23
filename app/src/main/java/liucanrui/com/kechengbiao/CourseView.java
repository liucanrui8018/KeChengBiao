package liucanrui.com.kechengbiao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 刘灿锐 on 2017/9/23.
 */

@SuppressLint("ViewConstructor")
public class CourseView extends android.support.v7.widget.AppCompatTextView {

    boolean[] showWeekNumber;

    Context mContext;
    String s;
    int f;
    int session;
    int week;
    int danshuang;

    int thisWeek;



    public CourseView(Context context, int f,int session, int week, String s, int danshuang, int thisWeek) {
        super(context);
        mContext = context;
        showWeekNumber = new boolean[20];
        this.f = f;
        this.session = session;
        this.week = week;
        this.s = s;
        this.danshuang = danshuang;
        this.thisWeek = thisWeek;

    }

    public int getWeek() {
        return week;
    }

    public void setThisWeek(int week) {
        this.thisWeek = week;
    }

    public void showView() {
        setWidth((int) mContext.getResources().getDimension(R.dimen.class_width));
        setHeight((int) mContext.getResources().getDimension(R.dimen.class_number_high) * session);
//        this.setScaleX(context.getResources().getDimension(R.dimen.class_width));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(((int) mContext.getResources().getDimension(R.dimen.class_number_high) + 3) * (week - 1), f * (int) mContext.getResources().getDimension(R.dimen.class_number_high), 0, 0);//4个参数按顺序分别是左上右下
        setLayoutParams(layoutParams);

        ShapeDrawable backgroundDrawable = new ShapeDrawable(new RoundRectShape(new float[] {10, 10, 10, 10, 10, 10, 10, 10},
                null, null));

        //2. 设置背景颜色
        if (showWeekNumber[thisWeek - 1]) {
            backgroundDrawable.getPaint().setColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            backgroundDrawable.getPaint().setColor(mContext.getResources().getColor(R.color.gray_300));

        }

        //3. 设置透明度

        backgroundDrawable.setAlpha(100);

        //4. 给需要用的ViewGroup/View设置背景
        setBackgroundDrawable(backgroundDrawable);

        setTextSize(11);
        setText(s);
    }

    public boolean[] getShowWeekNumber() {
        return showWeekNumber;
    }

    public void setShowWeekNumber(String showWeekString) {

        boolean[] showWeekNumber = new boolean[20];

        if (showWeekString.contains(",")) {
            String[] strings = showWeekString.split(",");
            for (String string : strings) {
                String[] stringsWeek = string.split("-");
                for (int j = Integer.parseInt(stringsWeek[0]) - 1; j < Integer.parseInt(stringsWeek[1]); j++) {
                    showWeekNumber[j] = ((j + 1) % 2 == 1 && danshuang == 1) || ((j + 1) % 2 == 0 && danshuang == 2) || danshuang == 0;
                }
            }

        } else {
            String[] stringsWeek = showWeekString.split("-");
            for (int j = Integer.parseInt(stringsWeek[0]) - 1; j < Integer.parseInt(stringsWeek[1]); j++) {
                showWeekNumber[j] = ((j + 1) % 2 == 1 && danshuang == 1) || ((j + 1) % 2 == 0 && danshuang == 2) || danshuang == 0;
                System.out.println(j + " " + showWeekNumber[j]);
            }
        }

        this.showWeekNumber = showWeekNumber;
    }
}
