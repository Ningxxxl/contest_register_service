package cn.tjpuacm.contest_register_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.tjpuacm.contest_register_service.**.dao")
public class ContestRegisterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContestRegisterServiceApplication.class, args);
    }
}
