package cn.wanru.springbatch.javaconfig;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by xxf on 17-12-14.
 */
@ComponentScan
@Configuration
@EnableBatchProcessing
public class AppConfig extends ApplicationObjectSupport {

//    @Autowired
//    private JobRepository jobRepository;

    @Autowired
    private JobLauncher launcher;

    @Bean
    public JobRepository jobRepository() throws Exception {
        MapJobRepositoryFactoryBean result = new MapJobRepositoryFactoryBean();
//        result.setTransactionManager(transactionManager());
        return result.getObject();
    }

//    @Bean
//    public DataSource dataSource() {
//        DataSource dataSource = new DataSource();
//        dataSource.setName("Tomcat-DataSource");
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost/springbatch");
//        dataSource.setUsername("caigin");
//        dataSource.setPassword("password");
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new ResourcelessTransactionManager();
//    }

    private ExitCodeMapper exitCodeMapper = new SimpleJvmExitCodeMapper();

    void runJob() {
        try {
            Job job = getApplicationContext().getBean(Job.class);
            JobParameters jobParameters = new JobParameters();
            JobExecution jobExecution = launcher.run(job, jobParameters);
            int exitCode = exitCodeMapper.intValue(jobExecution.getExitStatus().getExitCode());
            System.exit(exitCode);
        }catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(AppConfig.class).runJob();
    }


}
