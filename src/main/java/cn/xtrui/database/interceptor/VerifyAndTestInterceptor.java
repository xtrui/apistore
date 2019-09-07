package cn.xtrui.database.interceptor;

import cn.xtrui.database.bean.Role;
import cn.xtrui.database.bean.User;
import cn.xtrui.database.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Vector;

public class VerifyAndTestInterceptor implements HandlerInterceptor {
    @Autowired
    private RoleService roleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        requestURI = requestURI.substring(1,requestURI.lastIndexOf("/")-1);
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        Vector<Role> roles = roleService.getRolesByUserId(userId);
        if(userId == 1) {return true;}
        for(Role role: roles){
            if ("test".equals(requestURI) && "apiTest".equals(role.getName())){
                return true;
            }
            if ("verify".equals(requestURI) && "apiVerify".equals(role.getName())){return true;}
        }
        request.getRequestDispatcher("/noPermission").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
