package cn.xtrui.database.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;



public class MyBatisConfig {
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer(){
            @Override
            public void customize(Configuration configuration){
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
