package liucanrui.com.kechengbiao.data;

import org.ksoap2.serialization.SoapObject;

import java.util.HashMap;


/**
 * Created by 刘灿锐 on 2017/6/19.
 */

public class ConnectService {
    public SoapObject getClassInData() {
//        HashMap<String, Object> paramsMap = new HashMap<>();
        // 服务的名称    方法名    soapAction--null   参数数据
        SoapObject soapObject = WebServiceHelper.getSoapObject("getClassInData", null);
        if (soapObject != null) {
            return soapObject;
        }
        return null;
    }

    public SoapObject getCourseInData(String className) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("arg0", className);
        // 服务的名称    方法名    soapAction--null   参数数据
        SoapObject soapObject = WebServiceHelper.getSoapObject("getCourseInData", paramsMap);
        if (soapObject != null) {
            return soapObject;
        }
        return null;
    }


}
