package cn.xtrui.database.interceptor;

import cn.xtrui.database.bean.Permission;
import cn.xtrui.database.bean.User;
import cn.xtrui.database.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Vector;

public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private PermissionService permissionService;

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
                    if( "update".equals(p.getName())) {
                        flag = true;
                        break;
                    }
                    Integer userId = Integer.parseInt(request.getParameter("id"));
                    if (user.getId() == userId){
                        flag = true;
                        break;
                    }
                }
                if("GET".equals(method) && "search".equals(p.getName())){
                    flag = true; break;
                }
                if("DELETE".equals(method) && "delete".equals(p.getName())){
                    flag = true; break;
                }
                if("PUT".equals(method)){
                    if( "add".equals(p.getName())) {
                        flag = true;
                        break;
                    }
                    Integer userId = Integer.parseInt(request.getParameter("id"));
                    if (user.getId() == userId){
                        flag = true;
                        break;
                    }
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
