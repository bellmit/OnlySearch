package application;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wtl
 * @program: SpringBoot-Gateway-Sentinel
 * @description: main函数入口
 * @date 2020-03-11 11:18:10
 */
@SpringBootApplication
@EnableFeignClients
@EnableAutoDataSourceProxy
@MapperScan(basePackages = "application.mybatis.mappers")
@ServletComponentScan(basePackages = "application")
@EnableScheduling
public class AppSpringBootMain extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AppSpringBootMain.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppSpringBootMain.class);
    }
}
