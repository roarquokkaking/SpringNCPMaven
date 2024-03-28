package spring.conf;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@PropertySource("classpath:spring/db.properties")
@EnableTransactionManagement
//@MapperScan("user.dao") // root-context 에 있는 scan 이랑 같은 거 둘중 하나만 써도됨 
public class SpringConfiguration {
	
	@Value("${jdbc.driver}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	
	
	@Bean
	public BasicDataSource dataSource(){
		
		BasicDataSource basicDataSource =new BasicDataSource();
		basicDataSource.setDriverClassName(driverClassName);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		
		return basicDataSource;
		
	}
	
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); 
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
		//sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("mapper/userMapper.xml"));
		//sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("mapper/userMapper.xml"),new ClassPathResource("mapper/userUploadMapper.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*Mapper.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("user.bean");
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
//		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate( sqlSessionFactory());
//		return sqlSessionTemplate;
		
		return new SqlSessionTemplate( sqlSessionFactory());
	}
	
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
//		DataSourceTransactionManager dataSourceTransactionManager =new DataSourceTransactionManager(dataSource());
//		return dataSourceTransactionManager;
		return new DataSourceTransactionManager(dataSource());
	}
	
}
