package cn.tjpuacm.pcregister.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author ningxy
 * @date 2018-10-17 14:32
 */
@Slf4j
@Configuration
@PropertySource("classpath:application.yml")
public class MybatisConfiguration {
    @Autowired
    private DataSource druidDataSource;

    @Value("${mapperLocations}")
    private String mapperLocations;

    @Value("${default-statement-timeout}")
    private int defaultStatementTimeout;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        log.info("sqlSessionFactory:--->mybatis.mapperLocation:" + mapperLocations);

        sqlSessionFactoryBean.setDataSource(druidDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setDefaultStatementTimeout(defaultStatementTimeout);
        log.info("sqlSessionFactoryBean:-->" + sqlSessionFactoryBean.getObject());
        log.info("default-statement-timeout:" + defaultStatementTimeout);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }
}
