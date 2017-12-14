package cn.wanru.springbatch.simple.reader;

import cn.wanru.springbatch.simple.model.Product;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * 将一行映射成一个对象
 *
 * @author xxf
 * @since 2017/6/22
 */
public class ProductFieldSetMapper implements FieldSetMapper<Product> {

  @Override
  public Product mapFieldSet(FieldSet fieldSet) throws BindException {
    Product product = new Product();
    product.setId(fieldSet.readInt("id"));
    product.setName(fieldSet.readString("name"));
    product.setDescription(fieldSet.readString("description"));
    product.setQuantity(fieldSet.readInt("quantity"));
    return product;
  }

}
