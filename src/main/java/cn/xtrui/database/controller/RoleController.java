package cn.xtrui.database.controller;

import cn.xtrui.database.bean.Role;
import cn.xtrui.database.bean.User;
import cn.xtrui.database.service.RoleService;
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
public class RoleController {
    @Autowired
    private RoleService roleService;

    Logger log = LoggerFactory.getLogger("mylog");
    /**
     * 通过用户Id查角色
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/getRoles")
    public Vector<Role> getRolesByUserId(Integer id, HttpServletRequest request){
        if(id == null){
            User user = (User)request.getSession().getAttribute("user");
            id = user.getId();
        }
        return roleService.getRolesByUserId(id);
    }


    @ResponseBody
    @GetMapping("/role/search")
    public Vector<Role> searchRoles(){
        return roleService.getAllRoles();
    }

    @ResponseBody
    @DeleteMapping("/role/delete")
    public Map deleteRole(Integer id, HttpServletRequest request){
        HashMap<Object, Object> map = new HashMap<>();
        boolean flag = false;
        flag = roleService.deleteRoleById(id);
        if(flag){
            map.put("code","200");
            map.put("message","删除角色成功");
            User user = (User) request.getSession().getAttribute("user");
            log.info("用户--"+user.getUsername()+"---删除角色--角色Id="+id);
        }

        else {
            map.put("code","404");
            map.put("message","删除角色失败");
        }
        return map;
    }

    @ResponseBody
    @PostMapping("/role/update")
    public Map updateRole(Role role, HttpServletRequest request){
        HashMap<Object, Object> map = new HashMap<>();
        boolean flag = roleService.updateRole(role);
        if(flag){
            map.put("code","200");
            map.put("message","修改角色成功");
            User user = (User) request.getSession().getAttribute("user");
            log.info("用户--"+user.getUsername()+"---修改角色--"+role.toString());
        }

        else {
            map.put("code","404");
            map.put("message","修改角色失败");
        }
        return map;
    }

    @ResponseBody
    @PutMapping("/role/insert")
    public Map insertRole(Role role, HttpServletRequest request){
        Map<Object, Object> map = new HashMap<>();
        boolean flag = roleService.insertRole(role);

        if(flag){
            User user = (User) request.getSession().getAttribute("user");
            Integer userId = user.getId();
            Integer roleId = roleService.getIdByName(role.getName());
            roleService.inserRoleToUser(userId,roleId,1);
            map.put("code","200");
            map.put("message","添加角色成功");

            log.info("用户--"+user.getUsername()+"---z添加角色--"+role.toString());
        }

        else {
            map.put("code","404");
            map.put("message","添加角色失败");
        }
        return map;
    }

    @ResponseBody()
    @PutMapping("/role/insertRoleToUser")
    public Map insertRoleToUser(Integer userId, Integer rileId, Integer type){
        boolean flag = roleService.inserRoleToUser(userId, rileId, type);
        HashMap<Object, Object> map = new HashMap<>();
        if(flag){
            map.put("code","200");
            map.put("message","给用户添加角色成功");
        }

        else {
            map.put("code","404");
            map.put("message","给用户添加角色失败");
        }
        return map;
    }
}
