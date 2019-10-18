package com.zh.module.biz;

import com.alibaba.fastjson.JSONObject;
import com.zh.module.model.WebSocketClient;

import java.util.Map;

public interface BroadCastBiz {
    void broadCast(JSONObject data, Map<String, WebSocketClient> allSocketClients);
}
