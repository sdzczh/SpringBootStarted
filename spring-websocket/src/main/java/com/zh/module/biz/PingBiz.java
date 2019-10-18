package com.zh.module.biz;

import io.netty.channel.Channel;

public interface PingBiz {
    void ping(Channel channel, String cmsgCode);
}
