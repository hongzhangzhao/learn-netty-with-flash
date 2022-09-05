package chapter20.handler.clienthandler;

import chapter20.packet.group.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chanmoey
 * @date 2022年09月03日
 */
@Slf4j
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) throws Exception {
        log.info("群聊[{}]消息, 发送者：{}, 消息内容：{}", responsePacket.getFromGroupId(),
                responsePacket.getFromUser().getUserName(), responsePacket.getMessage());
    }
}
