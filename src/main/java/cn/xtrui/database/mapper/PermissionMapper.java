package cn.xtrui.database.mapper;

import cn.xtrui.database.bean.Permission;
import org.apache.ibatis.annotations.*;

import java.util.Vector;

@Mapper
public interface PermissionMapper {
    @Select("select name from permission where id = #{id}")
    public String getPermissionNameById(Integer id);
    @Select("select permission_id,type  from role_permission where role_id in (select role_id from user_role where user_id = #{id})")
    @Results(
            @Result(column = "permission_id",property = "id")
    )
    public  Vector<Permission> getPermissionByUserId(Integer id);

    @Select("select * from permission where id in(select permission_id from role_permission where role_id=#{id})")
    public Vector<Permission> getPermissionsByRoleId(Integer id);

    @Select("select * from permission")
    public Vector<Permission> getAllPermission();

    @Insert("insert into permission(id,name) values(#{id},#{name})")
    public boolean insertPermission(Permission permission);

    @Insert("insert into role_permission(id,role_id,permission_id,type) values(null,#{roleId},#{permissionId},#{type})")
    public boolean insertPermissionToRole(Integer roleId, Integer permissionId, Integer type);

    @Update("update permission set name = #{name} where id = #{id}")
    public boolean updatePermission(Permission permission);

    @Delete("delete from permission where id = #{id}")
    public  boolean deletePermission(Integer id);
}
