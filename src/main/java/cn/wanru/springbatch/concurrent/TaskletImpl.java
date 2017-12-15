package cn.wanru.springbatch.concurrent;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author xxf
 * @since 2017/12/15
 */
public class TaskletImpl implements Tasklet {

  @Override
  public RepeatStatus execute(StepContribution contribution,
         ChunkContext chunkContext) throws Exception {
    return RepeatStatus.FINISHED;
  }

}
