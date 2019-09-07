package cn.xtrui.database.mapper;

import cn.xtrui.database.bean.Role;
import org.apache.ibatis.annotations.*;

import java.util.Vector;

@Mapper
public interface RoleMapper {
    @Select("select count(*) from role")
    public int getCount();

    @Select("select id from role where name = #{roleName}")
    public Integer getIdByRoleName(String roleName);

    @Select("select * from role")
    public Vector<Role> getRoles();

    @Select("select * from role where id in (select role_id from user_role where user_id = #{id})")
    public Vector<Role> getRolesByUserId(Integer id);

    @Delete("delete from role where id =#{id}")
    public boolean deleteRoleById(Integer id);

    @InsertProvider(type = RoleDynaSqlProvider.class, method = "insertRole")
    public boolean insertRole(Role role);

    @UpdateProvider(type = RoleDynaSqlProvider.class, method = "updateRole")
    public boolean updateRole(Role role);

    @Insert("insert into user_role(id,user_id,role_id,type) values (null,#{userId},#{roleId},#{type})")
    public boolean insertRoleToUser(Integer userId, Integer roleId, Integer type);

}
