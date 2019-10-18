package com.zh.module.biz.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zh.module.biz.JoinBiz;
import com.zh.module.constants.CoinType;
import com.zh.module.constants.GlobalParams;
import com.zh.module.constants.SystemParams;
import com.zh.module.enums.EnumScene;
import com.zh.module.model.ResultCode;
import com.zh.module.model.ResultObj;
import com.zh.module.model.WebSocketClient;
import com.zh.module.utils.HTTP;
import com.zh.module.utils.RedisUtil;
import com.zh.module.variables.RedisKey;
import io.netty.channel.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Component
@Transactional
@Log4j2
public class JoinBizImpl extends BaseBizImpl implements JoinBiz {
    @Resource
    private RedisTemplate<String, String> redis;


    @Override
    public void join(Channel channel, JSONObject data, ResultObj resultObj, Map<String, WebSocketClient> allSocketClients) {
        int scene = data.getIntValue("scene");
        int gear = data.getIntValue("gear");
        int c1 = data.getIntValue("c1");
        int c2 = data.getIntValue("c2");
        resultObj.setScene(scene);
        if (scene == 0) {
            resultObj.setMsg(ResultCode.TYPE_ERROR_PARAMS.message());
            resultObj.setCode(ResultCode.TYPE_ERROR_PARAMS.code());
            sendMessage(channel, resultObj);
            return;
        }
        String comingIp = channel.remoteAddress().toString();
        WebSocketClient wsc = allSocketClients.get(comingIp);
        if (wsc == null) {
            wsc = new WebSocketClient(comingIp, scene, gear, c1, c2);
            wsc.setChannel(channel);
            wsc.setScene(scene);
            wsc.setGear(gear);
            wsc.setC1(c1);
            wsc.setC2(c2);
            allSocketClients.put(comingIp, wsc);
        } else {
            wsc.setChannel(channel);
            wsc.setScene(scene);
            wsc.setGear(gear);
            wsc.setC1(c1);
            wsc.setC2(c2);
        }
        resultObj.setCode(ResultCode.TYPE_SUCCESS_JOIN.code());
        resultObj.setMsg(ResultCode.TYPE_SUCCESS_JOIN.message());
        sendMessage(channel, resultObj);
    }

}
