package cn.xtrui.database;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "cn.xtrui.database.mapper")
@SpringBootApplication()
public class DatabaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseTestApplication.class, args);
    }

}
