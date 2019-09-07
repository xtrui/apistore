package cn.xtrui.database.service;

import cn.xtrui.database.bean.User;
import cn.xtrui.database.mapper.Usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Vector;

import static cn.xtrui.database.util.Constant.NUM_PER_PAGE;
@Component
public class UserService {
    @Autowired
    private Usermapper usermapper;

    /**
     * 分页查找user
     * @param page
     * @return
     */
    public Vector<User> searchUserByPage(Integer page){
        int count = usermapper.getCount();
        if(page > count/NUM_PER_PAGE + 1){
            page = 1;
        }
        if(page < 1){
            page = count/NUM_PER_PAGE + 1;
        }
        return usermapper.getUsers(page);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    public boolean updateUser(User user){
        return usermapper.updateUser(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public boolean deleteUser(Integer id) {
        return usermapper.deleteUserById(id);
    }

    public  boolean insertUser(User user){
        return usermapper.register(user);
    }

}
