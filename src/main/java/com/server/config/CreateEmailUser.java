package com.server.config;

import com.server.entity.HttpResponseEntity;
import com.server.utils.HttpClientUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CreateEmailUser {

    /**
     * 创建用户
     * useradd -g mailusers -s /sbin/nologin b
     *
     * 设置密码
     * echo "b" |passwd --stdin b
     */

    public static String createLinuxEmail(String cmd){
        Runtime runtime = Runtime.getRuntime();
        StringBuffer out = new StringBuffer();
        try{
            Process process = runtime.exec(cmd);
            InputStream inputStream = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(inputStream));
            byte[] b = new byte[8192];
            for (int n; (n = inputStream.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
            inputStream.close();
            process.destroy();
        }catch (Exception e){
            e.printStackTrace();
        }
        return out.toString();
    }


    public static String createLinuxEmail(String param1, String param2){
        Runtime runtime = Runtime.getRuntime();
        StringBuffer out = new StringBuffer();
        try{
            System.out.println("cmd : " + param1);
            System.out.println("cmd : " + param2);
            Process process = runtime.exec(new String[]{"/bin/sh","-c","/usr/a/createuser.sh " + param1 +" " + param2});
            process.waitFor();
            InputStream inputStream = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(inputStream));
            byte[] b = new byte[8192];
            for (int n; (n = inputStream.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }
            System.out.println("返回值 : " + out.toString());
            inputStream.close();
            process.destroy();
            return out.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        /*String cmd = "useradd -g mailusers -s /sbin/nologin e";
        String cmd1 = "echo 'e' |passwd --stdin e";
        String cmd2 = "passwd e";
        String cmd[] = {"echo", "e"}*/
        Map<String, String> map = new HashMap<>();
        map.put("name", "h");
        map.put("password", "h");
        HttpResponseEntity httpResponseEntity = HttpClientUtils.post_init("192.168.56.106",
                9000,
                "/utils/createUser",
                map);
        System.out.println(httpResponseEntity);
    }

}
