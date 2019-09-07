package cn.xtrui.database.controller;

import cn.xtrui.database.bean.User;
import cn.xtrui.database.mapper.Usermapper;
import cn.xtrui.database.util.MD5Util;
import cn.xtrui.database.util.ValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static cn.xtrui.database.util.MD5Util.simpleMD5;

@Controller
public class LoginController {
    Logger log =  LoggerFactory.getLogger("mylog");

    /**
     * 获取验证码
     */
    @GetMapping("/getValidateCode")
    @ResponseBody
    public Map<String, String> getValidateCode(HttpSession session){
        //直接调用静态方法，返回验证码对象
        ValidateCodeUtil.Validate v = ValidateCodeUtil.getRandomCode();
        Map<String, String> map = new Hashtable<>();
        if(v!=null) {
            String s = DigestUtils.md5DigestAsHex(v.getValue().toLowerCase().getBytes());
            map.put("Base64Str",v.getBase64Str());
            map.put("code", "200");
            map.put("message", "验证码刷新成功");
            session.setAttribute("validateMd5Value",s);
        }
        else {
            map.put("code", "504");
            map.put("message", "验证码刷新失败");
        }

        return map;

    }

    /**
     *判断验证码session是否正确
     * @param request
     * @param validateCode
     * @return
     */

    public boolean existVolidateSession(HttpServletRequest request, String validateCode){
        boolean flag;
        if (request.getSession().getAttribute("validateMd5Value") == null) {
            flag = false;
        }
        else {
            flag = ((String) request.getSession().getAttribute("validateMd5Value")).equals(simpleMD5(validateCode.toLowerCase()));
        }
        return  flag;
    }

    /**
     * 登录请求处理处理
     */
    @Autowired
    Usermapper usermapper;
    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password, String validateCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean flag =false ;
        flag = existVolidateSession(request, validateCode);

        flag = true; //临时不要验证码

        Map<String, String> map = new HashMap<>();
        /** 如果验证码正确*/
        if(flag) {
            User user = usermapper.login(username, MD5Util.simpleMD5(password));

            if (user == null) {
                map.put("code", "404");
                map.put("message", "用户名不存在或密码不正确");

            } else {
                user.setPassword("");
                request.getSession().setAttribute("user", user);
                Cookie cookie = new Cookie("username", URLEncoder.encode(user.getUsername(),"utf-8"));
                response.addCookie(cookie);
                map.put("code", "200");
                map.put("message", "登陆成功");
                log.info("用户----"+username+"----登陆");

            }
        }

        else {
            map.put("code", "404");
            map.put("message", "验证码不正确或已过期");
        }
        request.getSession().removeAttribute("validateMd5Value");
        return map;
    }

    /**
     * 注册请求处理
     * @return
     */

    @PostMapping("/register")
    @ResponseBody

    public Map<String, String> register(User user, String validateCode, HttpServletRequest request){
        boolean flag = false ;
        flag = existVolidateSession(request,validateCode);

        flag = true; //临时不要验证码
        Map<String, String> map = new HashMap<>();
        if (flag){
            if(usermapper.getUserByName(user.getUsername())==null) {
                user.setPassword(MD5Util.simpleMD5(user.getPassword()));
                usermapper.register(user);
                map.put("code", "200");
                map.put("message", "注册成功");
                log.info("用户"+user.getUsername()+"注册成功");
            }
            else {
                map.put("code","404");
                map.put("message","用户名已存在");
            }
        }
        else {
            map.put("code","404");
            map.put("message","验证码不正确或已过期");
        }
        request.getSession().removeAttribute("validateMd5Value");
        return map;
    }
}
