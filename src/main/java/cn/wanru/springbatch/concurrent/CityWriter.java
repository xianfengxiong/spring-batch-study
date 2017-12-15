package cn.wanru.springbatch.concurrent;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author xxf
 * @since 2017/12/15
 */
public class CityWriter implements ItemWriter<City> {

  @Override
  public void write(List<? extends City> items) throws Exception {
    System.out.println("===== write city "+ Thread.currentThread().getName() + items.size()  +" ==========");
  }

}
