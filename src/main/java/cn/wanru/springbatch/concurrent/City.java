package cn.wanru.springbatch.concurrent;

/**
 * @author xxf
 * @since 2017/12/15
 */
public class City {

  private int id;
  private int pid;
  private String name;

  public City() {
  }

  public City(int id, int pid, String name) {
    this.id = id;
    this.pid = pid;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPid() {
    return pid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
