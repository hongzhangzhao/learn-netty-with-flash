# Demo Proxy


# 参考
- https://www.cnblogs.com/flydean/p/15725880.html
- https://blog.csdn.net/qq_15038701/article/details/123995361

# 代码逻辑
```
数据交换handler 继承ChannelInboundHandlerAdapter类 中channelRead方法获取服务端数据和客户端数据，打通服务端和客户端互相交换数据。
原理是通过两者各自拿到对方channel互相发送数据
```


