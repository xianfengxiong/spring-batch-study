package cn.wanru.springbatch.simple.processor;

import cn.wanru.springbatch.simple.model.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xxf
 * @since 2017/12/14
 */
public class ProductItemProcessor implements ItemProcessor<Product,Product> {

  private static final String GET_PRODUCT = "select id,name,description,quantity from product where id = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Product process(Product product) throws Exception {
    List<Product> productList = jdbcTemplate.query(GET_PRODUCT, new Object[]{product.getId()}, new RowMapper<Product>() {
      @Override
      public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product p = new Product();
        p.setId(rs.getInt(1));
        p.setName(rs.getString(2));
        p.setDescription(rs.getString(3));
        p.setQuantity(rs.getInt(4));
        return p;
      }
    });

    if (productList.size() > 0) {
      Product existingProduct = productList.get(0);
      product.setQuantity(existingProduct.getQuantity()+product.getQuantity());
    }

    return product;
  }

}
