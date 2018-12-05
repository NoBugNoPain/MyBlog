package com.yuan.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@Configuration
@PropertySource("classpath:dataSource.properties")
@MapperScan("com.yuan.mapper")
@ComponentScan(
        basePackages={"com.yuan"},
        excludeFilters={//设置不扫描的文件，这里会排除springMVC扫描过的包
                @Filter(type=FilterType.ANNOTATION, value= EnableWebMvc.class)
        }
)
@EnableTransactionManagement
public class RootConfig {

    @Autowired
    Environment env;

    @Bean(value="dataSource",initMethod="init",destroyMethod="close")
    public  DruidDataSource druidDataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setDriverClassName(env.getProperty("jdbc.driver"));
        ds.setUsername(env.getProperty("jdbc.userName"));
        ds.setPassword(env.getProperty("jdbc.password"));
        return ds;
    }

    @Bean
    public SqlSessionFactoryBean sqlSession() throws IOException {
        SqlSessionFactoryBean sqlS = new SqlSessionFactoryBean();
        sqlS.setDataSource(druidDataSource());
        sqlS.setTypeAliasesPackage("com.yuan.model");
        return sqlS;
    }

    @Bean
    public DataSourceTransactionManager setDataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(druidDataSource());
        return dataSourceTransactionManager;
    }
}
