package cn.xtrui.database.config;

import cn.xtrui.database.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyServerConfig  {
    //注册三大组件


    /**
     * 注册Filter
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/**","/myServlet"));
        Map<String, String> map = new HashMap<>();
        map.put("encoding", "utf-8");
        registrationBean.setInitParameters(map);
        return registrationBean;
    }




}
