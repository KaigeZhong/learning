package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
    // 信道选择器
    private Selector selector;

    // 与服务器通信的信道
    SocketChannel socketChannel;


    public static void main(String[] args) throws IOException {
        new NioClient().connect();
    }

    public NioClient() throws IOException {
        // 获得一个Socket通道
        SocketChannel channel = SocketChannel.open();
        // 设置通道为非阻塞
        channel.configureBlocking(false);
        // 获得一个通道管理器
        this.selector = Selector.open();

        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
        //用channel.finishConnect();才能完成连接
        channel.connect(new InetSocketAddress("localhost", 8080));
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
        channel.register(selector, SelectionKey.OP_CONNECT);

    }

    public void connect() throws IOException {
        // 轮询访问selector
        while (true) {
            selector.select();//如果队列有新的Channel加入，那么Selector.select()会被唤醒
            // 获得selector中选中的项的迭代器
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                // 连接事件发生
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 如果正在连接，则完成连接
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();

                    }
                    // 设置成非阻塞
                    channel.configureBlocking(false);

                    //在这里可以给服务端发送信息哦
                    channel.write(ByteBuffer.wrap(new String("hello server, this is client").getBytes()));
                    //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                    channel.register(this.selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    read(key);
                }

            }

        }

    }


    /**
     * 处理读取服务端发来的信息 的事件
     *
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {

        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();

//        //发送消息到服务端
//        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
//        channel.write(outBuffer);// 将消息回送给客户端
    }

    public static final String btyetoString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() == 1) {
                sb.append(0);

            }
            sb.append(sTemp.toUpperCase());
            sb.append(" ");
        }
        return sb.toString();
    }

//    /**
//     * 发送字符串到服务器
//     *
//     * @param message
//     * @throws IOException
//     */
//    public void sendMsg(String message) throws IOException {
//        ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
//
//        socketChannel.write(writeBuffer);
//    }
}
