package liucanrui.com.kechengbiao;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;


/**
 * Created by 刘灿锐 on 2017/9/5.
 */

class WeekDialog {

    public Dialog onCreateDialog(final Context context, final Button button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.pick_week)
                .setItems(R.array.all_week, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        String sWeekFormat = context.getResources().getString(R.string.show_week);
                        String sFinalWeek = String.format(sWeekFormat, which+1);

                        button.setText(sFinalWeek);
                        Log.e("TAG", "" + which);


                    }
                });
        return builder.create();
    }
}
