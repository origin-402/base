package com.jd.em.base.config;


import com.jd.em.base.plugin.PagePlugin;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @author KING
 *
 * <p>Description: mybatis</p>
 *
 * 2019年1月21日
 *
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {
	   @Resource(name = "dataSource")
	    DataSource dataSource;

	    @Bean(name = "sqlSessionFactory")
	    public SqlSessionFactory sqlSessionFactoryBean() {
	        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	        VFS.addImplClass(SpringBootVFS.class);
	        bean.setDataSource(dataSource);
	        bean.setTypeAliasesPackage("com.jd.em.base.domain");
	        PagePlugin plu = new PagePlugin();
	        bean.setPlugins(new Interceptor[]{plu});
	        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        try {
	            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
	            return bean.getObject();
	        } catch (Exception e) {
	            throw new RuntimeException("sqlSessionFactory init fail",e);
	        }
	    }

	    /**
	     * 用于实际查询的sql工具,传统dao开发形式可以使用这个,基于mapper代理则不需要注入
	     * @param sqlSessionFactory
	     * @return
	     */
	    @Bean(name = "sqlSessionTemplate")
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }
	    /**
	     * 事务管理,具体使用在service层加入@Transactional注解
	     */
	    @Bean(name = "transactionManager")
	    @Override
	    public PlatformTransactionManager annotationDrivenTransactionManager() {
	        return new DataSourceTransactionManager(dataSource);
	    }
}