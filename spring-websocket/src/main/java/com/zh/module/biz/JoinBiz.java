package com.zh.module.biz;

import com.alibaba.fastjson.JSONObject;
import com.zh.module.model.ResultObj;
import com.zh.module.model.WebSocketClient;
import io.netty.channel.Channel;

import java.util.Map;

public interface JoinBiz {
    void join(Channel channel, JSONObject data, ResultObj resultObj, Map<String, WebSocketClient> allSocketClients);
}
