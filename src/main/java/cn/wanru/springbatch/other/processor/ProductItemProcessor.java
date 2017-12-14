package cn.wanru.springbatch.other.processor;

import cn.wanru.springbatch.simple.model.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author xxf
 * @since 2017/6/22
 */
@Component
public class ProductItemProcessor implements ItemProcessor<Product, Product> {

  private static final String GET_PRODUCT = "select * from product where id = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Product process(Product product) throws Exception {
    List<Product> products = jdbcTemplate.query(GET_PRODUCT, new Object[]{product.getId()}, (ResultSet rs, int rowNum) -> {
          Product p = new Product();
          p.setId(rs.getInt(1));
          p.setName(rs.getString(2));
          p.setDescription(rs.getString(3));
          p.setQuantity(rs.getInt(4));
          return p;
        });

    if (products.size() > 0) {
      Product existProduct = products.get(0);
      product.setQuantity(existProduct.getQuantity() + product.getQuantity());
    }

    return product;
  }
}
