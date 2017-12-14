package cn.wanru.springbatch.other;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author xxf
 * @since 2017/6/22
 */
@ComponentScan
@Configuration
public class App {

  public static void main(String[] args) {
    System.out.println("hello world");
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);

  }

  @Configuration
  protected static class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {
      PoolProperties poolProperties = new PoolProperties();
      poolProperties.setName("root");
      poolProperties.setPassword("0000");
      poolProperties.setUrl("jdbc:mysql://localhost:3306/springbatch");
      poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
      poolProperties.setTestOnBorrow(true);
      poolProperties.setTestWhileIdle(true);
      poolProperties.setValidationQuery("select 1");
      poolProperties.setValidationInterval(30000);
      poolProperties.setTimeBetweenEvictionRunsMillis(30000);
      return new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
    }

  }

  @Configuration
  protected static class JdbcTemplateConfiguration {

    @Autowired(required = false)
    private DataSource dataSource;

    @Bean
    private JdbcTemplate jdbcTemplate() {
      return new JdbcTemplate(dataSource);
    }

  }

  @Configuration
  protected static class TransactionManagerConfiguration {

    @Autowired(required = false)
    private DataSource dataSource;

    @Bean
    private PlatformTransactionManager transactionManager() {
      return new DataSourceTransactionManager(dataSource);
    }

  }

  @Configuration
  protected static class JobRepositoryConfiguration {

    @Autowired(required = false)
    private PlatformTransactionManager transactionManager;

    @Bean
    private MapJobRepositoryFactoryBean jobRepository() {
      return new MapJobRepositoryFactoryBean(transactionManager);
    }

  }

  @Configuration
  protected static class JobLauncherConfiguration {

    @Autowired(required = false)
    private JobRepository jobRepository;

    @Bean
    public JobLauncher jobLauncher() {
      SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
      jobLauncher.setJobRepository(jobRepository);
      return jobLauncher;
    }

  }

}
