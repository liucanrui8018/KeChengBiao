package liucanrui.com.kechengbiao;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import liucanrui.com.kechengbiao.databinding.ActivityMainBinding;


/**
 * Created by 刘灿锐 on 2017/9/5.
 */

class ShowDialog {


    public Dialog onCreateWeekDialog(final Context context, final ActivityMainBinding mBinding, final int thisWeek
            , final DateCalculation dateCalculation, final CourseView[] courses) {

        final String[] item = new String[]{
                "第1周",
                "第2周",
                "第3周",
                "第4周",
                "第5周",
                "第6周",
                "第7周",
                "第8周",
                "第9周",
                "第10周",
                "第11周",
                "第12周",
                "第13周",
                "第14周",
                "第15周",
                "第16周",
                "第17周",
                "第18周",
                "第19周",
                "第20周",
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        item[thisWeek - 1] = item[thisWeek - 1] + "(本周)";

        builder.setTitle(R.string.pick_week)
                .setItems(item, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        int week = which + 1;

                        for (CourseView course : courses) {

                                course.setThisWeek(week);
                                course.showView();

                        }


                        mBinding.pickWeek.setText(item[which]);


                        dateCalculation.setDateAndMonth(week);


                    }
                });

        return builder.create();
    }

    public Dialog onCreateLoadDialog(final Context context, LayoutInflater inflater) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(inflater.inflate(R.layout.dialog_load, null));

        return builder.create();
    }


}
