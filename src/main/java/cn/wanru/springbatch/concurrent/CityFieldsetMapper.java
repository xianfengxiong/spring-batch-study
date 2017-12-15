package cn.wanru.springbatch.concurrent;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @author xxf
 * @since 2017/12/15
 */
public class CityFieldsetMapper implements FieldSetMapper<City> {

  @Override
  public City mapFieldSet(FieldSet fieldSet) throws BindException {
    return new City(fieldSet.readInt(0),fieldSet.readInt(1),fieldSet.readString(2));
  }

}
