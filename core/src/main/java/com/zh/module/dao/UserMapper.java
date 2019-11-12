package com.zh.module.dao;

import com.zh.module.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKey(User record);

    int updateByPrimaryKeySelective(User record);

    int deleteByPrimaryKey(Integer id);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll(Map<Object, Object> param);

    List<User> selectPaging(Map<Object, Object> param);

    int selectCount(Map<Object, Object> param);
}