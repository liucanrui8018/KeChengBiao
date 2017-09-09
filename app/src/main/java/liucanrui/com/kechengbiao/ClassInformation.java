package liucanrui.com.kechengbiao;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by 刘灿锐 on 2017/9/9.
 */

public class ClassInformation {

    private int Start;
    private int time;
    private int classNumber;
    private int date;

    public ClassInformation(int Start, int time, int classNumber, int date){
        this.Start = Start;
        this.time = time;
        this.classNumber = classNumber;
        this.date = date;
    }


//    public TextView CreateClassInformation(Context context){
//        TextView classInfo = new TextView(context);
//
//        classInfo.setText("计算机科学与技术");
//        classInfo.setWidth();
//        classInfo.setX(50);
//        classInfo.setY(65);
//        return classInfo;
//    }


}
