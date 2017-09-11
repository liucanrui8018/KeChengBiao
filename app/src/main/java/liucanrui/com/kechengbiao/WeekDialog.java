package liucanrui.com.kechengbiao;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;

import liucanrui.com.kechengbiao.databinding.ActivityMainBinding;


/**
 * Created by 刘灿锐 on 2017/9/5.
 */

class WeekDialog {


    private DateCalculation dateCalculation;

    public WeekDialog( DateCalculation dateCalculation) {
        this.dateCalculation = dateCalculation;
    }

    public Dialog onCreateDialog(final Context context, final Button button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.pick_week)
                .setItems(R.array.all_week, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        int week = which + 1;

                        String sWeekFormat = context.getResources().getString(R.string.show_week);
                        String sFinalWeek = String.format(sWeekFormat, week);

                        dateCalculation.setDateAndMonth(week);
                        button.setText(sFinalWeek);

                    }
                });
        return builder.create();
    }


}
