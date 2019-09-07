package cn.xtrui.database.config;

import cn.xtrui.database.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Bean
    public  LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
    @Bean
    public  UserInterceptor userInterceptor(){
        return new UserInterceptor();
    }
    @Bean
    public RoleInterceptor roleInterceptor(){
        return new RoleInterceptor();
    }
    @Bean
    public PermissionInterceptor permissionInterceptor(){
        return new PermissionInterceptor();
    }
    @Bean
    public FileInterceptor fileInterceptor(){ return new FileInterceptor(); }
    @Bean
    public VerifyAndTestInterceptor verifyAndTestInterceptor(){ return  new VerifyAndTestInterceptor(); }
    @Bean
    public ApiInterceptor apiInterceptor(){return new ApiInterceptor();}
    @Bean
    public LogInterceptor logInterceptor(){return  new LogInterceptor();}
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<>();
        list.add("/Login.html");
        list.add("/admin");
        list.add("/file/**");
        list.add("/login");
        list.add("/register");
        list.add("/getValidateCode");

        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**").excludePathPatterns(list);

        registry.addInterceptor(userInterceptor())
                .addPathPatterns("/user/**");

        registry.addInterceptor(roleInterceptor())
                .addPathPatterns("/role/**");

        registry.addInterceptor(permissionInterceptor())
                .addPathPatterns("/permission/**");

        registry.addInterceptor(fileInterceptor())
                .addPathPatterns("/file/**");

        registry.addInterceptor(verifyAndTestInterceptor())
                .addPathPatterns(Arrays.asList("/test/**","/verify/**"));

        registry.addInterceptor(apiInterceptor())
                .addPathPatterns("api/**");
        registry.addInterceptor(logInterceptor())
                .addPathPatterns("/log/**");

    }
}
