package com.zh.module.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.zh.module.biz.BaseBiz;
import com.zh.module.model.ResultObj;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

@Component
public class BaseBizImpl implements BaseBiz {


    @Override
    public void sendError(Channel channel) {

    }

    @Override
    public void sendMessage(Channel incoming, ResultObj resultObj) {
        String json = JSONObject.toJSONString(resultObj);
        TextWebSocketFrame twsf = new TextWebSocketFrame(json);
        incoming.writeAndFlush(twsf);
    }
}
