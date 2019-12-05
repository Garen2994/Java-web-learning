package ee.web.Utils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;    //JDK9 中取代 sun.misc.BASE64Encoder


public class DownLoadUtils {

    public static String getFileName(String agent, String filename) throws UnsupportedEncodingException {
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            Base64.Encoder encoder = Base64.getEncoder();
            String str = encoder.encodeToString(filename.getBytes("utf-8"));  //JDK-9 开始 改用encodeToString()
            filename = "=?utf-8?B?" + str + "?=";

//            Base64.Encoder base64Encoder = Base64.getEncoder();
//            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
