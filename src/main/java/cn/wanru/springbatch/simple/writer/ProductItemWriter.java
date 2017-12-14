package cn.wanru.springbatch.simple.writer;

import cn.wanru.springbatch.simple.model.Product;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xxf
 * @since 2017/6/22
 */
public class ProductItemWriter implements ItemWriter<Product> {

  private static final String GET_PRODUCT="select * from product where id = ?";
  private static final String INSERT_PRODUCT="insert into product(id,name,description,quantity) values(?,?,?,?)";
  private static final String UPDATE_PRODUCT="update product set name = ? ,description = ? ,quantity = ? where id = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void write(List<? extends Product> list) throws Exception {
    for (Product product : list) {
      List<Product> products = jdbcTemplate.query(GET_PRODUCT, new Object[]{product.getId()}, new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
          Product p =new Product();
          p.setId(rs.getInt(0));
          p.setName(rs.getString(1));
          p.setDescription(rs.getString(2));
          p.setQuantity(rs.getInt(3));
          return p;
        }
      });

      if (products.size() > 0) {
        jdbcTemplate.update(UPDATE_PRODUCT,product.getName(),product.getDescription(),product.getQuantity(),product.getId());
      }else{
        jdbcTemplate.update(INSERT_PRODUCT,product.getId(),product.getName(),product.getDescription(),product.getQuantity());
      }
    }
  }
}
