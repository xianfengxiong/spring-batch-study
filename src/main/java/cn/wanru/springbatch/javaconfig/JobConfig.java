package cn.wanru.springbatch.javaconfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * Created by xxf on 17-12-14.
 */
@Configuration
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobBuilder;

    @Autowired
    private StepBuilderFactory stepBuilder;

    @Bean
    public TaskExecutor executor() {
        return new SimpleAsyncTaskExecutor("batch-");
    }

    @Bean
    public Job job() {
        /*
         *          step1
         *
         *   step2A     step2B
         *
         *          step3
         */
        Flow flow2A = new FlowBuilder<Flow>("flow2A").start(step2A()).build();
        Flow flow2B = new FlowBuilder<Flow>("flow2B").start(step2B()).build();

        return jobBuilder.get("job1")
                .start(step1())
                .split(executor())
                .add(flow2A,flow2B)
                .next(step3())
                .build().build();
    }

    @Bean
    public Step step1() {
        return step("step 1");
    }

    @Bean
    public Step step2A() {
        return step("step 2A");
    }

    @Bean
    public Step step2B() {
        return step("step 2B");
    }

    @Bean
    public Step step3() {
        return step("step 3");
    }


    private Step step(String name) {
        return stepBuilder.get(name).tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println(name + " executing...");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }



}
