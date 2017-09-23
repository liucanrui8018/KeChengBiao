package liucanrui.com.kechengbiao.data;

import android.app.SearchManager;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by 刘灿锐 on 2017/9/12.
 */

public class CourseContract {

    public static final String CONTENT_AUTHORITY = "liucanrui.com.kechengbiao";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_COURSE = "course";

    public static final String PATH_CLASS = "class";





    public static final class ClassEntry implements BaseColumns {



        public static final Uri CONTENT_CLASS_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_CLASS)
                .build();

        public static final String CLASS_TABLE_NAME = "class";


        public static final String COLUMN_CLASS_ID = "class_id";

        public static final String COLUMN_CLASS_NAME = SearchManager.SUGGEST_COLUMN_TEXT_1;

        public static final String COLUMN_SUGGEST = SearchManager.SUGGEST_COLUMN_INTENT_DATA;


    }


    public static final class CourseEntry implements BaseColumns {



        public static final Uri CONTENT_COURSE_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_COURSE)
                .build();

        public static final String COURSE_TABLE_NAME = "course";


        public static final String COLUMN_CLASS_ID = "class_id";

        public static final String COLUMN_C_NAME = "c_name";

        public static final String COLUMN_CLASS_NAME = "class_name";

        public static final String COLUMN_C_METHOD = "c_method";

        public static final String COLUMN_C_TEACHER_NAME = "c_teaName";

        public static final String COLUMN_C_NUM = "c_peoNum";

        public static final String COLUMN_C_WEEK_NUM = "c_zhoushu";

        public static final String COLUMN_C_WEEK = "c_xingqi";

        public static final String COLUMN_C_SESSION = "c_jieci";

        public static final String COLUMN_C_S_OR_D = "c_danshuang";

        public static final String COLUMN_C_ADDRESS = "c_address";



    }
}
