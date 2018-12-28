package com.gj.gejigeji.util;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;

public class SmsUtil {

        private static final String ACCOUNT = "bht1210";
        private static final String PASSWORD = "fe3238d6867ace97bd4d289ec87e1517";
        private static final String BASEURL="http://api.smsbao.com/sms";

        public static boolean sendSms(String phone,String checkcode){
            HttpClient httpClient = null;
            HttpGet httpGet= null;
            String result = null;
            try {
                String content="【格叽格叽】您的验证码为"+checkcode+"，请注意查收！";


                httpClient = new DefaultHttpClient();
                String url=BASEURL+"?u="+ACCOUNT+"&p="+PASSWORD+"&m="+phone+"&c="+ URLEncoder.encode(content,"utf-8");
                httpGet = new HttpGet(url);
                HttpResponse response = httpClient.execute(httpGet);
                if(response != null){
                    HttpEntity resEntity = response.getEntity();
                    if(resEntity != null){
                        result = EntityUtils.toString(resEntity,"utf-8");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if("0".equals(result)){
                return true;
            }
            return false;

        }

        public static void main(String[] args) {
            boolean b = SmsUtil.sendSms("13261900961","345678");
            System.out.println(b);
        }



}
