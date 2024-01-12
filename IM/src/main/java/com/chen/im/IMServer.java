package com.chen.im;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class IMServer {
    public static void start() {

        //创建两个线程
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        //监听
        ServerBootstrap bootstrap = new ServerBootstrap();
        //把线程放入线程池，
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)//并且放入NioServerSocketChannel这个信道中
                .childHandler(new ChannelInitializer<SocketChannel>() {//初始化chanel

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        //先通过socektChanel获取一个ChannelPipeline
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new HttpServerCodec())//添加http解码器
                                .addLast(new ChunkedWriteHandler())//支持大数据流
                                .addLast(new HttpObjectAggregator(1024 * 64))//对http消息做聚合操作，fullrequest,fullresponse
                                .addLast(new WebSocketServerProtocolHandler("/"))//websocket
                                .addLast(new WebSocketHandler());//支持读
                    }
                });

        //绑定端口
        ChannelFuture future = bootstrap.bind(8080);


    }
}
