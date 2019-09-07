package cn.xtrui.database.interceptor;

import cn.xtrui.database.bean.Permission;
import cn.xtrui.database.bean.Role;
import cn.xtrui.database.bean.User;
import cn.xtrui.database.service.ApiService;
import cn.xtrui.database.service.PermissionService;
import cn.xtrui.database.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Vector;


public class ApiInterceptor implements HandlerInterceptor {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ApiService apiService;

    @Autowired
    private RoleService roleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String method = request.getMethod();
        Vector<Permission> permissions =(Vector<Permission>) request.getSession().getAttribute("permissions");
        User user = (User)request.getSession().getAttribute("user");
        if (user.getId() == 1){return true;}
        if(permissions == null){
            permissions = permissionService.getPermissionByUserId(user.getId());
            for (Permission p:permissions){
                if("POST".equals(method)){
                    if ("updateApi".equals(p.getName())){
                        flag = true; break;
                    }
                    else {
                        String id = request.getParameter("id");
                        Integer apiId = Integer.parseInt(id);
                        String username = apiService.getUsernameByApiIdandType(apiId,0);
                        if (username.equals(user.getUsername())){
                            int i= 1;
                            flag = true; break;
                        }
                    }

                }
                if("GET".equals(method) && "searchApi".equals(p.getName())){
                    Integer  status= Integer.parseInt(request.getParameter("status"));
                    if(status != 0){
                        Integer userId = user.getId();
                        Vector<Role> roles = roleService.getRolesByUserId(userId);
                        for(Role role: roles){
                            if (status == 2 && "apiTest".equals(role.getName())){
                                return true;
                            }
                            if (status == 1 && "apiVerify".equals(role.getName())){return true;}
                        }
                    }
                    flag = true; break;
                }
                if("DELETE".equals(method)){
                    if ("deleteApi".equals(p.getName())){
                    flag = true; break;
                    }
                    else {
                        String id = request.getParameter("id");
                        Integer apiId = Integer.parseInt(id);
                        String username = apiService.getUsernameByApiIdandType(apiId,0);

                        if (username.equals(user.getUsername())){
                            flag = true; break;
                        }
                    }
                }
                if("PUT".equals(method) &&  "addApi".equals(p.getName())){
                    flag = true; break;
                }
            }
        }
        if (flag){
            return true;
        }
        else {
            System.out.println("UerI跳转之前");
            request.getRequestDispatcher("/noPermission").forward(request, response);
            return false;
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
