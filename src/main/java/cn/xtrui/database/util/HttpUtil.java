package cn.xtrui.database.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static String getHttpContent(String url,String parameterData ) throws Exception {
        HttpURLConnection connection = null;
//          String content = "";
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        try {
            URL address_url = new URL(url);
            connection = (HttpURLConnection) address_url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(parameterData.length()));
            //设置访问超时时间及读取网页流的超市时间,毫秒值
            System.setProperty("sun.net.client.defaultConnectTimeout","3000");
            System.setProperty("sun.net.client.defaultReadTimeout", "3000");
            outputStream = connection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(parameterData);
            outputStreamWriter.flush();

            if (connection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + connection.getResponseCode());
            }
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
        }finally {
            if(connection !=null){
                connection.disconnect();
            }
        }
        return resultBuffer.toString();
    }
}
