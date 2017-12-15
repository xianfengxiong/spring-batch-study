package cn.wanru.springbatch.javaconfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ApplicationObjectSupport;

import java.util.Map;

/**
 * Created by xxf on 17-12-14.
 */
@ComponentScan
@Configuration
@EnableBatchProcessing
public class AppConfig extends ApplicationObjectSupport {

    @Autowired
    private JobLauncher launcher;

    @Bean
    public JobRepository jobRepository() throws Exception {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

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
        ApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = context.getBean(name);
            System.out.println("type=" + bean.getClass()+",name="+name);
        }
        Map<String, JobLauncher> jobLauncherMap = context.getBeansOfType(JobLauncher.class);
        System.out.println(jobLauncherMap);
        JobLauncher bean = context.getBean(JobLauncher.class);
        System.out.println(bean.toString());
        System.out.println(bean.getClass().getName());
        context.getBean(AppConfig.class).runJob();
    }


}
