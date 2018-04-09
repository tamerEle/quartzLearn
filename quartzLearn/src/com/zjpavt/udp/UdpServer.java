package com.zjpavt.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpServer {
    private static final Logger log = LoggerFactory.getLogger(UdpServer.class);
    private UdpServer(){
    }
    public static class InnerClass{
        public static final UdpServer INSTANCE = new UdpServer();
    }
    private void initServer(int port){
        final NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioDatagramChannel.class);
        bootstrap.group(nioEventLoopGroup);
        bootstrap.handler(new ChannelInitializer<NioDatagramChannel>() {
            @Override
            protected void initChannel(NioDatagramChannel ch) throws Exception {
                ch.pipeline().addLast(new UdpServerHandler());
            }
        });
    }

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String OUTPUT_UDP_SERVER_IP = "127.0.0.1";

    private class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
            ByteBuf byteBuf = msg.copy().content();
            byte[] bytes = new  byte[byteBuf.readableBytes()];
            String res = new String(bytes,DEFAULT_ENCODING);
            log.trace(res);
            if(OUTPUT_UDP_SERVER_IP.equals(msg.sender().getHostString())){
            }
            /*ByteBuf buf = msg.copy().content();
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String res = new String(req, DEFAULT_ENCODING);
            log.trace("received from " + msg.sender().getHostString() +":"+ res);
            //过滤掉非本机发送的udp信息，增加安全性
            if (OUTPUT_UDP_SERVER_IP.equals(msg.sender().getHostString())) {
                //dispatchMsg(res);
            }*/
        }
    }
}
