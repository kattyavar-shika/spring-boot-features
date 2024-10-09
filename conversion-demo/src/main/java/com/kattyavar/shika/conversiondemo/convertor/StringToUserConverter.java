package com.kattyavar.shika.conversiondemo.convertor;

import com.kattyavar.shika.conversiondemo.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserConverter implements Converter<String, User> {

  @Override
  public User convert(String source) {
    String[] parts = source.split(",");
    User user = new User();
    user.setName(parts[0]);
    user.setAge(Integer.parseInt(parts[1]));
    return user;
  }
}
