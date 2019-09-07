package cn.xtrui.database.mapper;

import cn.xtrui.database.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.Vector;

@Mapper
public interface Usermapper {

    @Select("select count(*) from user")
    public int getCount();

    @Select("select * from userwp where id between 10* (#{page}-1) and 10*#{page}")
    public Vector<User> getUsers(Integer page);

    @Select("select * from user where username = #{username}")
    public User getUserByName(String username);

    @Delete("delete from user where id =#{id}")
    public boolean deleteUserById(Integer id);

    @Select("select * from user where username = #{username} and password = #{password}")
    public User login(String username, String password);

    @InsertProvider(type = UserDynaSqlProvider.class, method = "insertUser")
    public boolean register(User user);

    /**
     * 改密码
     */
    @Update("update user set password=#{password} where username =#{username}")
    public int updatePasswordByName(User user);

    /**
     * 修改用户信息
     */
    @UpdateProvider(type = UserDynaSqlProvider.class, method = "updateUser")
    public boolean updateUser(User user);

}
