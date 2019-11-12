package com.zh.module.service;

import com.zh.module.entity.User;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author: autogeneration
 * @date: 2019-11-12 16:44:45
 **/ 
public interface UserService {
    /**
     * 添加
     * 
     * @author: autogeneration
     * @date: 2019-11-12 16:44:45
     **/ 
    int insert(User record);

    /**
     * 添加
     * 
     * @author: autogeneration
     * @date: 2019-11-12 16:44:45
     **/ 
    int insertSelective(User record);

    /**
     * 更新
     * 
     * @author: autogeneration
     * @date: 2019-11-12 16:44:45
     **/ 
    int updateByPrimaryKey(User record);

    /**
     * 更新
     * 
     * @author: autogeneration
     * @date: 2019-11-12 16:44:45
     **/ 
    int updateByPrimaryKeySelective(User record);

    /**
     * 删除
     * 
     * @author: autogeneration
     * @date: 2019-11-12 16:44:45
     **/ 
    int deleteByPrimaryKey(Integer id);

    /**
     * 按主键查询
     * 
     * @author: autogeneration
     * @date: 2019-11-12 16:44:45
     **/ 
    User selectByPrimaryKey(Integer id);

    /**
     * 条件查询
     * 
     * @author: autogeneration
     * @date: 2019-11-12 16:44:45
     **/ 
    List<User> selectAll(Map<Object, Object> param);

    /**
     * 分页查询
     * 
     * @author: autogeneration
     * @date: 2019-11-12 16:44:45
     **/ 
    List<User> selectPaging(Map<Object, Object> param);

    /**
     * 统计查询
     * 
     * @author: autogeneration
     * @date: 2019-11-12 16:44:45
     **/ 
    int selectCount(Map<Object, Object> param);
}