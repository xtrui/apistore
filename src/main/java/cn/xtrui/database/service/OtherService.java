package cn.xtrui.database.service;

import cn.xtrui.database.bean.HttpClientResult;
import cn.xtrui.database.util.HttpClientUtils;
import cn.xtrui.database.util.MyStringUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OtherService {
    public HttpClientResult doHttpRequest(String url,String param,String method) throws Exception {
        HttpClientResult result = new HttpClientResult();
        Map map = MyStringUtil.paramToMap(param);
        if(param == null){
            switch (method){
                case "GET":
                    result = HttpClientUtils.doGet(url);
                    break;
                case "POST":
                    result = HttpClientUtils.doPost(url);
                    break;
                case "PUT":
                    result = HttpClientUtils.doPost(url);
                    break;
                case "DELETE":
                    result = HttpClientUtils.doDelete(url);
                    break;
                    default:break;
            }
        }
        else {
            switch (method){
                case "GET":
                    result = HttpClientUtils.doGet(url,map);
                    break;
                case "POST":
                    result = HttpClientUtils.doPost(url,map);
                    break;
                case "PUT":
                    result = HttpClientUtils.doPost(url,map);
                    break;
                case "DELETE":
                    result = HttpClientUtils.doDelete(url,map);
                    break;
                default:break;
            }
        }
        return result;
    }
}
