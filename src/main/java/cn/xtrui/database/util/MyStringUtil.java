package cn.xtrui.database.util;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MyStringUtil {
    public static Map paramToMap(@NotNull String param){
        HashMap<Object, Object> map = new HashMap<>();
        String[] kvs = param.split("&");
        if(param.length() >= 1){
            for(String p:kvs){
                String[] kv = p.split("=");
                String k = kv[0];
                String v = kv[1];
                map.put(k,v);
            }
        }
        return map;
    }
}
