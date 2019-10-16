package com.zh.module.handler;

import com.zh.module.model.WebSocketClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.ImmediateEventExecutor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Log4j2
@ChannelHandler.Sharable
public class TextWebSocketFrameHandler extends
        SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    public static Map<String, WebSocketClient> allSocketClients = new ConcurrentHashMap<>();

    /**
     * 客户端发来消息时触发
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
            throws Exception {
        Channel incoming = ctx.channel();
        String reciveMsg = msg.text();
        log.info("websocket服务器收到数据：" + reciveMsg);

    }



    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
        Channel incoming = ctx.channel();
		/*group.writeAndFlush(new TextWebSocketFrame("[SERVER] - "
				+ incoming.remoteAddress() + " 离开"));*/
        log.info("Client:" + incoming.remoteAddress().toString() + "离开,当前链接数："
                + group.size());
        allSocketClients.remove(incoming.remoteAddress().toString());
        incoming.close();
        // Broadcast a message to multiple Channels
        // A closed Channel is automatically removed from ChannelGroup,
        // so there is no need to do "channels.remove(ctx.channel());"

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) // (7)
            throws Exception {
        Channel incoming = ctx.channel();
        log.info("Client:" + incoming.remoteAddress() + "异常");
        allSocketClients.remove(incoming.remoteAddress().toString());
        cause.printStackTrace();
        //sendMessage(incoming, Result.toResultStr(ResultCode.TYPE_ERROR_UNKNOW));
    }


}