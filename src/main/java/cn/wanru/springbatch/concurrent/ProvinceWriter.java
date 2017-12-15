package cn.wanru.springbatch.concurrent;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author xxf
 * @since 2017/12/15
 */
public class ProvinceWriter implements ItemWriter<Provice> {

  @Override
  public void write(List<? extends Provice> items) throws Exception {
    System.out.println("=========== writer province " + Thread.currentThread().getName() + items.size() + " ==========");
  }

}
