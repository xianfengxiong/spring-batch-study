package cn.wanru.springbatch.concurrent;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @author xxf
 * @since 2017/12/15
 */
public class ProviceFieldSetMapper implements FieldSetMapper<Provice> {

  @Override
  public Provice mapFieldSet(FieldSet fieldSet) throws BindException {
    return new Provice(fieldSet.readInt(0),fieldSet.readString(1));
  }

}
