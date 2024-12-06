package com.kattyavar.shika.order_service.utils;

import java.util.Collections;
import java.util.List;

public class ListUtils {

  public static <T> List<T> emptyIfNull(List<T> list) {
    return list != null ? list : Collections.emptyList();
  }
}
