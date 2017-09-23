package liucanrui.com.kechengbiao.utilities;

import org.ksoap2.serialization.SoapObject;

import liucanrui.com.kechengbiao.data.ConnectService;

/**
 * Created by 刘灿锐 on 2017/9/14.
 */

public class NetworkUtils {




    public static String getClassResponse() {
        ConnectService connectService = new ConnectService();
        SoapObject soapObject = connectService.getClassInData();

        return soapObject.getProperty(0).toString();

    }

    public static String getCourseResponse(String className) {
        ConnectService connectService = new ConnectService();
        SoapObject soapObject = connectService.getCourseInData(className);

        return soapObject.getProperty(0).toString();

    }
}
