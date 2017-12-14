package cn.wanru.springbatch.other;

import cn.wanru.springbatch.simple.model.Product;
import cn.wanru.springbatch.simple.reader.ProductFieldSetMapper;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author xxf
 * @since 2017/6/22
 */
@Configuration
public class ProductJobConfig {

  @Bean(name="productReader")
  public FlatFileItemReader<Product> itemReader() {
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(",");
    lineTokenizer.setNames(new String[]{"id","name","description","quantity"});

    DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(new ProductFieldSetMapper());

    FlatFileItemReader<Product> itemReader = new FlatFileItemReader<>();
    itemReader.setResource(new ClassPathResource(""));
    itemReader.setLinesToSkip(1);
    itemReader.setLineMapper(lineMapper);

    return itemReader;
  }




}
