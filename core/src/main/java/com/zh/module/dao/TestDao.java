package com.zh.module.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @description:
 * @author: zhaohe
 * @create: 2019-10-17 19:10
 */

@Mapper
public interface TestDao {

    String getTest(Integer id);
}
