package cn.xtrui.database.service;

import cn.xtrui.database.bean.Permission;
import cn.xtrui.database.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Vector;
@Component
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public Vector<Permission> getAllPermission(){
        return permissionMapper.getAllPermission();
    }

    public boolean deletePermission(Integer id){
        return permissionMapper.deletePermission(id);
    }

    public  boolean insertPermission(Permission permission){
        return permissionMapper.insertPermission(permission);
    }

    public  boolean updatePermission(Permission permission){
        return permissionMapper.updatePermission(permission);
    }

    public  String get(Integer id){
        return permissionMapper.getPermissionNameById(id);
    }

    public boolean insertPermissionToRole(Integer roleId, Integer permissionId, Integer type){
        return permissionMapper.insertPermissionToRole(roleId, permissionId, type);
    }

    public Vector<Permission> getPermissionsByRoleId(Integer roleId){
        return permissionMapper.getPermissionsByRoleId(roleId);
    }
    /**
     * 通过用户ID获取用户权限集合
     * @param userId
     * @return
     */
    public Vector<Permission> getPermissionByUserId(Integer userId){
        Vector<Permission> permissions= permissionMapper.getPermissionByUserId(userId);
        for(Permission p: permissions){
            String permissionName = permissionMapper.getPermissionNameById(p.getId());
            p.setName(permissionName);
        }
        return permissions;
    }
}
