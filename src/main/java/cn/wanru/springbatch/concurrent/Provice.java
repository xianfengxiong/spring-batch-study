package cn.wanru.springbatch.concurrent;

/**
 * @author xxf
 * @since 2017/12/15
 */
public class Provice {

  private int id;
  private String name;

  public Provice() {

  }

  public Provice(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append("id=").append(id).append(",")
        .append("name").append(name).toString();
  }
}
