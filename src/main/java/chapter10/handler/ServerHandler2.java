package chapter10.handler;

import chapter10.packet.Packet;
import chapter10.packet.PacketCodec;
import chapter10.packet.login.LoginRequestPacket;
import chapter10.packet.login.LoginResponsePacket;
import chapter10.packet.message.MessageRequestPacket;
import chapter10.packet.message.MessageResponsePacket;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
@Slf4j
public class ServerHandler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(requestByteBuf);

        System.out.println("");


    }
}
