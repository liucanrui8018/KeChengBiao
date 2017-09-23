package liucanrui.com.kechengbiao.data;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 访问Web Service的工具类
 *
 * @author jCuckoo
 */
@SuppressLint("NewApi")
public class WebServiceHelper {
    static {
        // 4.0以后需要加入下列两行代码，才可以访问Web Service
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
        //4.0以前版本不需要以上设置
    }

    /**
     * @param methodName web service方法名称
     * @param params     web service方法参数
     */
    public static SoapObject getSoapObject(String methodName, HashMap<String, Object> params) {
        String URL = "http://10.0.2.2:8080/services/MyService?wsdl";
        String NAMESPACE = "http://example/";// 名称空间，服务器端生成的namespace属性值

        SoapObject soap = null;
        try {
            SoapObject rpc = new SoapObject(NAMESPACE, methodName);
            if (params != null && params.size() > 0) {
                for (Entry<String, Object> item : params.entrySet()) {
                    rpc.addProperty(item.getKey(), item.getValue().toString());
                }
            }

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = rpc;
            envelope.dotNet = false;// true--net; false--java;
            envelope.setOutputSoapObject(rpc);

            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.debug = true;
            ht.call(null, envelope);
            try {
                soap = (SoapObject) envelope.getResponse();

            } catch (Exception e) {
                soap = (SoapObject) envelope.bodyIn;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return soap;
    }



}
