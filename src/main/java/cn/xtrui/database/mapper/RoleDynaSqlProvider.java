package cn.xtrui.database.mapper;

import cn.xtrui.database.bean.Role;
import org.apache.ibatis.jdbc.SQL;

public class RoleDynaSqlProvider {
    public String insertRole(Role role){
        return new SQL(){
            {
                INSERT_INTO("role");
                VALUES("id,info,name","null,#{info},#{name}");

            }
        }.toString();
    }

    public String updateRole(Role role){
        return new SQL(){
            {
                String name = role.getName();
                String info = role.getInfo();
                UPDATE("role");
                if (!(name == null || name == "")) {
                    SET("name=#{name}");
                }
                if(!(info == null || info == "")) {
                    SET("info = #{info}");
                }

                WHERE("id = #{id}");

            }
        }.toString();
    }
}
