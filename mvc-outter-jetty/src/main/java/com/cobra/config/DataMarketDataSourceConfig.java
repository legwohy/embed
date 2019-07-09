package com.cobra.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@MapperScan(basePackages = "com.cobra.dao.datamarket", sqlSessionTemplateRef = "dataMarketSqlSessionTemplate")
public class DataMarketDataSourceConfig {

    @Value("${spring.datasource.data.market.url}")
    private String url;

    @Value("${spring.datasource.data.market.username}")
    private String user;

    @Value("${spring.datasource.data.market.password}")
    private String password;

    @Value("${spring.datasource.data.market.driver-class-name}")
    private String driverClass;

    @Bean(name = "dataMarketDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.data.market")
    public DataSource setDataSource() {
        DruidDataSource dataMarketDataSource = new DruidDataSource();
        dataMarketDataSource.setDriverClassName(driverClass);
        dataMarketDataSource.setUrl(url);
        dataMarketDataSource.setUsername(user);
        dataMarketDataSource.setPassword(password);
        return dataMarketDataSource;
    }

    @Bean(name = "dataMarketTransactionManager")
    public DataSourceTransactionManager setTransactionDataSourceManager(@Qualifier("dataMarketDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dataMarketSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("dataMarketDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/datamarket//*.xml"));


        // 分页插件 5.0使用PageInterceptor
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("helperDialect","mysql");
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);
        bean.setPlugins(new Interceptor[]{pageInterceptor});
        return bean.getObject();
    }

    @Bean(name = "dataMarketSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("dataMarketSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
