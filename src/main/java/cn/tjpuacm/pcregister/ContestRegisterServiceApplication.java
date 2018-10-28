package cn.tjpuacm.pcregister;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author ningxy
 */
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "cn.tjpuacm.pcregister.**.dao")
public class ContestRegisterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContestRegisterServiceApplication.class, args);
    }
}
