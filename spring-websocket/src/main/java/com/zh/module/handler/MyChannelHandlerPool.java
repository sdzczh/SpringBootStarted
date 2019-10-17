package com.zh.module.handler;

import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zhaohe
 * @create: 2019-10-17 16:13
 */
public class MyChannelHandlerPool {
    public MyChannelHandlerPool(){}

    public static Map<String, ChannelId> channelIdMap = new HashMap<>();

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
