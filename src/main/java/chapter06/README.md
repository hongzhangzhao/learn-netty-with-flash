# 第六章

## 数据逻辑处理

- 要想处理数据需要继承ChannelInboundHandlerAdapter并重写channelRead
- 数据传输用ByteBuf, 可以通过ChannelHandlerContext获取
- channelPipeline是一个责任链


- 往ByteBuf写数据, 就是在组装一个报文
- 将数据写到对端使用: channel.writeAndFlush(buffer); 这个方法可以在任何一个handler中调用
  - 那么调用writeAndFlush(), 接下来的handler还会执行吗?



## Question




