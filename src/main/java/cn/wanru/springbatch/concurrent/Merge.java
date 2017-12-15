package cn.wanru.springbatch.concurrent;

/**
 * @author xxf
 * @since 2017/12/15
 */
public class Merge {

  public void merge() {
    System.out.println("......" + Thread.currentThread().getName() + "merge.....");
  }

  public void merge2() {
    System.out.println("......" + Thread.currentThread().getName() + "merge2..........");
  }
}
