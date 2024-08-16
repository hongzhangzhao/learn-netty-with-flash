# 第二章

## Netty 是什么

## 传统IO编程实现客户端和服务端通信程序

- IOClient
- IOServer

- 传统IO的方式, 问题在于, 服务端每接收一个客户端连接都会创建一个线程


## NIO编程(传统IO的升级版)

- NIOClient
- NIOServer

- 它解决了, 每个连接都创建一个线程
- 每当有新连接, 它会将新连接注册到Selector中, 再由一个线程去批量处理Selector中的连接


## Netty编程

- NettyServer
- NettyClient

- 特点: 异步事件驱动

## Question

- 客户端和服务端通信程序的核心概念是什么?
  - 关键技术: Socket编程(网络编程)
- 客户端和服务端是如何建立连接的?
- 什么是阻塞方法?


