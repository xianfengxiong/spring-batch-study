package cn.wanru.springbatch.other.tasklet;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;

/**
 * @author xxf
 * @since 2017/6/22
 */
public class ArchiveProductImportFileTasklet implements Tasklet {

  private String inputFile;

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    File archiveDir = new File("archive");
    FileUtils.forceMkdir(archiveDir);
    FileUtils.copyFileToDirectory(new File(inputFile),archiveDir);

    return RepeatStatus.FINISHED;
  }

  public String getInputFile() {
    return inputFile;
  }

  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }
}
