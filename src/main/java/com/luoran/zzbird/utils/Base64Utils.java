package com.luoran.zzbird.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.misc.BASE64Encoder;

/**
 * @Author WSL
 * @Date 13:25 2019/1/22
 * @ProjectName kaka-parent
 * @PackageName com.luoran.common.util
 **/
public class Base64Utils {
    public static void main(String[] args) throws Exception {
        //本地图片地址
        //String url = "E:/img/3e0d5909751a4061b8c063dd86dbf505.jpg";
        //在线图片地址
        String string = "http://192.168.1.100/kaka/4a293cbc5a9d4ab2a68667d55a4ca785.jpg";

        //String str = Base64Utils.ImageToBase64ByLocal(url);

        String ste = Base64Utils.ImageToBase64ByOnline(string);

        System.out.println(ste);

        //Base64Utils.Base64ToImage(str, "C:/Users/18523/Desktop/test1.jpg");

        //Base64Utils.Base64ToImage(ste, "C:/Users/18523/Desktop/test2.jpg");
    }



    /**
     * 在线图片转换成base64字符串
     *
     * @param imgURL 图片线上路径
     * @return
     * @author WSL
     * @dateTime 2018-02-23 14:43:18
     */
    public static String ImageToBase64ByOnline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }

	
}

