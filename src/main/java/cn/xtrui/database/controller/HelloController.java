package cn.xtrui.database.controller;

import cn.xtrui.database.bean.User;
import cn.xtrui.database.mapper.Usermapper;
import cn.xtrui.database.util.FileManageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

@Component
@Controller
public class HelloController {
    @Autowired
    private FileManageUtil fileManageUtil;

    Logger log = LoggerFactory.getLogger("mylog");

    @ResponseBody
    @GetMapping("/log/getLog")
    public Vector<String> getLog(){
        File file = new File("/weblog/database-test/mylog");
        File files[] = file.listFiles();
        Vector<String> logNames = new Vector<>();
        for (File e:files ){
            logNames.add(e.getName());
        }
        return logNames;
    }

    @ResponseBody
    @GetMapping("/log/downloadLog")
    public  Map downloadLog(String fileName, HttpServletRequest request, HttpServletResponse response){
        Map<String, String> map = new Hashtable<>();
        String path = "/weblog/database-test/mylog/"+fileName;
        boolean flag= fileManageUtil.downloadFile(path,fileName,response);
        if (flag) {
            User user = (User) request.getSession().getAttribute("user");
            log.info("用户--" + user.getUsername() + "---下载日志---" + fileName);
            map.put("code", "200");
            map.put("message", "下载成功");
        }
        else {
            map.put("code", "404");
            map.put("message", "下载失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/noPermission")
    public Map noPermission(){
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put("code","404");
        hashtable.put("message","没有权限");
        return hashtable;
    }

    @Autowired
    Usermapper usermapper;
    @GetMapping("/admin")
    public String admin(HttpServletRequest request){


        User user =  usermapper.getUserByName("root");
        request.getSession().setAttribute("user",user);
        int a =request.getSession().getMaxInactiveInterval();

        System.out.println(a);
        System.out.println(request.getSession().getId());
        return "index.html";
    }




}
