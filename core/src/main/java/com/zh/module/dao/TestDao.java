package com.zh.module.dao;

import org.apache.ibatis.annotations.Select;

/**
 * @description:
 * @author: zhaohe
 * @create: 2019-10-17 19:10
 */

public interface TestDao {

    @Select("select name from aaa where id = #{id}")
    String getTest(Integer id);
}
