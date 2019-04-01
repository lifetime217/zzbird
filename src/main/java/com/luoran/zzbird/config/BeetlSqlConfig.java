package com.luoran.zzbird.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.IDAutoGen;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luoran.zzbird.utils.UUID;


/**
 * @author lifetime
 *
 */
@Configuration
public class BeetlSqlConfig {
	
	@Bean(name = "sqlManagerFactoryBean")
	@Profile("dev")
	public SqlManagerFactoryBean sqlManagerFactoryBean(@Qualifier("druidDataSource") DataSource datasource){
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		BeetlSqlDataSource  source = new BeetlSqlDataSource();
		source.setMasterSource(datasource);
		factory.setCs(source);
		factory.setDbStyle(new MySqlStyle());
		factory.setSqlLoader(new DevMdLoader("src/db/mysql/sql"));
		factory.setNc(new UnderlinedNameConversion());
		HashMap<String, IDAutoGen> idGens = new HashMap<>();
		idGens.put("uuid", new IDAutoGen<String>() {
			public String nextID(String params) {
				return UUID.get();
			}
		});
		factory.setIdAutoGens(idGens);
		factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
		return factory;
	}
	
	@Bean(name = "sqlManagerFactoryBean")
	@Profile({"pro","uat"})
	public SqlManagerFactoryBean sqlManagerFactoryBean2(@Qualifier("druidDataSource") DataSource datasource){
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		BeetlSqlDataSource  source = new BeetlSqlDataSource();
		source.setMasterSource(datasource);
		factory.setCs(source);
		factory.setDbStyle(new MySqlStyle());
		factory.setNc(new UnderlinedNameConversion());
		factory.setSqlLoader(new ClasspathLoader("/sql"));
		HashMap<String, IDAutoGen> idGens = new HashMap<>();
		idGens.put("uuid", new IDAutoGen<String>() {
			public String nextID(String params) {
				return UUID.get();
			}
		});
		factory.setIdAutoGens(idGens);
		return factory;
	}
	
	@Bean(name = "beetlSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
    	BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
    	conf.setBasePackage("com.luoran.zzbird.dao");
    	conf.setDaoSuffix("Dao");
    	conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
    	return conf;
    }
}
