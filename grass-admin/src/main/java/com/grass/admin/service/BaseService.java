package com.grass.admin.service;

import com.grass.common.page.PageQuery;
import com.grass.common.page.PageResult;

public interface BaseService<T,V> {

    T get(V id);

    V add(T t);

    int update(T t);

    int delete(V id);

    PageResult<T> list(PageQuery<T> pageQuery);

}
