package cn.xtrui.database;

import cn.xtrui.database.mapper.EmployeeMapper;
import cn.xtrui.database.mapper.PermissionMapper;
import cn.xtrui.database.mapper.RoleMapper;
import cn.xtrui.database.mapper.Usermapper;
import cn.xtrui.database.service.*;
import cn.xtrui.database.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTestApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    Usermapper usermapper;
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleService roleService;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    ApiService apiService;
    @Autowired
    FileService fileService;
    @Test
    public void contextLoads() throws SQLException {
        try {
            System.out.println(HttpUtil.getHttpContent("https://xtrui.cn",""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
