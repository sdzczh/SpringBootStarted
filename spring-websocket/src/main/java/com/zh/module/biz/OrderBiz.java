package com.zh.module.biz;

import com.alibaba.fastjson.JSONObject;
import com.zh.module.model.WebSocketClient;

import java.util.Map;

public interface OrderBiz {
    void orderBroadcast(JSONObject data, Map<String, WebSocketClient> allSocketClients);
}
