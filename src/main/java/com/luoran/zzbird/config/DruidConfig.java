package com.luoran.zzbird.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author lifetime
 *
 */
@Configuration
@EnableTransactionManagement
public class DruidConfig {

	@Autowired
	private Environment env;

	@Bean(name = "druidDataSource", destroyMethod = "close", initMethod = "init")
	public DataSource druidConfig() {
		DruidDataSource ds = new DruidDataSource();
		ds.setName(env.getProperty("druid.name", "druid-zzbird"));
		ds.setUrl(env.getProperty("druid.url"));
		ds.setUsername(env.getProperty("druid.username"));
		ds.setPassword(env.getProperty("druid.password"));
		ds.setInitialSize(env.getProperty("druid.initSize", Integer.class, 20));
		ds.setMaxActive(env.getProperty("druid.maxActive", Integer.class, 20));
		ds.setMinIdle(env.getProperty("druid.initSize", Integer.class, 20));
		ds.setValidationQuery(env.getProperty("druid.validationQuery", "select 0"));
		ds.setValidationQueryTimeout(env.getProperty("druid.validationQueryTimeout", Integer.class, 10000));
		ds.setTestWhileIdle(env.getProperty("druid.testWhileIdle", Boolean.class, true));
		ds.setMinEvictableIdleTimeMillis(env.getProperty("druid.minEvictableIdleTimeMillis", Long.class, 1200000L));// 连接保持空闲的最大时长：20分钟
		try {
			ds.setFilters(env.getProperty("druid.filters", "filter:log4j"));
		} catch (SQLException e) {
			System.err.println(" >>>>> druid filters init fail.");
		}
		return ds;
	}

	@Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("druidDataSource") DataSource datasource) {  
    	DataSourceTransactionManager dsm = new DataSourceTransactionManager();
    	dsm.setDataSource(datasource);
    	return dsm;
    }
	
	@Bean
	public ServletRegistrationBean duridServlet(){
		ServletRegistrationBean bean = new ServletRegistrationBean();
		bean.setServlet(new com.alibaba.druid.support.http.StatViewServlet());
		bean.addUrlMappings("/druid/*");
		bean.addInitParameter("loginUsername", env.getProperty("druid.monitor.username", "zzbird"));
		bean.addInitParameter("loginPassword", env.getProperty("druid.monitor.password", "zzbird123!@#"));
		return bean;
	}
	
}
