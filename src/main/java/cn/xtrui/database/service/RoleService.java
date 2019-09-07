package cn.xtrui.database.service;

import cn.xtrui.database.bean.Role;
import cn.xtrui.database.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Vector;
@Component
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public Vector<Role> getAllRoles(){
        return roleMapper.getRoles();
    }

    public  Vector<Role> getRolesByUserId(Integer id){return roleMapper.getRolesByUserId(id);}

    public boolean deleteRoleById(Integer id){

        return roleMapper.deleteRoleById(id);
    }

    public  boolean insertRole(Role role){
        return roleMapper.insertRole(role);
    }

    public boolean updateRole(Role role){
        return roleMapper.updateRole(role);
    }

    public boolean inserRoleToUser(Integer userId,Integer roleId,Integer type){
        return roleMapper.insertRoleToUser(userId,roleId,type);
    }

    public Integer getIdByName(String roleName){
        return roleMapper.getIdByRoleName(roleName);
    }
}
