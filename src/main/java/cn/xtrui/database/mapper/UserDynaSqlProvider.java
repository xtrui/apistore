package cn.xtrui.database.mapper;

import cn.xtrui.database.bean.User;
import org.apache.ibatis.jdbc.SQL;

public class UserDynaSqlProvider {
    public String insertUser(User user){
       return new SQL(){
           {
               INSERT_INTO("user");
               VALUES("id,username,password,email,introduction","null,#{username},#{password},#{email},#{introduction}");

           }
       }.toString();
    }


    public String updateUser(User user){
        return new SQL(){
            {
                UPDATE("user");
                if (!(user.getUsername() == null || user.getUsername()== "")) {
                    SET("username=#{username}");
                }
                if(!(user.getPassword() == null || user.getPassword() == "")) {
                    SET("password = #{password}");
                }
                if(!(user.getEmail() == null ||user.getEmail() == "")){
                    SET("email = #{email}");
                }
                if (!(user.getIntroduction() == null || user.getIntroduction() == "")){
                    SET("introduction = #{introduction}");
                }
                WHERE("id = #{id}");

            }
        }.toString();
    }
}
