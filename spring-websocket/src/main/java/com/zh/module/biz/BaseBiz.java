package com.zh.module.biz;

import com.zh.module.model.ResultObj;
import io.netty.channel.Channel;

public interface BaseBiz {
    void sendError(Channel channel);

    void sendMessage(Channel incoming, ResultObj resultObj);
}
