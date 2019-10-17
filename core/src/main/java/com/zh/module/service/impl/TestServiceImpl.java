package com.zh.module.service.impl;

import com.zh.module.dao.TestDao;
import com.zh.module.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhaohe
 * @create: 2019-10-17 19:10
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;
    @Override
    public String getName(int id) {
        return testDao.getTest(id);
    }
}
