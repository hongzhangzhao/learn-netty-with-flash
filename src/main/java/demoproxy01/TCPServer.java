package demoproxy01;



import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TCPServer {


    private Bootstrap bootstrap;
    private ServerBootstrap server;

    private NioEventLoopGroup bossgroup;
    private NioEventLoopGroup workgroup;

    public void init() {
        // 线程组
        this.bossgroup = new NioEventLoopGroup();  // 接收连接
        this.workgroup = new NioEventLoopGroup();  // 处理数据


        this.server = new ServerBootstrap();  // 服务端
        this.bootstrap = new Bootstrap();  // 客户端

        // 客户端
        this.bootstrap.channel(NioSocketChannel.class);
        this.bootstrap.group(bossgroup);

        // 服务端
        this.server.group(bossgroup, workgroup);
        this.server.channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))  // 处理打印日志
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("split", new Spliter());
                        //服务端channel，将服务端的数据发送给客户端，所以构造函数参数要传入客户端的channel
                        ch.pipeline().addLast("serverHandler", new DataHandler(getClientChannel(ch)));
                    }
                }).option(ChannelOption.SO_BACKLOG, 1024)
                // SO_SNDBUF发送缓冲区，SO_RCVBUF接收缓冲区，SO_KEEPALIVE开启心跳监测（保证连接有效）
                .option(ChannelOption.SO_SNDBUF, 16 * 1024)
                .option(ChannelOption.SO_RCVBUF, 16 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);
        this.server.bind(9999).syncUninterruptibly()
                .addListener((ChannelFutureListener) channelFuture -> {
                    if (channelFuture.isSuccess()) {
                        System.out.println("服务器启动成功");
                    } else {
                        System.out.println("服务器启动失败");
                    }
                });
    }

    private Channel getClientChannel(SocketChannel ch) throws InterruptedException {
        this.bootstrap
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        //客户端端channel，客户端返回的数据给服务端，所以构造函数参数要传入服务端的channel
                        socketChannel.pipeline().addLast("clientHandler", new DataHandler2(ch));
                    }
                });
        ChannelFuture sync = bootstrap.connect("192.168.1.164", 9042).sync();
        return sync.channel();
    }


    public static void main(String[] args) {
        TCPServer tcpServer = new TCPServer();
        tcpServer.init();
    }


}
