package demoproxy01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;


import java.util.List;

public class TestHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf in = (ByteBuf) msg;

        String hexDump = ByteBufUtil.hexDump(in);
        System.out.println("-----TestHandler-----: " + hexDump);
        String result = hexToString(hexDump);
        System.out.println("####TestHandler######: " + result);

        // 将消息传递给下一个处理器
        ctx.fireChannelRead(msg);
    }

    public static String hexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            sb.append((char) Integer.parseInt(str, 16));
        }
        return sb.toString();
    }

}
