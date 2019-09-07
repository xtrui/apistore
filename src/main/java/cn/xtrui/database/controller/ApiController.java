package cn.xtrui.database.controller;

import cn.xtrui.database.bean.Api;
import cn.xtrui.database.bean.HttpClientResult;
import cn.xtrui.database.bean.TestLog;
import cn.xtrui.database.bean.User;
import cn.xtrui.database.service.ApiService;
import cn.xtrui.database.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ApiController {

    @Autowired
    private ApiService apiService;

    @ResponseBody
    @PostMapping("/api/insert")
    public Map insert(Api api){
        boolean flag = false;
        HashMap<Object, Object> map = new HashMap<>();
        Date date = new Date();
        api.setTime(date);
        flag = apiService.insertApi(api);
        if(flag){
            map.put("code","200");
            map.put("message","添加api成功");
        }
        else {
            map.put("code","404");
            map.put("message","添加api失败");
        }
        return map;
    }

    @ResponseBody
    @PutMapping("/api/update")
    public Map updateApi(Api api){
        boolean flag = false;
        HashMap<Object, Object> map = new HashMap<>();
        Date date = new Date();
        api.setTime(date);
        flag = apiService.updateApi(api)
                && apiService.updateApiStatus(api.getId(),1);
        if(flag){
            map.put("code","200");
            map.put("message","修改api成功");
        }
        else {
            map.put("code","404");
            map.put("message","修改api失败");
        }
        return map;
    }

    @ResponseBody
    @GetMapping("/public/getPublicApi")
    public Vector<Api> getPublicApi(){
        return apiService.getApiByStatus(3);
    }

    @ResponseBody
    @GetMapping("/getMyApi")
    public Vector<Api> getMyApi(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Integer id = user.getId();
        //添加测试人
        Vector<Api> apis = apiService.getApiByUserId(id);
        for(Api api :apis){

            Integer apiId = api.getId();
            String info = api.getInfo();
            if(api.getStatus() == 2){
                String username = apiService.getUsernameByApiIdandType(apiId,2);
                api.setInfo("审核用户:"+username + "  "+ info);
            }
            if (api.getStatus() == 3){
                String username = apiService.getUsernameByApiIdandType(apiId,3);
                api.setInfo("测试用户:"+username + "  "+ info);
            }
        }
        return apis;
    }

    @ResponseBody
    @GetMapping("/api/getApiByStatus")
    public Vector<Api> getApiByStatus(Integer status){
        Vector<Api> apis = apiService.getApiByStatus(status);
        if(status == 2){
            for (Api api: apis){
                Integer apiId = api.getId();
                String info = api.getInfo();
                String username = apiService.getUsernameByApiIdandType(apiId,2);
                api.setInfo("审核用户:"+username + "  "+ info);
            }
        }
        else if (status == 3){
            for (Api api: apis) {
                Integer apiId = api.getId();
                String info = api.getInfo();
                String username = apiService.getUsernameByApiIdandType(apiId,3);
                api.setInfo("测试用户:"+username + "  "+ info);
            }
        }
        return apis;
    }


    @ResponseBody
    @DeleteMapping("/api/deleteApiById")
    public  Map<Object, Object> deleteApiById(Integer id){
        boolean flag = false;
        HashMap<Object, Object> map = new HashMap<>();
        flag = apiService.deleteApiById(id);
        if(flag){
            map.put("code","200");
            map.put("message","删除api成功");
        }
        else {
            map.put("code","404");
            map.put("message","删除api失败");
        }
        return map;
    }

    /**
     * 审核用户请求开始
     */
    @ResponseBody
    @PostMapping("/verify/v")
    public Map verifyApi(Integer flag,Api api){
        Integer apiId = api.getId();
        HashMap<Object, Object> map = new HashMap<>();
        boolean p;
        if(flag == 1){
            p = apiService.updateApiStatus(apiId,2);
        }
        else {
            p = apiService.updateApiStatus(apiId,0);
        }
        if (p){
            map.put("code","200");
            map.put("message","操作成功");
        }
        else {
            map.put("code","400");
            map.put("message","操作失败");
        }
        return map;
    }


    /**
     * 测试用户请求开始
     */
    @ResponseBody
    @PostMapping("/test/ensure")
    public Map testApi(Integer flag, Api api){
        Integer apiId = api.getId();
        HashMap<Object, Object> map = new HashMap<>();
        boolean p;
        if(flag == 1){
            p = apiService.updateApiStatus(apiId,3);
        }
        else {
            p = apiService.updateApiStatus(apiId,0);
        }
        if (p){
            map.put("code","200");
            map.put("message","操作成功");
        }
        else {
            map.put("code","400");
            map.put("message","操作失败 ");
        }
        return map;
    }

    @Autowired
    OtherService otherService;
    @ResponseBody
    @PostMapping("/test/t")
    public HttpClientResult test(Api api,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        api = apiService.getApiById(api.getId());
        Integer userId = user.getId();
        Integer apiId = api.getId();
        String method = api.getMethod();
        String url = api.getUrl();
        String param = api.getReqParam();
        HttpClientResult result = new HttpClientResult();
        try {
            result = otherService.doHttpRequest(url,param,method);
        } catch (Exception e) {
            result.setCode(404);
            result.setContent("请求出错");
            e.printStackTrace();
        }
        Date date = new Date();
        TestLog testLog = new TestLog(null,userId,apiId,result.getCode(),result.getContent(),date);
        apiService.insetLog(testLog);

        return result;
    }

    @ResponseBody
    @GetMapping("/test/getLog")
    public List<TestLog> getTestLogByPage(Integer page){
        return apiService.getLogByPage(page);
    }

}
