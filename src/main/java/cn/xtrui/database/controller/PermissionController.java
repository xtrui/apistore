package cn.xtrui.database.controller;

import cn.xtrui.database.bean.Permission;
import cn.xtrui.database.bean.User;
import cn.xtrui.database.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    Logger log = LoggerFactory.getLogger("mylog");
    @ResponseBody
    @GetMapping("/getPermissionsByRoleId")
    public Vector<Permission> getPermissionsByRoleId(Integer roleId){
        return permissionService.getPermissionsByRoleId(roleId);
    }

    @ResponseBody
    @GetMapping("/getPermissions")
    public Vector<Permission> getPermisionByUserId(Integer id, HttpServletRequest request){
        if(id == null){
            User user = (User)request.getSession().getAttribute("user");
            id = user.getId();
        }
        return permissionService.getPermissionByUserId(id);
    }

    @ResponseBody
    @GetMapping("/permission/search")
    public Vector<Permission> search(){
        return permissionService.getAllPermission();
    }

    @ResponseBody
    @PutMapping("/permission/insert")
    public Map insertPermission(Permission permission, HttpServletRequest request){
        HashMap<Object, Object> map = new HashMap<>();
        boolean flag = permissionService.insertPermission(permission);
        if(flag){
            map.put("code","200");
            map.put("message","添加权限成功");
            User user = (User) request.getSession().getAttribute("user");
            log.info("用户--"+user.getUsername()+"---添加权限--"+permission.toString());
        }

        else {
            map.put("code","404");
            map.put("message","添加权限失败");
        }
        return map;
    }


    @ResponseBody
    @PutMapping("/permission/insertToRole")
    public Map insertToRole(Integer roleId, Integer permissionId, Integer type, HttpServletRequest request){
        boolean flag = permissionService.insertPermissionToRole(roleId, permissionId, type);
        HashMap<Object, Object> map = new HashMap<>();
        if(flag){
            map.put("code","200");
            map.put("message","添加权限成功");
            User user = (User) request.getSession().getAttribute("user");
            log.info("用户--"+user.getUsername()+"给角色"+roleId+ "---添加权限--"+permissionId);
        }

        else {
            map.put("code","404");
            map.put("message","添加权限失败");
        }
        return map;

    }



    @ResponseBody
    @PostMapping("/permission/update")
    public Map updatePermission(Permission permission, HttpServletRequest request){
        Map<Object, Object> map = new HashMap<>();
        boolean flag = permissionService.updatePermission(permission);
        if(flag){
            map.put("code","200");
            map.put("message","更新权限成功");
            User user = (User) request.getSession().getAttribute("user");
            log.info("用户--"+user.getUsername()+"---修改权限--"+permission.toString());
        }

        else {
            map.put("code","404");
            map.put("message","更新权限失败");
        }
        return map;
    }

    @ResponseBody
    @DeleteMapping("/permission/delete")
    public Map deletePermission(Integer id, HttpServletRequest request){
        Map<Object, Object> map = new HashMap<>();
        boolean flag = permissionService.deletePermission(id);
        if(flag){
            map.put("code","200");
            map.put("message","删除权限成功");
            User user = (User) request.getSession().getAttribute("user");
            log.info("用户--"+user.getUsername()+"---删除-权限id"+id);
        }

        else {
            map.put("code","404");
            map.put("message","删除权限失败");
        }
        return map;
    }

}
