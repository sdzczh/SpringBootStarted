package com.zh.module.service;

import com.zh.module.entity.Users;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author: autogeneration
 * @date: 2019-12-19 18:31:27
 **/ 
public interface UsersService {
    /**
     * 添加
     * 
     * @author: autogeneration
     * @date: 2019-12-19 18:31:27
     **/ 
    int insert(Users record);

    /**
     * 添加
     * 
     * @author: autogeneration
     * @date: 2019-12-19 18:31:27
     **/ 
    int insertSelective(Users record);

    /**
     * 更新
     * 
     * @author: autogeneration
     * @date: 2019-12-19 18:31:27
     **/ 
    int updateByPrimaryKey(Users record);

    /**
     * 更新
     * 
     * @author: autogeneration
     * @date: 2019-12-19 18:31:27
     **/ 
    int updateByPrimaryKeySelective(Users record);

    /**
     * 删除
     * 
     * @author: autogeneration
     * @date: 2019-12-19 18:31:27
     **/ 
    int deleteByPrimaryKey(Integer id);

    /**
     * 按主键查询
     * 
     * @author: autogeneration
     * @date: 2019-12-19 18:31:27
     **/ 
    Users selectByPrimaryKey(Integer id);

    /**
     * 条件查询
     * 
     * @author: autogeneration
     * @date: 2019-12-19 18:31:27
     **/ 
    List<Users> selectAll(Map<Object, Object> param);

    /**
     * 分页查询
     * 
     * @author: autogeneration
     * @date: 2019-12-19 18:31:27
     **/ 
    List<Users> selectPaging(Map<Object, Object> param);

    /**
     * 统计查询
     * 
     * @author: autogeneration
     * @date: 2019-12-19 18:31:27
     **/ 
    int selectCount(Map<Object, Object> param);

    Users login(Users user);
}