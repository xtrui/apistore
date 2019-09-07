package cn.xtrui.database.controller;

import cn.xtrui.database.bean.User;
import cn.xtrui.database.service.UserService;
import cn.xtrui.database.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Controller
public class UserController {
    Logger log =  LoggerFactory.getLogger("mylog");
    @Autowired
    private  UserService userService;

    /**
     * 查找指定页的user
     * @param page
     * @return
     */
    @ResponseBody
    @GetMapping("/user/search")
    public Vector<User> search(Integer page){
        if (page == null) {
            page = 1;
        }
        Vector<User> users = userService.searchUserByPage(page);
        return users;
    }

    /**
     * 修改更新用户
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/user/update")
    public Map updateUser(User user, HttpServletRequest request){
        HashMap<Object, Object> map = new HashMap<>();
        boolean flag = false;
        if (user.getId()!=1) {
            user.setPassword(MD5Util.simpleMD5(user.getPassword()));
            flag = userService.updateUser(user);
            if (flag) {
                map.put("code", "200");
                map.put("message", "更新成功");
                User user1 = (User) request.getSession().getAttribute("user");
                log.info("用户--" + user1.getUsername() + "---更改用户信息--" + user.toString());
            }
            else {
                map.put("code","404");
                map.put("message","更新失败");
            }
        }
        else {
            map.put("code","404");
            map.put("message","更新失败");
        }
            return map;
    }

    @ResponseBody
    @DeleteMapping("/user/delete")
    public Map deleteUser(Integer id,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;

        if(id!=1) {
            flag = userService.deleteUser(id);
            if (flag) {
                map.put("code", "200");
                map.put("message", "删除成功");
                User user = (User) request.getSession().getAttribute("user");
                log.info("用户--" + user.getUsername() + "---删除用户--被删除用户id=" + id);
            }
            else {
                map.put("code","404");
                map.put("message","删除失败");
            }
        }
        else {
            map.put("code","404");
            map.put("message","删除失败");
        }
        return map;
    }

}
