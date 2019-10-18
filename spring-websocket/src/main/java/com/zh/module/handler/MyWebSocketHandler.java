package com.zh.module.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zh.module.biz.*;
import com.zh.module.model.ResultCode;
import com.zh.module.model.ResultObj;
import com.zh.module.model.WebSocketClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.ImmediateEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Resource
    private PingBiz pingBiz;
    @Resource(name = "baseBizImpl")
    private BaseBiz baseBiz;
    @Autowired
    private JoinBiz joinBiz;
    @Autowired
    private OrderBiz orderBiz;
    @Autowired
    private BroadCastBiz broadCastBiz;
    public static ChannelGroup group = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    public static Map<String, WebSocketClient> allSocketClients = new ConcurrentHashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道开启！");

        //添加到channelGroup通道组
        MyChannelHandlerPool.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端断开连接，通道关闭！");
        //添加到channelGroup 通道组
        MyChannelHandlerPool.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest，处理参数
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();

            Map paramMap=getUrlParams(uri);
            System.out.println("接收到的参数是："+ JSON.toJSONString(uri));
            //如果url包含参数，需要处理
            if(uri.contains("?")){
                String newUri=uri.substring(0,uri.indexOf("?"));
                System.out.println(newUri);
                request.setUri(newUri);
            }
        }else if(msg instanceof TextWebSocketFrame){
            //正常的TEXT消息类型
            TextWebSocketFrame frame=(TextWebSocketFrame)msg;
            Channel incoming = ctx.channel();
            String reciveMsg = frame.text();
//        log.info("websocket服务器收到数据：" + reciveMsg);
            try {
                JSONObject json = JSONObject.parseObject(reciveMsg);
                String action = json.getString("action");
                String cmsgCode = json.getString("cmsg_code");
                if (cmsgCode == null || "".equals(cmsgCode)) {
                    cmsgCode = "-1";
                }
                ResultObj resultObj = new ResultObj();
                resultObj.setCmsgCode(cmsgCode);
                switch (action) {
                    case "ping": {
                        pingBiz.ping(incoming, cmsgCode);
                        break;
                    }
                    case "join": {
                        JSONObject data = json.getJSONObject("data");
                        if (data == null) {
                            resultObj.setCode(ResultCode.TYPE_ERROR_PARAMS.code());
                            resultObj.setMsg(ResultCode.TYPE_ERROR_PARAMS.message());
                            baseBiz.sendMessage(incoming, resultObj);
                            return;
                        }
                        joinBiz.join(incoming, data, resultObj, allSocketClients);

                        break;
                    }
                    case "order": {
                        JSONObject data = json.getJSONObject("data");
                        if (data != null) {
                            orderBiz.orderBroadcast(data, allSocketClients);
                            return;
                        }

                        break;
                    }
                    case "broadcast": {
                        JSONObject data = json.getJSONObject("data");
                        if (action == null || "".equals(action) || data == null) {
                            resultObj.setCode(ResultCode.TYPE_ERROR_PARAMS.code());
                            resultObj.setMsg(ResultCode.TYPE_ERROR_PARAMS.message());
                            baseBiz.sendMessage(incoming, resultObj);
                            return;
                        }
                        broadCastBiz.broadCast(data, allSocketClients);
                        break;
                    }
                    default: {
                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                ResultObj resultObj = new ResultObj();
                resultObj.setMsg(ResultCode.TYPE_ERROR_UNKNOW.message());
                resultObj.setCode(ResultCode.TYPE_ERROR_UNKNOW.code());
                //baseBiz.sendMessage(incoming, resultObj);
                // ctx.close();
            }
        }
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
    }

    private static void sendAllMessage(String message){
        //收到信息后，群发给所有channel
        MyChannelHandlerPool.channelGroup.writeAndFlush( new TextWebSocketFrame(message));
    }

    private static Map getUrlParams(String url){
        Map<String,String> map = new HashMap<>();
        url = url.replace("?",";");
        if (!url.contains(";")){
            return map;
        }
        if (url.split(";").length > 0){
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr){
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key,value);
            }
            return  map;

        }else{
            return map;
        }
    }
}
