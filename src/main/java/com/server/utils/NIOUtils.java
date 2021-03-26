package com.server.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOUtils {

    public  static void nioReactor(){
        try {
            //获取选择器
            Selector selctor = Selector.open();
            //获取通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //将通道设置为非阻塞
            ssc.configureBlocking(false);
            //通道绑定连接 bind
            ssc.bind(new InetSocketAddress("localhost",8082));
            //将通道注册到选择器上,设置为接收操作
            ssc.register(selctor, SelectionKey.OP_ACCEPT);
            //采用轮询的方式,查询获取准备就绪的注册操作
            while(selctor.select() > 0){
                //获取当前选择器所有注册的选择键(已准备就绪的操作)
                Iterator<SelectionKey> iter = selctor.selectedKeys().iterator();
                while(iter.hasNext()){
                    //获取准备就绪时间
                    SelectionKey sk = iter.next();
                    //判断 sk是什么事件
                    if(sk.isAcceptable()){
                        //接收事件,获取客户端连接
                        SocketChannel socketChannel = ssc.accept();
                        //切换为非阻塞
                        socketChannel.configureBlocking(false);
                        //将这个通道注册到选择器上
                        socketChannel.register(selctor, SelectionKey.OP_READ);
                    }else if(sk.isReadable()){
                        //获取选择器上的读就绪状态的通道
                        SocketChannel socketChannel = (SocketChannel)sk.channel();
                        //独取数据
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int length = 0;
                        while((length = socketChannel.read(byteBuffer)) != -1){
                            byteBuffer.flip();
                            System.out.println(new String(byteBuffer.array(),0,length));
                            byteBuffer.clear();
                        }
                        socketChannel.close();
                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
